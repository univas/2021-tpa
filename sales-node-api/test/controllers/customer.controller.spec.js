const sinon = require('sinon')
const chai = require('chai')
const expect = chai.expect
const controller = require('../../src/controllers/customer.controller')

describe('Customer Controller', () => {
   it('Call create method should add new customer', () => {
    const myCustomer = {
      id: 123,
      name: 'João'
    }

    const req = {
      body: myCustomer
    }

    const res = {}
    res.status = () => res
    res.send = sinon.spy()

    controller.create(req, res)

    expect(res.send.calledOnce).to.be.true
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
    expect(parameter[0].id).to.be.equal(123)
    expect(parameter[0].name).to.be.equal('João')
  })
})