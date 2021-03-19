package com.example.blackmomo.domain;

import lombok.Getter;
import lombok.Setter;

//회원 정보
@Setter
@Getter
public class Member {

    private int    userNo;          // 회원번호
    private String userId;          // 회원id
    private String pass;            // 패스워드(비밀번호)
    private String passCheck;       // 패스워드2(비밀번호확인)
    private String name;            // 이름
    private String nickname;        // 닉네임(별명)
    private String email;           // 이메일(최대 20자 제한)

    private String zipCode;         // 우편번호
    private String baseAddress;     // 주소
    private String detailedAddress; // 상세주소소

    private String phoneNumber;     // 휴대폰번호
    private String gender;          // 성별
    private String dateOfBirth;     // 생년월일
    private String interest1;       // 관심분야1
    private String interest2;       // 관심분야2
    private String interest3;       // 관심분야3

    private String registeredDate;  // 회원등록일시
    private String modifiedDate;    // 회원수정일시
    private String secessionDate;   // 회원탈퇴일시
    private String LastLoginDate;   // 마지막로그인일시
    private String mbLevel;         // 회원등급
    // 브론즈(기본등급, Bronze), 실버(silver), 골드(gold), 플래티넘(Platinum)

    private String emailAllowingYn; // 이메일수신여부
    private String perlAgreementYn; // 개인정보동의여부
    private String managerYn;       // 관리자여부
    private String certificationYn; // 인증여부
    private String useYn;           // 사용여부
    private String deleteYn;        // 삭제여부
}
