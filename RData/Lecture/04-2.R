
english <- c(90,80,60,70)
math <- c(50,60,100,20)

df_midterm <- data.frame(english,math)
df_midterm

# 데이터 프레임에 데이터 추가
class<-c(1,1,2,2)
name <-c("홍길동","전우치","손오공","해리포터")

df_midterm<-data.frame(english,math,class,name)
df_midterm

#데이터 분석하기
mean(df_midterm$english)
mean(df_midterm$math)
max(df_midterm$math)
min(df_midterm$english)

#데이터 프레임 한번에 만들기
df_midterm2 <- data.frame(english2=c(90,80,60,70),
                          math2=c(50,60,100,20),
                          class2=c(1,1,2,2),
                          name2=c("홍길동","전우치","손오공","해리포터")
                          )

# 주의! 데이터 프레임 안에서 만든 지역변수라서 밖에서는 쓸 수 없다. 단독 사용 시 Error 발생!
# 별도의 변수 객체로 지정되지 않았다. 데이터 프레임에서 컬럼으로만 사용 됨. 
english2

