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
}
