# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

#기본----------------------------
print(2+4)   #결과 숫자 연산
print("2+4") #결과 문자열 출력

#연산자--------------------------
print(2+4)
print(7-4)
print(7*4)
print(7/4)
print(2**3)     #제곱
print(7//4)     #몫
print(7%4)      #나머지
print(2*(3+4))

#변수----------------------------
a = 3   #변수 선언
a
b = 4

print("a + b =",a+b)
c = a+b
print("a + b =",c)

#for문---------------------------
for x in range(10) :
    print("Number :",x)

#range---------------------------
list(range(5))   # [0,1,2,3,4]
list(range(0,5)) # [0,1,2,3,4] 시작점, 끝점
list(range(1,4)) # [1,2,3] 시작은 같거나 크고 뒤는 작다

for x in range(1,4):
    print("Number:",x)  #for문 아래 같은 라인이면 반복에 포함됨