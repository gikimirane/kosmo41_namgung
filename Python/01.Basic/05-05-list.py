# -*- coding: utf-8 -*-
"""
Created on Fri Dec  7 14:57:02 2018

@author: kosmo10
"""

# 리스트 만들기
# 리스트를 만들기 위해 대괄호를 사용한다.
# len(), min(), max(), count()

list1 = [1,2,3,3,4,5]
print(list1)
print(len(list1))
print(min(list1))
print(max(list1))
print(list1.count(3))

# 인덱스 사용하기
print(list1[1])
print(list1[-1])

# 리스트 분할하기
list2 = list1[0:2]
print(list2)

print("="*30)

# 리스트 복사하기
list2 = list1
list3 = list1[:]
list1[0] = 9

print(list2)
print(list3)

print("="*30)

# 리스트 병합하기
list4 = ["홍길동","전우치","손오공"]
list5 = list1 + list4
print(list5)

print("="*30)

# in과 not in
a = 2 in list1
print(a)
if 2 in list1 :
    print("2 is in list",list1)

b = 6 not in list1
print(b)

if 6 not in list1 :
    print("6 is not in list",list1)

print("="*30)


# append, remove, pop 함수들

list1.append(6)
list1.append(7)
print(list1)

list1.pop()     # 맨 뒤에꺼 버림
print(list1)

list1.remove(3)     #숫자 3을 버림
print(list1)

print("="*30)

#[9,2,3,4,5,6]
list1.insert(0,1)    # 0번째 자리에 1 추가
print(list1[1:2])
list1[1:2] = ['a','b','c']
print(list1)

list1[1:4] = []
print(list1)

del list1[5]    # 5번째 자리 삭
print(list1)
print("="*30)

# reverse 함수
# 해당 리스트 내에서 변경이 일어난다. ---> 인플레이스 변경

list6 = list1[:]
list6.reverse()
print("reverse : ",list6)

#sort 함수 : 인플레이스 정렬

list7 = list1[:]
print("list7:",list7)

list7.sort()
print("sort:",list7)

#sorted 함수
my_list = [[1,2,3,4],[4,3,2,1],[2,4,1,3]]
list8 = sorted(my_list,key=lambda index_value: index_value[1])
print(list8)












