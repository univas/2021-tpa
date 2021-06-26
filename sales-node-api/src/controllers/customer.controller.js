const customerService = require('../services/customer.service')

//remove this line below
const my_database = new Map()

const listAll = async (req, res) => {
  return await customerService.listAll()
    .then(items => {
      res.status(200).send(items)
    })
}

const create = async (req, res) => {
  return await customerService.save(req, res)
    .then(item =>
      res.status(201).send(item)
    )
}

const update = async (req, res) => {
  return await customerService.update(req, res)
    .then(() =>
      res.status(200).send(`Customer updated with successfully`)
    )
}

const remove = async (req, res) => {
  return await customerService.remove(req, res)
    .then(item =>
      res.status(200).send(item)
    )
}

module.exports = {
  listAll,
  create,
  update,
  remove
}