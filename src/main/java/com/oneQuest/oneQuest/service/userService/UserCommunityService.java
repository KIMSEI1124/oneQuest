package com.oneQuest.oneQuest.service.userService;

import com.oneQuest.oneQuest.domain.user.UserCommunity;
import com.oneQuest.oneQuest.repository.userRepository.UserCommunityRepository;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommunityService {
    private final UserCommunityRepository userCommunityRepository;
    private final UserRepository userRepository;

    /**
     * <p>Create Date : [ 2022 - 06 - 01 ]</p>
     * <p>Update Date : [ 2022 - 06 - 01 ]</p>
     * <p>유저들의 관계를 생성한다</p>
     *
     * @param userCommunity 유저의 관계 데이터
     * @return PK
     */
    public Long createUserCommunity(UserCommunity userCommunity) {
        Long saveIdx = userCommunityRepository.save(userCommunity);
        userCommunity.getUser().addFollowing();
        userRepository.findById(userCommunity.getFollowing()).addFollower();
        return saveIdx;
    }
}
