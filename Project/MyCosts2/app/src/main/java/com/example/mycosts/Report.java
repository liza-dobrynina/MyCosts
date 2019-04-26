package com.example.mycosts;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;

public class Report {
 private Date beginDate ;
 private Date endDate ;

 private HashMap<Category,Integer> ExpensesForPeriod;
 public Report(Date beginDate, Date endDate){
  this.beginDate =  beginDate;
  this.endDate = endDate;
  this.ExpensesForPeriod  = buildReport(beginDate,endDate);

 }

 private HashMap<Category,Integer> buildReport(Date begDate, Date eDate){
  HashMap<Category,Integer> t= new HashMap<>();
  return t;
 }

 public Date getBeginDate() {
  return beginDate;
 }

 public void setBeginDate(Date beginDate) {
  this.beginDate = beginDate;
 }

 public Date getEndDate() {
  return endDate;
 }

 public void setEndDate(Date endDate) {
  this.endDate = endDate;
 }

 public HashMap<Category, Integer> getExpensesForPeriod() {
  return ExpensesForPeriod;
 }

 public void setExpensesForPeriod(HashMap<Category, Integer> expensesForPeriod) {
  ExpensesForPeriod = expensesForPeriod;
 }
}
