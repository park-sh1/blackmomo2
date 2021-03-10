package com.example.blackmomo.service;

import com.example.blackmomo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

public interface LoginService {

    @Autowired
    LoginServiceImpl loginServiceImpl = new LoginServiceImpl();

    // 아이디 중복체크
    public int idCheck(Member member) throws Exception;
}
