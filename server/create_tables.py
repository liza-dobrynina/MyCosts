from db.model import Category, Expense
from peewee import SqliteDatabase


db = SqliteDatabase('db/database.db')
db.connect()
db.create_tables([Category, Expense])
