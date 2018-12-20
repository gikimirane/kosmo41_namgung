# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 11:30:18 2018

@author: kosmo10
"""

# 소인수분해
# 12를 소인수분해 하면 2*2*3

x = int(input("?"))

d = 2           #가장 작은 소수인 2부터 나눕니다.
while d<=x :
    if x % d ==0:
        print(d)        #d는 x의 약수이므로 출력
        x = x / d       #x를 d로 나눠서 다시 x에 저장 
    else :
        d = d + 1       # 나누어 지지 않으면 1을 더해서 반복 