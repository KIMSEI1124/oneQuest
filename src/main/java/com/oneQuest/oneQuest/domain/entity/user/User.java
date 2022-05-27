package com.oneQuest.oneQuest.domain.entity.user;

import com.oneQuest.oneQuest.domain.entity.enumType.Provider;
import com.oneQuest.oneQuest.domain.entity.enumType.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Create Date : [2022 - 05 - 25]</p>
 * <p>Update Date : [2022 - 05 - 25]</p>
 * <p>User as U</p>
 * <p>유저의 데이터를 담은 테이블</p>
 */
@Entity
@Getter
@NoArgsConstructor
public class User {
    /**
     * <p>[Auth]</p>
     * <p>idx ( PK ) : index</p>
     * <p>id ( NN, UQ ) : 유저의 id</p>
     * <p>password ( NN ) : 유저의 pw</p>
     * <p>email ( NN, UQ ) : 유저의 email</p>
     * <p>name ( NN ) : 유저의 name</p>
     * <p>phone_number ( NN ) : 유저의 전화번호</p>
     * <p>provider ( NN ) : 유저의 가입경로</p>
     * <p>authentication ( NN ) : 유저의 이메일 인증 유무</p>
     */
    @Id
    @GeneratedValue
    private Long idx;

    @Column(name = "user_id", nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(nullable = false)
    private boolean authentication;

    /**
     * <p>[Profile]</p>
     * <p>nickname : 유저의 별칭</p>
     * <p>header : 유저의 소갯말</p>
     * <p>image : 유저의 프로필 이미지</p>
     * <p>role ( NN ) : 유저의 역활</p>
     */
    private String nickname;
    private String header;
    private String image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * <p>[Timestamp]</p>
     * <p>create_data ( NN ) : 유저의 생성일</p>
     * <p>update_date : 유저의 정보 수정일</p>
     * <p>delete_date : 유저의 탈퇴일</p>
     */
    @Column(nullable = false)
    private LocalDateTime create_data;
    private LocalDateTime update_data;
    private LocalDateTime delete_data;

    /**
     *  [ Relationship ]
     */
    @OneToMany(mappedBy = "user")
    private List<UserCommunity> userCommunityList;

    /**
     * <p> Create Date : [ 2022 - 05 - 25 ]</p>
     * <p> Update Date : [ 2022 - 05 - 25 ]</p>
     * <p>유저의 회원 가입 생성자</p>
     */
    @Builder
    public User(String id, String password, String email, String name, String phone_number, Provider provider,
                boolean authentication, String nickname, Role role, LocalDateTime create_data) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.provider = provider;
        this.authentication = authentication;
        this.nickname = nickname;
        this.role = role;
        this.create_data = create_data;
    }
}