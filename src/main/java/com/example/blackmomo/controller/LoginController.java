package com.example.blackmomo.controller;

import com.example.blackmomo.domain.Member;
import com.example.blackmomo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * <p>로그인 화면으로</p>
     * @return
     */
    @GetMapping("/")
    public String login(){
        return "/login/login.html";
    }

    /**
     * <p>로그인 진행</p>
     */
    /*@PostMapping("/login")
    public login()*/

    /**
     * <p>회원가입 화면으로</p>
     * @return
     */
    @GetMapping("/signUp")
    public String signUp() { return "/login/signUp.html";}

    @PostMapping("/doubleCheck")
    public String doubleCheck(){
        return null;
    }


    /**
     * <p>id찾기 화면으로</p>
     * @return
     */
    @GetMapping("/idFind")
    public String idFind() {
        return "/login/idFind.html";
    }

    /**
     * <p>pw찾기 화면으로</p>
     * @return
     */
    @GetMapping("/pwFind")
    public String pwFind() {
        return "/login/idFind.html";
    }

    /**
     * <p>pw찾기 화면으로</p>
     * <p>id 찾기에서 바로 pw찾기로 넘어가는 경우</p>
     * @return
     */
    @GetMapping("/login/idFind")
    public String pwFind2() {
        return "/login/idFind.html";
    }

    @GetMapping("/idCheckForm")
    public String idCheckForm(){



        return "/login/idCheckForm.html";
    }

    /**
     * <p>아이디 중복 체크</p>
     * @param member
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    public int idCheck(Member member) throws  Exception{

        System.out.println("중복체크 서버에 도착 ::: " + member);

        int result = loginService.idCheck(member);

        System.out.println("result 값 확인 ::: " + result);

        return result;
    }
}
