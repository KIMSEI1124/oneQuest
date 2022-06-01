package com.oneQuest.oneQuest.service.userService;

import com.oneQuest.oneQuest.domain.enumType.Provider;
import com.oneQuest.oneQuest.domain.enumType.Role;
import com.oneQuest.oneQuest.domain.user.User;
import com.oneQuest.oneQuest.domain.user.UserCommunity;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class UserCommunityServiceTest {

    @Autowired
    UserCommunityService userCommunityService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    @Rollback(value = false)
    public void 유저의관계() {
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

        User user3 = User.builder()
                .id("sam123")
                .name("삼장법사")
                .password("Password1234!")
                .email("sam123@gmail.com")
                .provider(Provider.Google)
                .phone_number("010-5678-1234")
                .authentication(false)
                .nickname("sam")
                .role(Role.USER)
                .create_data(LocalDateTime.now())
                .build();

        userService.join(user1);
        userService.join(user2);
        userService.join(user3);

        UserCommunity userCommunity1 = UserCommunity.builder()
                .follower_user(user1)
                .following_id(user2.getId())
                .build();

        UserCommunity userCommunity2 = UserCommunity.builder()
                .follower_user(user2)
                .following_id(user1.getId())
                .build();

        UserCommunity userCommunity3 = UserCommunity.builder()
                .follower_user(user3)
                .following_id(user1.getId())
                .build();

        // when
        userCommunityService.createUserCommunity(userCommunity1);
        userCommunityService.createUserCommunity(userCommunity2);
        userCommunityService.createUserCommunity(userCommunity3);

        // then
        assertEquals(user1.getId(), userCommunity1.getUser().getId(),"follower_user_Id가 동일하여야 한다.");
        assertEquals(user2.getId(), userCommunity1.getFollowing(),"following_user_Id가 동일하여야 한다.");
        assertEquals(user1.getFollower(),2,"addFollower logic이 작동해야 한다.");
        assertEquals(user3.getFollowing(),1,"addFollowing logic이 작동해야 한다.");
    }
}