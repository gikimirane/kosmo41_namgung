# 필요한 변수만 추출하기

# 변수 추출하기
# 반이랑 수학점수만 보여주기
exam %>% select(class,math)
exam %>% select(english)

# 변수 제외하기
# 수학점수만 제거
exam %>% select(-math)

#여러개 제거하기
exam %>% select(-math,-english)

#dplyr 함수 조합하기
exam %>% filter(class==1) %>% 
  select(class,english)
