# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 09:42:18 2018

@author: kosmo10
"""

# 1부터 n까지의 합을 구하는 함수 만들고 호출하기



def sum_func(num):
    sum=0
    n = int(num)  
    for x in range(1,n+1) :
        sum = sum+x
        x = x+1
    return sum

print(sum_func(10))