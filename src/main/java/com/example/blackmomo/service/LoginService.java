package com.example.blackmomo.service;

import com.example.blackmomo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

public interface LoginService {

    @Autowired
    LoginServiceImpl loginServiceImpl = new LoginServiceImpl();

    // 아이디 중복체크
    public int idCheck(Member member) throws Exception;

    // 휴대폰 번호 중복체크
    int phoneCheck(Member member) throws Exception;

    // 회원가입 처리
    void userInsert(Member member) throws Exception;

    Member login(Member member) throws Exception;

    Member idFind(Member member);

    Member pwFindForm(Member member);

    void pwChange(Member member);

    // 회원 정보 수정

    // 회원 탈퇴

    //
}
