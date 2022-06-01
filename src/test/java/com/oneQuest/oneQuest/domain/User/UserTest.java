package com.oneQuest.oneQuest.domain.User;

import com.oneQuest.oneQuest.domain.enumType.Provider;
import com.oneQuest.oneQuest.domain.enumType.Role;
import com.oneQuest.oneQuest.domain.user.User;
import com.oneQuest.oneQuest.exception.IdException;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import com.oneQuest.oneQuest.service.userService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserTest {
    @Autowired
    UserRepository ur;

    @Autowired
    UserService us;

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 id Notnull 테스트 [ + ]
     */
    @Test
    public void IdNullTest() {
        // given
        User user = User.builder()
//                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (NullPointerException e) {
            return;
        }

        // then
        Assertions.fail("Id가 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 id Unique 테스트 [ + ]
     */
    @Test
    public void IdUniqueTest() {
        // given
        User user1 = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test1")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test2")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        us.join(user1);
        try {
            us.join(user2);
        } catch (IdException e) {
            return;
        }

        // then
        Assertions.fail("Id가 중복되므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 PW Null 테스트 [ + ]
     */
    @Test
    public void PwNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
//                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("PW가 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 Email Notnull 테스트 [ + ]
     */
    @Test
    public void EmailNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
//                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("Email 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 Email Unique 테스트 [ - ]
     */
    @Test
    public void EmailUniqueTest() {
        // given
        User user1 = User.builder()
                .id("gildong1234")
                .password("Password1234!")
                .email("gildong3@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test3")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("gildong4321")
                .password("Password1234!")
                .email("gildong3@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test4")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        Long user1_idx = us.join(user1);
        System.out.println("user1_email = " + ur.findByIdx(user1_idx).getEmail());
        try {
            Long user2_idx = us.join(user2);
            System.out.println("user2_email = " + ur.findByIdx(user2_idx).getEmail());
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("Email 이 중복되므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 Name Notnull 테스트 [ + ]
     */
    @Test
    public void NameNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
//                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("Name 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 phone_number Notnull 테스트 [ + ]
     */
    @Test
    public void phone_numberNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
//                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("phone_number 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 provider Notnull 테스트 [ + ]
     */
    @Test
    public void providerNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
//                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("provider 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 authentication Notnull 테스트 [ - ]
     */
    @Test
    public void authenticationNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
//                .authentication(false)
                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("authentication 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 nickname Notnull 테스트 [ + ]
     */
    @Test
    public void nicknameNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
//                .nickname("test")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("nickname 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 nickname Unique 테스트 [ - ]
     */
    @Test
    public void nicknameUniqueTest() {
        // given
        User user1 = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong1@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test1")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("gildong321")
                .password("Password1234!")
                .email("gildong2@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test2")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();


        // when
        try {
            us.join(user1);
        } catch (Exception e) {
            us.join(user2);
            return;
        }

        // then
        Assertions.fail("nickname 이 중복되므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 role Notnull 테스트 [ + ]
     */
    @Test
    public void roleNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
//                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("role 이 없으므로 실행되면 안됩니다.");
    }

    /**
     * Create Date : [ 2022 - 05 - 27 ] <p>
     * Last Update Date : [ 2022 - 05 - 27 ] <p>
     * 유저 create_date Notnull 테스트 [ + ]
     */
    @Test
    public void create_dateNullTest() {
        // given
        User user = User.builder()
                .id("gildong123")
                .password("Password1234!")
                .email("gildong@gmail.com")
                .name("홍길동")
                .phone_number("010-1234-5678")
                .provider(Provider.Google)
                .authentication(false)
                .nickname("test")
                .role(Role.USER)
//                .create_data(LocalDateTime.now())
                .build();

        // when
        try {
            us.join(user);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("create_date 가 없으므로 실행되면 안됩니다.");
    }
}
