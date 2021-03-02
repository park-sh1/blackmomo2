package com.example.blackmomo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

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
}
