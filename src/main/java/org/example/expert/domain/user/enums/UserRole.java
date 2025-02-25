package org.example.expert.domain.user.enums;

import org.example.expert.domain.common.exception.InvalidRequestException;

import java.util.Arrays;

public enum UserRole {
    ADMIN, USER;

    public static UserRole of(String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException("유효하지 않은 UerRole"));

        // name(): Java enum 에 있는 내장 메서드
        // 쉽게 말해 UserRole 이라는 enum 클래스에 포함된 이넘값(name)을 스트림화 시키고, 들어온 String role 변수값과 비교함.
        // equalsIgnoreCase 이건 대소문자 무시해주는 것 => 들어온 String role 변수의 대소문자를 무시하고 비교하게 해줌.

        // .findFirst(): 필터링된 요소중 첫번째 요소를 선택함. (요소가 비어있다면 `[]` 무시됨)
        // 왜 .findFirst()를 쓸까? => 일반적으로는 필터링 된 값이 1개 or 0개임 (ex `[USER]`, `[]`)
        // => 그래서 일반적으로는 굳이 필요 없음.
        // 하지만 유지 보수 측면을 생각해 본다면 안정성, 확장 가능성을 고려하면 .findFirst() 필요
        // 실수로 이넘값이 중복이라던가 `ADMIN, USER, ADMIN;` => `[ADMIN, ADMIN]` 등...

        // 스트림 결과 값을 Optional (null 도 다룰 수 있게끔 하기 위해) 객체로 만들어서 내보내고,
        // Optional 이 비어 있으면, .orElseThrow(() -> new InvalidRequestException("유효하지 않은 UerRole")); 으로 가서 예외처리.
    }
}
