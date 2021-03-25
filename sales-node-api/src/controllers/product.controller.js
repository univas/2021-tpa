const { response } = require("express")

const my_database = new Map()

const listAll = (req, res) => {
  const values = Array.from(my_database.values())
  res.status(200).send(values)
}

const create = (req, res) => {
  const product = req.body
  my_database.set(product.id, product)
  res.status(201).send(product)
}

const update = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const product = req.body
    product.id = id
    my_database.set(id, product)
    res.status(200).send(product)
  } else {
    res.status(404).send({
      message: `product with id (${id}) not found`
    })
  }
}

const remove = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const product = my_database.get(id)
    my_database.delete(product.id)
    res.status(200).send(product)
  } else {
    res.status(404).send({
      message: `product with id (${id}) not found`
    })
  }
}

module.exports = {
  listAll,
  create,
  update,
  remove
}