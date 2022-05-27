package com.oneQuest.oneQuest.service.userService;

import com.oneQuest.oneQuest.domain.entity.user.User;
import com.oneQuest.oneQuest.exception.IdException;
import com.oneQuest.oneQuest.repository.userRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository UR;

    /**
     * <p>Create Date : [ 2022 - 05 - 25 ]</p>
     * <p>Last Update Date : [ 2022 - 05 - 25 ]</p>
     * <p>유저 회원가입</p>
     *
     * @param user 유저의 데이터
     * @return 유저의 생성 번호
     */
    @Transactional
    public Long join(User user) {
        verifyId(user); // Id 형식 확인
        validateDuplicateUser(user);    // 중복 유저 확인
        UR.save(user);
        return user.getIdx();
    }

    /**
     * <p>Create Date : [ 2022 - 05 - 25 ]</p>
     * <p>Last Update Date : [ 2022 - 05 - 25 ]</p>
     * <p>중복 가입 방지</p>
     * @param user 유저의 데이터
     */
    private void validateDuplicateUser(User user) {
        List<User> findUsers = UR.findByIdList(user.getId());
        // 이미 회원이 존재하면
        if (!findUsers.isEmpty()) {
            throw new IdException("이미 존재하는 유저입니다.");
        }
    }

    /**
     * <p>Create Date : [ 2022 - 05 - 25]</p>
     * <p>Update Date : [ 2022 - 05 - 25]</p>
     * <p>유저의 id 형식에 대한 확인</p>
     * @param user 유저의 데이터
     */
    private void verifyId(User user) {
        String saveId = user.getId();
        // Id의 길이를 검사한다. 허용 범위 5 ~ 20
        if (saveId.length() < 5 || saveId.length() > 20) {
            throw new IdException("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
        }
        // Id의 문자열을 검사한다. 허용 문자 영어 소문자, 특수기호 [_],(-)
        //FIXME
//        if (!saveId.contains("^[a-z]")) {
//            throw new IdException("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
//        }
    }
}
