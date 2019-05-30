from typing import List
from datetime import datetime
import uvicorn
from fastapi import FastAPI
from pydantic import BaseModel
from peewee import SqliteDatabase
import db.dao
import db.model


app = FastAPI()


class Category(BaseModel):
    id: int = None
    name: str
    maxSum: int

    @staticmethod
    def fromDbModel(category: db.model.Category) -> 'Category':
        return Category(id=category.id, name=category.name, maxSum=category.maxSum)

class CategoryList(BaseModel):
    categories: List[Category]

    @staticmethod
    def fromDbModel(categories: List[db.model.Category]) -> 'List[Category]':
        return CategoryList(categories=[Category.fromDbModel(c) for c in categories])


class Expense(BaseModel):
    id: int = None
    name: str
    date: datetime
    summ: int
    categoryId: int

    @staticmethod
    def fromDbModel(expense: db.model.Expense) -> 'Expense':
        return Expense(id=expense.id, name=expense.name, date=expense.date, summ=expense.summ, categoryId=expense.category.id)


class ExpenseList(BaseModel):
    expenses: List[Expense]

    @staticmethod
    def fromDbModel(expenses: List[db.model.Expense]) -> 'List[Expense]':
        return ExpenseList(expenses=[Expense.fromDbModel(e) for e in expenses])


@app.get("/category", response_model=CategoryList)
async def getAllCategories():
    categories = db.dao.findAllCategories()
    return CategoryList.fromDbModel(categories)


@app.post("/category", response_model=Category)
async def createCategory(category: Category):
    newCategory = db.dao.createCategory(category.name, category.maxSum)
    return Category.fromDbModel(newCategory)


@app.put("/category/{id}", response_model=Category)
async def updateCategory(id: int, category: Category):
    newCategory = db.dao.updateCategory(id, category.name, category.maxSum)
    return Category.fromDbModel(newCategory)


@app.delete("/category/{id}")
async def deleteCategory(id: int):
    return { "numDeleted": db.dao.deleteCategory(id)}


@app.get("/expense", response_model=ExpenseList)
async def getAllExpenses():
    expenses = db.dao.findAllExpenses()
    return ExpenseList.fromDbModel(expenses)


@app.post("/expense", response_model=Category)
async def createExpense(expense: Expense):
    newExpense = db.dao.createExpense(name=expense.name, date=expense.date, summ=expense.summ, categoryId=expense.categoryId)
    return Expense.fromDbModel(newExpense)


@app.put("/expense/{id}", response_model=Expense)
async def updateExpense(id: int, expense: Expense):
    newExpense = db.dao.updateExpense(id, expense.name, expense.date, expense.summ, expense.category)
    return Expense.fromDbModel(newExpense)


@app.delete("/expense/{id}")
async def deleteExpense(id: int):
    return { "numDeleted": db.dao.deleteExpense(id)}


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)