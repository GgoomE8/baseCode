package com.example.demo.entity;

import com.example.demo.enums.UserRoleEnum;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity(name="TB_MEMBER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "i_user", columnList = "member_id"))
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    @NonNull
    private String username;

    @Column(unique = true)
    @NonNull
    private String email;

    @Column
    @NonNull
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Board> boardList;

//    @OneToMany(fetch = FetchType.LAZY) //CaseCade (삭제시 같이 삭제)
//    @JoinColumn(name = "comment_id", insertable = false, updatable = false)
//    private List<Comment> comments;

    public Member(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
