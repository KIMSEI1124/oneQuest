package com.oneQuest.oneQuest.domain.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <p>Create Date : [ 2022 - 05 - 25 ]</p>
 * <p>Update Date : [ 2022 - 05 - 25 ]</p>
 * <p>User_Community as UC</p>
 * <p>유저들의 커뮤니티 관계</p>
 */
@Entity
@Getter
@NoArgsConstructor
public class UserCommunity {

    /**
     * <p>idx ( PK ) : index</p>
     * <p>user_id ( FK ) : 유저의 id</p>
     * <p>follower ( NN ) : 팔로워 유저의 id</p>
     * <p>following ( NN ) : 팔로윙 유저의 id</p>
     */
    @Id
    @GeneratedValue
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String follower;

    @Column(nullable = false)
    private String following;
}
