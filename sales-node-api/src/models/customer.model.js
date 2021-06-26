module.exports = (sequelize, DataTypes) => {

  const Customer = sequelize.define('customer', {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    cpf: DataTypes.STRING,
    nome: DataTypes.STRING,
    email: DataTypes.STRING
    }, {
    freezeTableName: true,
    timestamps: false
  })

  return Customer
}
