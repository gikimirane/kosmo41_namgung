# -*- coding: utf-8 -*-
"""
Created on Mon Dec 10 09:43:24 2018

@author: kosmo10
"""

# 딕셔너리
# 중괄호를 이용하여 딕셔너리 생성하기
# len()

empty_dict = {}

dict1 ={'a':3,'c':2,'b':1}
print(dict1)
print(len(dict1))

dict2 = {'x':'printer','y':5,'z':['star','circle',9]}
print(dict2)
print(len(dict2))

print("="*30)

# 딕셔너리 내 값 접근하기
# 키를 사용하여 딕셔너리 내 특정 값에 접근하기

print(dict1['c'])

# 복사하기
dict3 = dict1.copy()
print(dict3)

# 키, 값, 아이템
print(dict1.keys())
print(dict1.values())
print(dict1.items())

# in, not in, get 이용하기

if 'y' in dict2 :
    print("'y' is a key in dict2.",dict2)
if 'c' in dict2 : 
    print("'c' is not a key in dict2.",dict2)

print(dict1.get('c'))
print(dict1.get('d'))
print(dict1.get('d','Not in dict1'))

print("="*30)

# 정렬하기
dict4 = sorted(dict3.items(),key=lambda item:item[0])
# print(dict3)
print(dict4)

dict5 = sorted(dict3.items(),key=lambda item:item[1])
print(dict5)