# 변수
a <-1
b <-2
a+b

# 여러 값으로 구성된 변수 선언
var1 <- c(1,2,3,4,5)
var2 <- c(1:5)
var3 <- seq(1,5)
var4 <- seq(1,10, by = 3)

# 여러 값으로 구성된 변수 연산

# 1. 각각의 요소에 대해 2씩 더함
var1 + 2

# 2. 각각의 요소끼리 더함 (위치)
var1 + var2

# 3. 길이(자릿수)가 다르면 Error 발생
var1 + var4

# 문자로 된 변수 만들기
str1 <- "a"
str2 <- "test"
str3 <- c("I","am","young boy.")
str3

# 문자로 된 변수는 연산 불가 (문자+숫자 = Error)
str1 + 2
