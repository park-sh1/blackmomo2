package com.example.blackmomo.service;

import com.example.blackmomo.domain.Member;
import com.example.blackmomo.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public int idCheck(Member member) throws Exception {

        System.out.println("serviceImpl 도착 ::: " +  member);

        return loginMapper.idCheck(member);
    }

    @Override
    public int phoneCheck(Member member) throws Exception {

        System.out.println("serviceImpl 도착 ::: " + member);

        return loginMapper.phoneCheck(member);
    }

    @Override
    public void userInsert(Member member) throws Exception {
        System.out.println("데이터 확인 ::: " + member.getPassCheck());
      loginMapper.userInsert(member);
    }

    @Override
    public Member login(Member member) throws Exception{
        return loginMapper.login(member);
    }
}
