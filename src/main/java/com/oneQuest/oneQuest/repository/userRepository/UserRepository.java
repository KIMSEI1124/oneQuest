package com.oneQuest.oneQuest.repository.userRepository;

import com.oneQuest.oneQuest.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    /**
     * <p>Create Date : [ 2022 - 05 - 25 ]</p>
     * <p>Last Update Date : [ 2022 - 05 - 25 ]</p>
     * <p>전체 유저의 정보를 리스트로 가져옴</p>
     *
     * @return 전체 유저의 데이터
     */
    public List<User> findAll() {
        return em.createQuery("select U from  User as U", User.class)
                .getResultList();
    }

    /**
     * <p>Create Date : [ 2022 - 05 - 25 ]</p>
     * <p>Last Update Date : [ 2022 - 05 - 25 ]</p>
     * 유저의 id로 기존에 가입한 유저를 조회한다.
     *
     * @param id 유저의 id
     * @return 유저의 정보를 담은 리스트 (size = 0 or 1)
     */
    public List<User> findByIdList(String id) {
        return em.createQuery("select U from User as U where U.id =: id", User.class)
                .setParameter("id", id)
                .getResultList();
    }
}

