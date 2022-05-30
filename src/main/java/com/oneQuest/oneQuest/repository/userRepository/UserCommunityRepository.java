package com.oneQuest.oneQuest.repository.userRepository;

import com.oneQuest.oneQuest.domain.user.UserCommunity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserCommunityRepository {
    private final EntityManager em;

    /**
     * <p>[ Create Date : 2022 - 05 - 30 ]</p>
     * <p>[ Update Date : 2022 - 05 - 30 ]</p>
     * <p>UserCommunity를 저장한다.</p>
     * @param userCommunity
     * @return
     */
    public Long save(UserCommunity userCommunity) {
        em.persist(userCommunity);
        return userCommunity.getIdx();
    }
}
