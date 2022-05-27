package com.oneQuest.oneQuest.service.userService;

import com.oneQuest.oneQuest.domain.entity.enumType.Provider;
import com.oneQuest.oneQuest.domain.entity.enumType.Role;
import com.oneQuest.oneQuest.domain.entity.user.User;
import com.oneQuest.oneQuest.exception.IdException;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Transactional  // 테스트 실행후 초기화를 위해서
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserRepository ur;

    @Autowired
    private UserService us;

    @Autowired
    private EntityManager em;

    /**
     * Create Date : [ 2022 - 05 - 25 ] <p>
     * Last Update Date : [ 2022 - 05 - 25 ] <p>
     * 유저 회원가입 테스트 [ + ]
     */
    @Test
    @DisplayName("User_Join_Test")
    public void 유저회원가입() {
        // given
        User user = User.builder()
                .id("gildong123")
                .name("홍길동")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-1234-5678")
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        Long saveIdx = us.join(user);

        // then
        Assertions.assertEquals(user.getId(), ur.findById(saveIdx).getId());
    }

    /**
     * Create Date : [ 2022 - 05 - 25 ] <p>
     * Last Update Date : [ 2022 - 05 - 25 ] <p>
     * 유저 아이디 중복 유효성 테스트 [ + ]
     */
    @Test
    @Transactional
    @DisplayName("User_Join_IdValidDuplicate_Test")
    public void 유저아이디중복유효성검사() {
        // given
        User user1 = User.builder()
                .id("gildong123")
                .name("홍길동")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-1234-5678")
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("gildong123")
                .name("홍길동")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-1234-5678")
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        us.join(user1);
        try {
            us.join(user2);
        } catch (IdException idE) {
            return;
        }

        // then
        Assertions.fail("예외처리가 되어야 합니다.");
    }
}