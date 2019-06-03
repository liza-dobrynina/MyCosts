from typing import List
from datetime import date
import uvicorn
from fastapi import FastAPI
from fastapi.exceptions import RequestValidationError
from starlette.exceptions import HTTPException as StarletteHTTPException
from starlette.responses import PlainTextResponse
from starlette.status import HTTP_201_CREATED, HTTP_204_NO_CONTENT
from pydantic import BaseModel
from peewee import SqliteDatabase
import db.dao
import db.model


app = FastAPI()


@app.exception_handler(StarletteHTTPException)
async def http_exception_handler(request, exc):
    return PlainTextResponse(str(exc.detail), status_code=exc.status_code)

@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request, exc):
    return PlainTextResponse(str(exc), status_code=400)


class Category(BaseModel):
    id: int = None
    name: str
    maxSum: int
    currentSum: int

    @classmethod
    def fromDbModel(cls, category: db.model.Category):
        apiModelCategory = cls(id=category.id, name=category.name, maxSum=category.maxSum, currentSum=sum((e.summ for e in category.expenses)))
        return apiModelCategory

class CategoryList(BaseModel):
    categories: List[Category]

    @classmethod
    def fromDbModel(cls, categories: List[db.model.Category]):
        return cls(categories=[Category.fromDbModel(c) for c in categories])


class CategoryToUpdate(BaseModel):
    name: str
    maxSum: int

    @classmethod
    def fromDbModel(cls, category: db.model.Category):
        apiModelCategory = cls(name=category.name, maxSum=category.maxSum)
        return apiModelCategory


class Expense(BaseModel):
    id: int = None
    name: str
    date: date
    summ: int
    categoryId: int

    @classmethod
    def fromDbModel(cls, expense: db.model.Expense):
        return cls(id=expense.id, name=expense.name, date=expense.date, summ=expense.summ, categoryId=expense.category.id)


class ExpenseList(BaseModel):
    expenses: List[Expense]

    @classmethod
    def fromDbModel(cls, expenses: List[db.model.Expense]):
        return cls(expenses=[Expense.fromDbModel(e) for e in expenses])


class ExpenseWithCategory(BaseModel):
    id: int = None
    name: str
    date: date
    summ: int
    category: Category

    @classmethod
    def fromDbModel(cls, expense: db.model.Expense):
        return cls(id=expense.id, name=expense.name, date=expense.date, summ=expense.summ, category=Category.fromDbModel(expense.category))


class ExpenseWithCategoryList(BaseModel):
    expenses: List[ExpenseWithCategory]

    @classmethod
    def fromDbModel(cls, expenses: List[db.model.Expense]):
        return cls(expenses=[ExpenseWithCategory.fromDbModel(e) for e in expenses])


class ExpenseToUpdate(BaseModel):
    name: str
    date: date
    summ: int
    categoryId: int

    @classmethod
    def fromDbModel(cls, expense: db.model.Expense):
        return cls(name=expense.name, date=expense.date, summ=expense.summ, categoryId=expense.category.id)


@app.get("/categories", response_model=CategoryList, summary="Get all categories")
async def getAllCategories():
    categories = db.dao.findAllCategories()
    return CategoryList.fromDbModel(categories)


@app.get("/categories/{id}", response_model=Category, summary="Get category with specified id")
async def getCategory(id: int):
    category = db.dao.findCategory(id)
    return Category.fromDbModel(category)


@app.post("/categories", response_model=Category, status_code=HTTP_201_CREATED, summary="Create new category")
async def createCategory(category: CategoryToUpdate):
    newCategory = db.dao.createCategory(category.name, category.maxSum)
    return Category.fromDbModel(newCategory)


@app.put("/categories/{id}", response_model=Category, summary="Update category with specified id")
async def updateCategory(id: int, category: CategoryToUpdate):
    newCategory = db.dao.updateCategory(id, category.name, category.maxSum)
    return Category.fromDbModel(newCategory)


@app.delete("/categories/{id}", status_code=HTTP_204_NO_CONTENT, \
    summary="Delete category with specified id", description="Note: you can't delete category with existing expenses")
async def deleteCategory(id: int):
    db.dao.deleteCategory(id)
    return None


@app.get("/expenses", response_model=ExpenseWithCategoryList, summary="Get all expenses")
async def getAllExpenses():
    expenses = db.dao.findAllExpenses()
    return ExpenseWithCategoryList.fromDbModel(expenses)


@app.post("/expenses", response_model=Expense, status_code=201, summary="Create new expense")
async def createExpense(expense: ExpenseToUpdate):
    newExpense = db.dao.createExpense(name=expense.name, date=expense.date, summ=expense.summ, categoryId=expense.categoryId)
    return Expense.fromDbModel(newExpense)


@app.put("/expenses/{id}", response_model=Expense, summary="Update expense with specified id")
async def updateExpense(id: int, expense: ExpenseToUpdate):
    newExpense = db.dao.updateExpense(id, expense.name, expense.date, expense.summ, expense.categoryId)
    return Expense.fromDbModel(newExpense)


@app.delete("/expenses/{id}", status_code=HTTP_204_NO_CONTENT, summary="Delete expense with specified id")
async def deleteExpense(id: int):
    db.dao.deleteExpense(id)
    return None


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)