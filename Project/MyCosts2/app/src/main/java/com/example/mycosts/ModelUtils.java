package com.example.mycosts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ModelUtils {

    public static List<Category> fillCategories() {
        List<Category> categories = new ArrayList<>();
        Category food = new Category("Еда", 1000);
        categories.add(food);
        Category entertainment = new Category("Развлечения", 5000);
        categories.add(entertainment);
        Category misc = new Category("Всякое", 500);
        categories.add(misc);

        misc.getExpenses().add(new Expense("швабра", DateUtils.convertStringToDate("10.12.2018"), 400, misc));
        misc.getExpenses().add(new Expense("обои", DateUtils.convertStringToDate("10.04.2019"), 4500, misc));

        food.getExpenses().add(new Expense("кефир", DateUtils.convertStringToDate("15.05.2019"), 60, food));
        food.getExpenses().add(new Expense("кетчуп", DateUtils.convertStringToDate("05.05.2019"), 40, food));
        food.getExpenses().add(new Expense("суп в столовке", DateUtils.convertStringToDate("10.05.2019"), 70, food));

        entertainment.getExpenses().add(new Expense("кино", DateUtils.convertStringToDate("12.05.2019"), 200, entertainment));
        entertainment.getExpenses().add(new Expense("автобус", DateUtils.convertStringToDate("13.05.2019"), 17, entertainment));
        entertainment.getExpenses().add(new Expense("стул на колесиках", DateUtils.convertStringToDate("13.05.2019"), 4000, entertainment));
        return categories;
    }

    public static List<Expense> getAllExpenses(List<Category> categories) {
        List<Expense> expenses = new ArrayList<>();
        for (Category category : App.getCategories()) {
            expenses.addAll(category.getExpenses());
        }
        sortExpensesByDate(expenses);
        return expenses;
    }

    private static void sortExpensesByDate(List<Expense> expenses) {
        Collections.sort(expenses, new Comparator<Expense>() {
            @Override
            public int compare(Expense o1, Expense o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }

    public static Category findCategoryByName(String name) {
        for (Category category : App.getCategories()){
            if (category.getName().equals(name))
                return category;
        }
        return null;
    }

    public static Expense findExpenseById(int id, Category category) {
        for (Expense expense: category.getExpenses()) {
            if (expense.getId() == id)
                return expense;
        }
        return null;
    }
}
