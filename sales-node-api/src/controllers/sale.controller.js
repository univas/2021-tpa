const my_database = new Map()

const listAll = (req, res) => {
  const values = Array.from(my_database.values())
  res.status(200).send(values)
}

const create = (req, res) => {
  const sale = req.body
  my_database.set(sale.id, sale)
  res.status(201).send(sale)
}

const update = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const sale = req.body
    sale.id = id
    my_database.set(id, sale)
    res.status(200).send(sale)
  } else {
    res.status(404).send({
      message: `sale with id (${id}) not found`
    })
  }
}

const remove = (req, res) => {
  const id = parseInt(req.params.id, 10)

  if (my_database.has(id)) {
    const sale = my_database.get(id)
    my_database.delete(sale.id)
    res.status(200).send(sale)
  } else {
    res.status(404).send({
      message: `sale with id (${id}) not found`
    })
  }
}

module.exports = {
  listAll,
  create,
  update,
  remove
}