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
     * <p>Create Date : [ 2022 - 05 - 30 ]</p>
     * <p>Update Date : [ 2022 - 05 - 30 ]</p>
     * <p>UserCommunity 를 저장한다.</p>
     *
     * @param userCommunity UserCommunity Data
     * @return PK
     */
    public Long save(UserCommunity userCommunity) {
        em.persist(userCommunity);
        return userCommunity.getId();
    }

    /**
     * <p>Create Date : [ 2022 - 06 - 01 ]</p>
     * <p>Update Date : [ 2022 - 06 - 01 ]</p>
     * UserCommunity_Idx 로 유저의 관계 불러오기
     *
     * @param idx PK
     * @return UserCommunity 데이터
     */
    public UserCommunity findByIdx(Long idx) {
        return em.find(UserCommunity.class, idx);
    }
}
