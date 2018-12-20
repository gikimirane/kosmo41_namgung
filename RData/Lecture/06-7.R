#데이터 합치기

# 중간고사 데이터 생성
test1 <- data.frame(id=c(1,2,3,4,5),
                    midterm=c(60,80,70,90,85) )

# 기말고사 데이터 생성
test2 <- data.frame(id=c(1,2,3,4,5),
                    final =c(70,83,65,95,80) )

total <-left_join(test1,test2, by ="id")
total
# 기준이 되는 id는 동일해야 한다. id가 틀리면 결과로 확인
# na가 됨

# 기존 데이터에 선생님 데이터 추가하기
name <- data.frame(class=c(1,2,3,4,5),
                   teader=c("kim","lee","park","choi","jung"))

exam_new <- left_join(exam,name,by="class")
exam_new

## 세로로 데이터 합치기
# 학생 1~5번 시험 데이터 생성
group_a <- data.frame(id=c(1,2,3,4,5),
                      test=c(60,80,70,90,85) )
group_b <- data.frame(id=c(6,7,8,9,10),
                      test=c(70,83,65,95,80))


group_all <- bind_rows(group_a,group_b)
group_all

# 두 데이터를 세로로 합칠 때는 두 데이터의 변수명이 같아야 함
# 다르면 rename이용해서 동일하게 맞춘 후 합치면 된다.