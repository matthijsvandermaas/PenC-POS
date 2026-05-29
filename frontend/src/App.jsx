import { useState, useEffect } from 'react'

function App() {
  const [categories, setCategories] = useState([])
  const [products, setProducts] = useState([])
  const [activeCategory, setActiveCategory] = useState(null)
  const [order, setOrder] = useState([])
  const [showPayment, setShowPayment] = useState(false)

  useEffect(() => {
    fetch('http://localhost:8081/categories')
      .then(res => res.json())
      .then(data => {
        setCategories(data)
        setActiveCategory(data[0]?.id)
      })
    fetch('http://localhost:8081/products')
      .then(res => res.json())
      .then(data => setProducts(data))
  }, [])

  const filteredProducts = products.filter(p => p.category === categories.find(c => c.id === activeCategory)?.name)

  const addToOrder = (product) => {
    setOrder(prev => {
      const existing = prev.find(i => i.id === product.id)
      if (existing) return prev.map(i => i.id === product.id ? { ...i, qty: i.qty + 1 } : i)
      return [...prev, { ...product, qty: 1 }]
    })
  }

  const total = order.reduce((sum, i) => sum + i.price * i.qty, 0)

  const placeOrder = () => {
    if (order.length === 0) return
    const orderData = {
      items: order.map(item => ({ productId: item.id, quantity: item.qty })),
      total: total,
      status: 'open'
    }
    fetch('http://localhost:8081/orders', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(orderData)
    })
      .then(res => res.json())
      .then(() => alert('Bestelling naar keuken/bar gestuurd!'))
  }

  const checkout = (method) => {
    setOrder([])
    setShowPayment(false)
    alert(`Betaald met ${method}!`)
  }

  return (
    <div style={{ display: 'flex', height: '100vh', fontFamily: 'sans-serif' }}>

      <div style={{ flex: 1, padding: '16px' }}>
        <div style={{ display: 'flex', gap: '8px', flexWrap: 'wrap', marginBottom: '16px' }}>
          {categories.map(cat => (
            <button
              key={cat.id}
              onClick={() => setActiveCategory(cat.id)}
              style={{
                padding: '8px 16px',
                borderRadius: '8px',
                border: 'none',
                cursor: 'pointer',
                background: activeCategory === cat.id ? '#6c63ff' : '#e0e0e0',
                color: activeCategory === cat.id ? 'white' : 'black',
                fontWeight: activeCategory === cat.id ? 'bold' : 'normal'
              }}
            >
              {cat.name}
            </button>
          ))}
        </div>

        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '12px' }}>
          {filteredProducts.map(product => (
            <button
              key={product.id}
              onClick={() => addToOrder(product)}
              style={{
                padding: '24px 8px',
                borderRadius: '12px',
                border: '1px solid #555',
                cursor: 'pointer',
                background: '#2a2a2a',
                color: 'white',
                fontSize: '15px',
                fontWeight: 'bold'
              }}
            >
              {product.name}<br />
              <span style={{ fontWeight: 'normal', fontSize: '13px' }}>€{product.price.toFixed(2)}</span>
            </button>
          ))}
        </div>
      </div>

      <div style={{ width: '280px', background: '#f5f5f5', padding: '16px', display: 'flex', flexDirection: 'column' }}>
        <h2 style={{ marginTop: 0 }}>Rekening</h2>
        <div style={{ flex: 1 }}>
          {order.length === 0 && <p style={{ color: '#aaa' }}>Nog geen items</p>}
          {order.map(item => (
            <div key={item.id} style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '8px' }}>
              <span>{item.name} x{item.qty}</span>
              <span>€{(item.price * item.qty).toFixed(2)}</span>
            </div>
          ))}
        </div>
        <hr />
        <div style={{ display: 'flex', justifyContent: 'space-between', fontWeight: 'bold', fontSize: '18px' }}>
          <span>Totaal</span>
          <span>€{total.toFixed(2)}</span>
        </div>
        <button
          onClick={placeOrder}
          style={{ marginTop: '16px', padding: '12px', background: '#6c63ff', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '16px', width: '100%' }}>
          Bestelling plaatsen
        </button>
        <button
          onClick={() => setShowPayment(true)}
          style={{ marginTop: '8px', padding: '12px', background: '#28a745', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '16px', width: '100%' }}>
          Afrekenen
        </button>

        {showPayment && (
          <div style={{ position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, background: 'rgba(0,0,0,0.5)', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <div style={{ background: 'white', padding: '32px', borderRadius: '16px', textAlign: 'center', width: '300px' }}>
              <h2 style={{ marginBottom: '8px' }}>Afrekenen</h2>
              <p style={{ marginBottom: '24px', fontSize: '24px', fontWeight: 'bold' }}>€{total.toFixed(2)}</p>
              <button
                onClick={() => checkout('pin')}
                style={{ width: '100%', padding: '12px', marginBottom: '8px', background: '#6c63ff', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '16px' }}>
                💳 Pin
              </button>
              <button
                onClick={() => checkout('contant')}
                style={{ width: '100%', padding: '12px', marginBottom: '8px', background: '#28a745', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '16px' }}>
                💵 Contant
              </button>
              <button
                onClick={() => setShowPayment(false)}
                style={{ width: '100%', padding: '12px', background: '#ccc', color: 'black', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '16px' }}>
                Annuleren
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default App