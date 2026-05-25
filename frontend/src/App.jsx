import { useState, useEffect } from 'react'

function App() {
  const [products, setProducts] = useState([])

  useEffect(() => {
    fetch('http://localhost:8081/products')
      .then(res => res.json())
      .then(data => setProducts(data))
  }, [])

  return (
    <div>
      <h1>PenC POS</h1>
      <h2>Producten</h2>
      <ul>
        {products.map(product => (
          <li key={product.id}>
            {product.name} - €{product.price} ({product.category})
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App