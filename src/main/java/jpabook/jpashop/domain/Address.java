package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    //값 타입은 변경이 되면 안된다
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 엔티티나 임베디드 타입은
    // 자바 기본 생성자를 public 또는 protected로 설정해야 한다.
    // 기본생성자를 만들어줘야 한다.
    protected Address(){

    }

}
