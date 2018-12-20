# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 11:51:38 2018

@author: kosmo10
"""

# +2씩
# range == list

for x1 in [1,3,5,7,9]:
    print(x1)
    
print("*" *30)

d = [x for x in range(10) if x % 2 == 0 ]

print(d)    

for x2 in d :
    print(x2)
    
print("*" *30)

# 한줄로 이어서 출력하기

for x3 in range(5):
    print(x3, end=" ")
    
# 구구단 출력하기

for x in range(2,10):
    for y in range (1,10):
        print(x,"*",y,"=",x*y)
    
    print("")