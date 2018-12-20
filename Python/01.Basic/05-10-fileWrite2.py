# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 11:09:21 2018

@author: kosmo10
"""

import sys
import glob
import os

# 파일 작성하기 2 : csv

my_numbers = [0,1,2,3,4,5,6,7,8,9]
max_index = len(my_numbers)
print("max_index",max_index)

#output_file = sys.argv[1]
output_file = input("File Name :")
filewriter = open(output_file,'a')

for index_value in range(len(my_numbers)):
    # 마지막 문자인지 아닌지 체크하기 위한 if문, 마지막 문자가 아니면 ,
    if index_value < (max_index-1):
        # str = 숫자를 문자로 바꿈 / 반대 = int
        filewriter.write(str(my_numbers[index_value])+',')
    else :
        filewriter.write(str(my_numbers[index_value])+'\n')
filewriter.close()

print("output appended to file")

#파일 작성하기3 : csv
#숫자일 경우 join이 안된다.

#my_numbers = [0,1,2,3,4,5,6,7,8,9]
my_numbers =["0","1","2","3","4","5","6","7","8","9"]
max_index = len(my_numbers)
my_string = ",".join(my_numbers)
print(my_string, type(my_string))


#output_file = sys.argv[1]
output_file = input("File Name : ")
filewriter = open(output_file,'a')
# filewriter.write(my_string+'\n')   writelines와 다른 차이 없음
filewriter.writelines(my_string)     #문장 끝에 엔터가 없음
#filewriter.writelines(my_string+'\n')
filewriter.close()

print("Output appended to file")