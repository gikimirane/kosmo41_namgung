# -*- coding: utf-8 -*-
"""
Created on Tue Dec 11 09:35:57 2018

@author: kosmo10
"""


#! /usr/bin/env python3
import sys
import csv
# input_file = sys.argv[1]
# input_file = sys.argv[2]
input_file = "./Data/03_data_no_header_row.csv"
output_file = "./Output/out08.csv"

with open(input_file,'r',newline='') as csv_in_file:
    with open(output_file,'w',newline='') as csv_out_file:
        filereader = csv.reader(csv_in_file)
        filewriter = csv.writer(csv_out_file)
        header_list = ['Supplier Name','Invoice Number',\
                       'Part Number','Cost','Purchase Date']
        
        filewriter.writerow(header_list)
        
        for row in filereader:
            filewriter.writerow(row)
            
# 헤더 먼저 한줄 쓰고, 그다음 for문 돌면서 데이터 입력
# '\' 뒤에 space가 있으면 Error 발생됨             