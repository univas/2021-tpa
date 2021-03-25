const express = require('express')
const productRoute = require('./routes/product.route')

const app = express()

app.get('/', (req, res) => {
  res.send('Hello World!!!!')
})

app.use(express.json())
app.use('/products', productRoute)

app.listen(8080, () => {
  console.log('Server started on port 8080')
})
