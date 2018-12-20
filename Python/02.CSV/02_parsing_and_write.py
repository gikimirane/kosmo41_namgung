# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 12:37:19 2018

@author: kosmo10
"""

#! /usr/bin/env python3
import csv
import sys

input_file = "./Data/01_data.csv"
output_file = "./Output/out02.csv"

with open(input_file,'r',newline='') as csv_in_file:
    with open(output_file,'w',newline='') as csv_out_file:
       filereader = csv.reader(csv_in_file,delimiter=',')
       filewriter = csv.writer(csv_out_file,delimiter=',')
       for row_list in filereader:
           filewriter.writerow(row_list)