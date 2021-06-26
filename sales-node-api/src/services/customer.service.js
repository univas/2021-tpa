const { customer } = require('../models/index')

const listAll = async () => {
  return item = await customer.findAll({})
}

const save = async (req, res) => {
  return await customer.create(req.body).then(item => item)
}

const update = async (req, res) => {
  return await customer.update(req.body, {
    where: {
      id: req.params.id
    }
  }).then(item => item)
}

const remove = async (req, res) => {
  return await customer.findAll(
    {
      where: {
        id: req.params.id
      }
    }
  ).then(item => {
    item[0].destroy()
    return item[0]
  })
}

module.exports = {
  listAll,
  save,
  update,
  remove
}