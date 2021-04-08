const express = require('express')
const productRoute = require('./routes/product.route')
const customerRoute = require('./routes/customer.route')
const saleRoute = require('./routes/sale.route')

const app = express()

app.get('/', (req, res) => {
  res.send('Hello World!!!!')
})

app.use(express.json())
app.use('/products', productRoute)
app.use('/customers', customerRoute)
app.use('/sales', saleRoute)

const port = process.env.PORT || 8080

app.listen(port, () => {
  console.log(`Server started on port ${port}`)
})
