exam <- read.csv("../RData/csv_exam.csv")
exam
head(exam)

# 단축키 ctrl+shift+m = %>% 
# exam 에서 class가 1인 경우만 추출하여 출력
exam %>% filter(class==1)

# 2반인 경우만 추출한다면?
exam %>% filter(class==2)

# 1반이 아닌 경우만 추출
exam %>% filter(class!=1)

# 3반이 아닌 경우만 추출
exam %>% filter(class!=3)

# 3반보다 작은 경우
exam %>% filter(class<3)

# 초과, 미만, 이상, 이하 조건 달기
# >     <      >=    <=

# 수학점수가 50점 초과한 경우
exam %>% filter(math > 50)

# 수학점수가 50점 미만인 경우
exam %>% filter(math < 50)

# 영어점수가 80점 이상인 경우
exam %>% filter(english >=80)

# 영어점수가 80점 이하인 경우
exam %>% filter(english <=80)


# and - 여러 조건을 충족하는 행 추출하기
# 1반이면서 수학점수가 50점 이상인 경우
exam %>% filter(class==1 & math>=50)

# 2반이면서 영어 점수가 80점 이상인 경우
exam %>% filter(class==2 & english>=50)

# or - 여러 조건 중 하나 이상 충족하는 행 추출
# 수학점수가 90점 이상이거나, 영어점수가 90점 이상인 경우
exam %>% filter(math>=90 | english>=90)

# 영어 점수가 90점 미만이거나, 과학점수가 50점 미만인 경우
exam %>% filter(english<90 | science<50)

# 목록에 해당하는 행 추출하기
exam %>% filter(class==1 | class==3 | class==5)

# in기호 활용
exam %>% filter(class %in% c(1,3,5))

class1 <- exam %>% filter(class==1)
class2 <- exam %>% filter(class==2)

# 1반 수학 평균
mean(class1$math)
