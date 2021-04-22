const express = require('express')
const customerRouter = require('./route/customer.route')

const app = express()

app.get('/', (req, res) => {
  res.send('Estou aqui!!!')
})

app.use(express.json())
app.use('/customers', customerRouter)

app.listen(8080, () => {
  console.log('Server started!!!')
})