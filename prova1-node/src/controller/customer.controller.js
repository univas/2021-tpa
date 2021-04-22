const my_database = new Map()

const create = (req, res) => {
  const customer = req.body
  my_database.set(customer.cpf, customer)
  res.status(201).send(customer)
}

const list = (req, res) => {
  const values = Array.from(my_database.values())
  res.status(200).send(values)
}

const update = (req, res) => {
  const cpf = req.params.cpf

  if (my_database.has(cpf)) {
    const customer = req.body
    customer.cpf = cpf
    my_database.set(cpf, customer)
    res.status(200).send(customer)
  } else {
    res.status(404).send({
      message: 'CPF not found'
    })
  }
}

const getByCpf = (req, res) => {
  const cpf = req.params.cpf

  if (my_database.has(cpf)) {
    let customer = my_database.get(cpf)
    res.status(200).send(customer)
  } else {
    res.status(404).send({
      message: 'CPF not found'
    })
  }
}

const remove = (req, res) => {
  const cpf = req.params.cpf

  if (my_database.has(cpf)) {
    deleteDependents(cpf)
    const customer = my_database.get(cpf)
    my_database.delete(cpf)
    res.status(200).send(customer)

  } else {
    res.status(404).send({
      message: 'CPF not found'
    })
  }
}

const deleteDependents = (cpf) => {
  const items = Array.from(my_database.values())
      .filter(customer => customer.cpfTitular === cpf)

  items.forEach(dependent => {
    my_database.delete(dependent.cpf)
  })
}

const listDependents = (req, res) => {
  const cpf = req.params.cpf

  if (my_database.has(cpf)) {
    const items = Array.from(my_database.values())
      .filter(customer => customer.cpfTitular === cpf)
    res.status(200).send(items)
  } else {
    res.status(404).send({
      message: 'CPF not found'
    })
  }
}


module.exports = {
  create,
  list,
  update,
  getByCpf,
  remove,
  listDependents
}