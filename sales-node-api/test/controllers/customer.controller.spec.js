const sinon = require('sinon')
const chai = require('chai')
const expect = chai.expect
const controller = require('../../src/controllers/customer.controller')

describe('Customer Controller', () => {
  const myCustomer = {
    id: 123,
    name: 'João'
  }

  it('Call create method should add new customer', () => {
    const req = {
      body: myCustomer
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.create(req, res)

    expect(res.send.calledOnce).to.be.false
    expect(res.send.firstCall.args[0]).to.equal(myCustomer)
  })

  it('Call listAll method should send an empty array', () => {
    const req = {}
    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.listAll(req, res)

    const parameter = res.send.firstCall.args[0]
    
    expect(res.send.calledOnce).to.be.true
    expect(parameter.length).to.be.equal(1)
    expect(parameter[0].id).to.be.equal(myCustomer.id)
    expect(parameter[0].name).to.be.equal(myCustomer.name)
  })

  it('Call update method with correct parameter should update customer', () => {
    const req = {
      body: myCustomer,
      params: {
        id: myCustomer.id
      }
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.update(req, res)

    expect(res.send.calledOnce).to.be.true
    expect(res.send.firstCall.args[0]).to.equal(myCustomer)
  })

  it('Call update method with invalid parameter should not update customer', () => {
    const req = {
      body: myCustomer,
      params: {
        id: 999
      }
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.update(req, res)

    expect(res.send.calledOnce).to.be.true
    expect(res.send.firstCall.args[0]).to.be.eql({
      message: `customer with id (${req.params.id}) not found`
    })
  })

  it('Call remove method with correct parameter should remove the customer', () => {
    const req = {
      params: {
        id: myCustomer.id
      }
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.remove(req, res)

    expect(res.send.calledOnce).to.be.true
    expect(res.send.firstCall.args[0]).to.be.equal(myCustomer)
  })

  it('Call remove method with invalid parameter should not delete the customer', () => {
    const req = {
      params: {
        id: 54
      }
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.remove(req, res)

    expect(res.send.calledOnce).to.be.true
    expect(res.send.firstCall.args[0]).to.be.eql({
      message: `customer with id (${req.params.id}) not found`
    })
  })
})