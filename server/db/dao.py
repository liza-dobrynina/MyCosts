from peewee import *
from .model import Category, Expense


def findAllCategories():
    return list(Category.select())


def findCategory(id):
    return Category.get_by_id(id)


def createCategory(name, maxSum):
    newCategory = Category(name=name, maxSum=maxSum)
    newCategory.save()
    return newCategory


def deleteCategory(id):
    return (Category.select()
        .where(Category.id == id)
        .get()
        .delete_instance())


def updateCategory(id, name, maxSum):
    category = (Category.select()
        .where(Category.id == id)
        .get())
    category.name = name
    category.maxSum = maxSum
    category.save()
    return category


def findAllExpenses():
    return list(Expense.select())


def createExpense(name, date, summ, categoryId):
    category = Category.get_by_id(categoryId)
    newExpense = Expense(name=name, date=date, summ=summ, category=category)
    newExpense.save()
    return newExpense


def deleteExpense(id):
    return (Expense.select()
        .where(Expense.id == id)
        .get()
        .delete_instance())


def updateExpense(id, name, date, summ, categoryId):
    expense = (Expense.select()
        .where(Expense.id == id)
        .get())
    expense.name = name
    expense.date = date
    expense.summ = summ
    expense.category = Category.get_by_id(categoryId)
    expense.save()
    return expense
