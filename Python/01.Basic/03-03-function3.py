# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 09:33:43 2018

@author: kosmo10
"""

import math

# 제곱 구하기

def getSquare(num):
    return num*num


def getTriangleArea(a,h):
    return a*h/2


def getSqrt(num):
    return math.sqrt(num)

x1 = 4
x2 = getSquare(x1)
print(x1,x2)

print(getTriangleArea(3,4))
print(getSqrt(4))
print(getSqrt(9))