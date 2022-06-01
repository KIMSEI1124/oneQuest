package com.oneQuest.oneQuest.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCommunity {

    /**
     * <p>idx ( PK ) : index</p>
     * <p>user_id ( FK ) : 유저의 id</p>
     * <p>follower ( NN ) : 팔로워 유저의 id</p>
     * <p>following ( NN ) : 팔로윙 유저의 id</p>
     */
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_user_id")
    private User user;

    @Column(nullable = false)
    private String following;

    @Builder
    public UserCommunity(User follower_user, String following_id) {
        this.user = follower_user;
        this.following = following_id;
    }
}
