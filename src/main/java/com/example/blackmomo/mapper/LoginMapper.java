package com.example.blackmomo.mapper;

import com.example.blackmomo.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    // 아이디 중복체크
    public int idCheck(Member member) throws Exception;

    public int phoneCheck(Member member) throws Exception;

    // 회원가입 처리
    public void userInsert(Member member) throws Exception;

}
