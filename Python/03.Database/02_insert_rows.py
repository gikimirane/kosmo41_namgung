# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 12:30:17 2018

@author: kosmo10
"""

#! /usr/bin/env python3

import sqlite3
import csv
import sys

#Path to and name of a CSV input file

input_file = "./Data/supplier_data.csv"

# Createan in-memory SQLite3 database 
# Create a table called Suppliers with five attributes
con=sqlite3.connect('./Data/Suppliers.db')
c = con.cursor()
create_table = """create table if not exists Suppliers
                (Supplier_Name varchar(20),
                Invoice_Number varchar(20),
                Part_Number varchar(20),
                Cost Float,
                Purchase_Date DATE);"""

c.execute(create_table)
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
    c.execute("insert into Suppliers Values (?,?,?,?,?);",data)
con.commit()

# Query the Suppliers table
output = c.execute("select * from Suppliers")
rows = output.fetchall()
for row in rows :
    output=[]
    for column_index in range(len(row)):
        output.append(str(row[column_index]))
    print(output)
