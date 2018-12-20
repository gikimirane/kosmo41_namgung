# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 11:05:34 2018

@author: kosmo10
"""

# 집합연산을 하는 프로그램

A = {1,2,3,4}
B = {3,4,5,6}

print(A)
print(B)

print(1 in A)
print(6 in B)
print(len(A))

print(A | B)
print(A & B)
print(A - B)

print("=" * 30)

# x는 1부터 10까지의 수를 요소로 가진다

C = {x for x in range(1,11)}
D = {x for x in range(1,11) if x % 3 ==0}

print(C)
print(D)

print(C<D)
print(C>D)