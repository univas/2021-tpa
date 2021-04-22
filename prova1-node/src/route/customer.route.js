const express = require('express')
const router = express.Router()
const controller = require('../controller/customer.controller')

router.get('/:cpf/dependents', controller.listDependents)
router.delete('/:cpf', controller.remove)
router.get('/:cpf', controller.getByCpf)
router.put('/:cpf', controller.update)
router.get('/', controller.list)
router.post('/', controller.create)

module.exports = router