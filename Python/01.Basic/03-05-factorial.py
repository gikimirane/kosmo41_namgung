# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 09:52:38 2018

@author: kosmo10
"""
# n을 입력받아 팩토리얼 함수 적용하기

def sum_func(num):
    sum = 1
    n = int(num)  
    for x in range(1,n+1) :
        sum = sum * x
    return sum

print(sum_func(5))