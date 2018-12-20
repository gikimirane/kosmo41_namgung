library(ggplot2)

#데이터 프레임 생성
df_var_change1 <- data.frame(var51= c(1,2,3),
                             var52=c(2,3,2))

install.packages("dplyr")
library(dplyr)

# 복사
df_var_change2 <- df_var_change1
df_var_change2

df_var_change2 <- rename(df_var_change2,v2=var52)
df_var_change2

# 변수명 바꾸면서 새로운 데이터 프레임 만들기
df_var_change3 <- rename(df_var_change1,v2=var52)

mpg <- as.data.frame(ggplot2::mpg)
mpg

mpg1 <- mpg

mpg1 <- rename(mpg1,city=cty)
mpg1 <- rename(mpg1,highway=hwy)

mpg1
head(mpg1)
str(mpg1)
