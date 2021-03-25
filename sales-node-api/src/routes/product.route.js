const express = require('express')
const controller = require('../controllers/product.controller')

const router = express.Router()

router.get('/', controller.listAll)
router.post('/', controller.create)
router.put('/:id', controller.update)
router.delete('/:id', controller.remove)

module.exports = router