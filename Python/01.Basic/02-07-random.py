# -*- coding: utf-8 -*-
"""
Created on Thu Dec  6 14:38:57 2018

@author: kosmo10
"""

import random
a = random.randint(1,30)
b = random.randint(1,30)

# 무작위로 덧셈 문제를 만들어서 맞히는 프로그램

print(a,"+",b," = ")
x = input()
c = int(x)

if a+b == c:
    print("정답입니다.")
else :
    print("오답입니다.")