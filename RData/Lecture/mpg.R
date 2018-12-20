
mpg <- as.data.frame(ggplot2::mpg)
mpg

mpg1 <- mpg


mpg1 <- rename(mpg1,city=cty)
mpg1 <- rename(mpg1,highway=hwy)

mpg1
# 데이터 앞부분 확인
head(mpg1)

str(mpg1)
# 데이터 뒷부분 확인
tail(mpg1)
# 데이터 뷰어 창에서 확인
View(mpg1)
# 행, 열 출력
dim(mpg1)
# 데이터 속성 확인
str(mpg)
# 요약 통계량 출력
summary(mpg)
# 도움말
?mpg