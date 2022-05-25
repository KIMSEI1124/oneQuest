package com.oneQuest.oneQuest.repository;

import com.oneQuest.oneQuest.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    /**
     * Create Date : [ 2022 - 05 - 25 ] <p>
     * Last Update Date : [ 2022 - 05 - 25 ] <p>
     * 기능 : 유저의 정보를 저장
     */
    public Long save(User user) {
        em.persist(user);
        return user.getIdx();
    }


}

