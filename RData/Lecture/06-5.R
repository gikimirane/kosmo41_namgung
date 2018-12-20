# 파생변수 추가하기
exam %>% 
  mutate(total=math+english+science) %>% 
  head

exam %>% 
  mutate(total=math+english+science,
         mean=(math+english+science)/3 ) %>% head


# mutate()에 ifelse() 적용하기
exam %>% 
  mutate(test=ifelse(science>=60,"pass","fail")) %>% 
  head
