library(ggplot2)
library(dplyr)
#데이터 프레임 만들기 
df_var_derived <- data.frame(var51 = c(4,3,8),
                             var52 = c(2,6,1))

df_var_derived

#합계를 구하는 파생 변수 
df_var_derived$var_sum <- df_var_derived$var51 + df_var_derived$var52
df_var_derived$var_mean <- (df_var_derived$var51+df_var_derived$var52)/2

mpg1$total <- (mpg1$city+mpg1$highway)/2
head(mpg1)

# 조건문을 이용해 파생 변수 만들기
summary(mpg1$total)
hist(mpg1$total)

mpg1$test<-ifelse(mpg1$total,"pass","fail")

head(mpg1)
qplot(mpg1$test)

mpg1$grade <- ifelse(mpg1$total>=30,"A",
                     ifelse(mpg1$total>=20,"B","C"))

# 등급 빈도표 생성
table(mpg1$grade)
# 등급 빈도 막대 그래프 생성
qplot(mpg1$grade)

mpg1$grade2 <- ifelse(mpg1$total>=30,"A",
                      ifelse(mpg1$total>=25,"B",
                             ifelse(mpg1$total>=20,"C","D")))

table(mpg1$grade2)
qplot(mpg1$grade2)
