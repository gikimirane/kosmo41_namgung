# 순서대로 정렬하기

# math 오름차순 정렬
exam %>% arrange(math)

# math 내림차순 정렬
exam %>% arrange(desc(math))

# class 는 오름차순, math는 내림차순
exam %>% arrange(class,desc(math))
