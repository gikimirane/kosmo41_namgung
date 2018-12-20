# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 12:53:07 2018

@author: kosmo10
"""


#! /usr/bin/env python3

import sqlite3
import csv
import sys

input_file="./Data/data_for_updating.csv"

# 메모리에 sales 테이블이 없으면 새로 만들어라.
con=sqlite3.connect(':memory:')
query = """CREATE TABLE if not exists sales
			(customer VARCHAR(20), 
			 product VARCHAR(40),
			 amount FLOAT,
			 date DATE);"""
con.execute(query)
con.commit()


# insert  a few rows of data into the table
data = [('Richard Lucas', 'Notepad', 2.50, '2014-01-02'),
		('Jenny Kim', 'Binder', 4.15, '2014-01-15'),
		('Svetlana Crow', 'Printer', 155.75, '2014-02-03'),
		('Stephen Randolph', 'Computer', 679.40, '2014-02-20')]

for tuple in data:
    print(tuple)
    
statement = "INSERT INTO sales VALUES(?,?,?,?)"
con.executemany(statement,data)
con.commit()

#Read the CSV file
#Insert the data into the Suplliers table
file_reader = csv.reader(open(input_file,'r'),delimiter=',')
header =next(file_reader,None)
for row in file_reader:
    data=[]
    for column_index in range(len(header)):
        data.append(row[column_index])
    print(data)
    con.execute("update sales set amount=?,date=? where customer=?;",data)
con.commit()

# Query the Suppliers table
output = con.execute("select * from sales")
rows = output.fetchall()
for row in rows :
    output=[]
    for column_index in range(len(row)):
        output.append(str(row[column_index]))
    print(output)
