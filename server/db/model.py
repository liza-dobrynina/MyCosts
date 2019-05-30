from peewee import *


db = SqliteDatabase('db/database.db')
db.connect()


class BaseModel(Model):
    class Meta:
        database = db


class Category(BaseModel):
    name = CharField()
    maxSum = IntegerField(column_name='max_sum')


class Expense(BaseModel):
    name = CharField()
    date = DateTimeField()
    summ = IntegerField(column_name='sum')
    category = ForeignKeyField(Category, backref='category')
