package com.oneQuest.oneQuest.domain.User;

import com.oneQuest.oneQuest.domain.enumType.Provider;
import com.oneQuest.oneQuest.domain.enumType.Role;
import com.oneQuest.oneQuest.domain.user.User;
import com.oneQuest.oneQuest.domain.user.UserCommunity;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import com.oneQuest.oneQuest.service.userService.UserCommunityService;
import com.oneQuest.oneQuest.service.userService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserCommunityTest {

    @Autowired
    UserService userService;

    @Autowired
    UserCommunityService userCommunityService;

    /**
     * <p>Create Date : [ 2022 - 06 - 01 ]</p>
     * <p>Update Date : [ 2022 - 06 - 01 ]</p>
     * <p>Follower Not Null Test</p>
     */
    @Test
    public void followerNullTest() {
        // given
        User user1 = User.builder()
                .id("gildong123")
                .name("홍길동")
                .password("Password1234!")
                .email("gildong123@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-1234-5678")
                .authentication(false)
                .nickname("gildong")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("jung123")
                .name("사오정")
                .password("Password4321!")
                .email("jung123@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-8765-4321")
                .authentication(false)
                .nickname("jung")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        userService.join(user1);
        userService.join(user2);

        UserCommunity userCommunity1 = UserCommunity.builder()
//                .follower_user(user1)
                .following_id(user2.getId())
                .build();

        // when
        try {
            userCommunityService.createUserCommunity(userCommunity1);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("Null 값이 있으므로 실행되면 안된다.");
    }    @Test

    /**
     * <p>Create Date : [ 2022 - 06 - 01 ]</p>
     * <p>Update Date : [ 2022 - 06 - 01 ]</p>
     * <p>Following Not Null Test</p>
     */
    public void followingNullTest() {
        // given
        User user1 = User.builder()
                .id("gildong123")
                .name("홍길동")
                .password("Password1234!")
                .email("gildong123@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-1234-5678")
                .authentication(false)
                .nickname("gildong")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        User user2 = User.builder()
                .id("jung123")
                .name("사오정")
                .password("Password4321!")
                .email("jung123@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-8765-4321")
                .authentication(false)
                .nickname("jung")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        userService.join(user1);
        userService.join(user2);

        UserCommunity userCommunity1 = UserCommunity.builder()
                .follower_user(user1)
//                .following_id(user2.getId())
                .build();

        // when
        try {
            userCommunityService.createUserCommunity(userCommunity1);
        } catch (Exception e) {
            return;
        }

        // then
        Assertions.fail("Null 값이 있으므로 실행되면 안된다.");
    }
}