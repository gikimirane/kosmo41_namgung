# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 12:06:56 2018

@author: kosmo10
"""

# format 문자열

print("{0}+{1}".format("Hello","홍길동"))

# 좌측정렬
x1 = "{0:<10}".format("hi")
print("[",x1,"]")
print("="*30)

# 우측정렬
x2 = "{0:<10}".format("hi")
print("[",x2,"]")
print("="*30)

# 중앙정렬
x3 = "{0:^10}".format("hi")
print("[",x3,"]")
print("="*30)

# 채우기
x4 = "{0:*>10}".format("hi")
print("[",x4,"]")
print("="*30)

#소수점 표현하기

y1 = 3.14159
y2 = "{0:10.2f}".format(y1)
print("[",y2,"]")


y3 = "{0:0.2f}".format(y1)
print("[",y3,"]")