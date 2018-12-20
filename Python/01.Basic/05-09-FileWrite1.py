# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 11:01:52 2018

@author: kosmo10
"""

# 파일 작성하기1
import sys
my_letters = ['동','해','물','과','백','두','산','이','마','르','고','닳','도','록']
max_index = len(my_letters)

#output_file = sys.argv[1]

output_file = input("File Name : ")
filewriter = open(output_file,'w')

for index_value in range(len(my_letters)):
    if index_value < (max_index-1):
        filewriter.write(my_letters[index_value]+'\t')
    else :
        filewriter.write(my_letters[index_value]+'\n')

filewriter.close()
print("Output written to file")