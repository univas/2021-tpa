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
  console.log('[CONTROLLER] ESTOU NO PUT!!!')
}

const remove = (req, res) => {
  console.log('[CONTROLLER] ESTOU NO DELETE!!!')
}

module.exports = {
  listAll,
  create,
  update,
  remove
}