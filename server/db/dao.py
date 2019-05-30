from peewee import *
from .model import Category, Expense


def findAllCategories():
    return list(Category.select())


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


def updateExpense(id, name, date, summ, category):
    expense = (Expense.select()
        .where(Expense.id == id)
        .get())
    expense.name = name
    expense.date = date
    expense.summ = summ
    expense.save()
    return expense


def findAllCategoriesWithExpenses():
    query = (Category
        .select(Category)
        .join(Expense, JOIN.INNER)
        .order_by(Expense.date))
    return list(query)


def findAllExpensesSumForCategory(category):
    query = (Expense
        .select(fn.SUM(Expense.sum))
        .where(Expense.category.id == category.id))
    return query.get()


def findExpensesSumWithMaxSumForCategory(category):
    query = (Category
        .select(fn.SUM(Expense.sum).alias('actualSum'), Category.maxSum)
        .join(Category, JOIN.INNER)
        .order_by(Category.name))
    return query.get()
