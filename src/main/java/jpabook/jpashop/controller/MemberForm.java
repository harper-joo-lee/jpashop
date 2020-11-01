package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "") //필수로 받을 값
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
