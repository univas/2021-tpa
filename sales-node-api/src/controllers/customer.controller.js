const my_database = new Map()

const listAll = (req, res) => {
  const values = Array.from(my_database.values())
  res.status(200).send(values)
}

const create = (req, res) => {
  const customer = req.body
  my_database.set(customer.id, customer)
  res.status(201).send(customer)
}

const update = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const customer = req.body
    customer.id = id
    my_database.set(id, customer)
    res.status(200).send(customer)
  } else {
    res.status(404).send({
      message: `customer with id (${id}) not found`
    })
  }
}

const remove = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const customer = my_database.get(id)
    my_database.delete(customer.id)
    res.status(200).send(customer)
  } else {
    res.status(404).send({
      message: `customer with id (${id}) not found`
    })
  }
}

module.exports = {
  listAll,
  create,
  update,
  remove
}