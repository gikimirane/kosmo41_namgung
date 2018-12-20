# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 09:55:52 2018

@author: kosmo10
"""

# try ~ except
# ZeroDivisionError, IndexError, FileNotFoundError
# LookupError, KeyError, ValueError

# 숫자 리스트의 평균계산하기

def getMean(numbericValues):
    return sum(numbericValues)/len(numbericValues)


my_list = []

# 짧은 버전
try : 
    print(getMean(my_list))
except ZeroDivisionError as detail:
    print(detail)

print("="*30)

# 긴 버전

try : 
    result = print(getMean(my_list))
except ZeroDivisionError as detail: 
    print(detail)
else :
    print(result)
finally :
    print("The finally block is executed every time")
print("="*30)

# (시스템 종료를 제외한) 최상위 에러로 표현

try:
    print(getMean(my_list))
except Exception as detail :
    print(detail)