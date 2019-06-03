from peewee import *


db = SqliteDatabase("db/database.db", pragmas={'foreign_keys': 1})
db.connect()


class BaseModel(Model):
    class Meta:
        database = db


class Category(BaseModel):
    name = CharField()
    maxSum = IntegerField(column_name="max_sum")


class Expense(BaseModel):
    name = CharField()
    date = DateField()
    summ = IntegerField(column_name="sum", default=0)
    category = ForeignKeyField(Category, backref="expenses")
