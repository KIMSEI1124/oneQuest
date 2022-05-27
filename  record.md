##[ 2022 - 05 - 25 ]  
### Entity  
User 생성  
User_Community : 생성 -> UserCommunity 이름 변경   

### Repository
UserRepository : save 생성, findByIdList, findAll 생성

### Service  
UserService : id 검증, 중복 회원가입 검증, 회원가입 생성  

### Exception
IdException : 생성    

### Test
UserServiceTest : 생성

--- 

##[ 2022 - 05 - 27 ]  

### Test
UserServiceTest : 단위 테스트마다 실행후 DB 초기화 성공  
-> @Transactional 을 붙여주면 됨