package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Table(name= "user") -> 이렇게 구현을 안하면 엔티티의 필드명을 그대로 테이블 명으로 사용
@Entity
@Getter @Setter
public class Member {

    @Id  @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    //내장 타입
    @Embedded
    private Address address;

    // 컬렉션은 필드에서 바로 초기화 하는것이 안전하다.
    // 일대 다 orderder
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
