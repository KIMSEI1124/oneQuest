package com.oneQuest.oneQuest.repository.userRepository;

import com.oneQuest.oneQuest.domain.user.User;
import com.oneQuest.oneQuest.domain.user.UserCommunity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCommunityRepository {
    private final EntityManager em;

    /**
     * <p>[ Create Date : 2022 - 05 - 30 ]</p>
     * <p>[ Update Date : 2022 - 05 - 30 ]</p>
     * <p>UserCommunity를 저장한다.</p>
     * @param userCommunity
     * @return PK
     */
    public Long save(UserCommunity userCommunity) {
        em.persist(userCommunity);
        return userCommunity.getIdx();
    }

    /**
     * <p>[ Create Date : 2022 - 06 - 01 ]</p>
     * <p>[ Update Date : 2022 - 06 - 01 ]</p>
     * UserCommunity_Idx 로 유저의 관계 불러오기
     * @param idx PK
     * @return  UserCommunity 데이터
     */
    public UserCommunity findByIdx(Long idx) {
        return em.find(UserCommunity.class, idx);
    }

}
