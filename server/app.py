from flask import Flask, jsonify, request
from peewee import SqliteDatabase
#from db.model import Category, Expense
from db import dao


app = Flask(__name__)


class Category:
    maxSum : int
    name : str


# class Expense:
#     name : str
#     date : datetime
#     summ = IntegerField()
#     category = ForeignKeyField(Category, backref='category')


@app.route("/category", methods=['GET'])
def getAllCategories():
    c = dao.findAllCategories()
    return jsonify(c)


@app.route("/category", methods=['POST'])
def createCategory():
    category = request.json
    return jsonify(category)


if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)