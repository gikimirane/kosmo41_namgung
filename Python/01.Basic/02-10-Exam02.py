# -*- coding: utf-8 -*-
"""
Created on Thu Dec  6 14:52:55 2018

@author: kosmo10
"""

# 숫자를 추측해서 맞히는 프로그램

import random
x = 0
x = random.randint(1,30)
while True : 
    print("숫자를 맞혀보세요. 1~30")
    y=input()
    num1=int(x)
    num2=int(y)

    if num1==num2 :
        print("정답입니다.")
        print("답은 : ",num2)
        break
    if num1 > num2 :
        print("정답보다 작습니다.")

    if num1 < num2 :
        print("정답보다 큽니다.")