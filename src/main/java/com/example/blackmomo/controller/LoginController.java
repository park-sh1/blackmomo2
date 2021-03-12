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

    /**
     * <p>회원가입 처리</p>
     * @param member
     * @return
     */
    @PostMapping("/signUp")
    public String signform(Member member) throws Exception{

        System.out.println("회원정보 ::: " + member.getUserId());
        System.out.println("회원정보 ::: " + member.getPass());
        System.out.println("회원정보 ::: " + member.getName());
        System.out.println("회원정보 ::: " + member.getZipCode());
        System.out.println("회원정보 ::: " + member.getBaseAddress());
        System.out.println("회원정보 ::: " + member.getDetailedAddress());
        System.out.println("회원정보 ::: " + member.getPhoneNumber());
        System.out.println("회원정보 ::: " + member.getGender());
        System.out.println("회원정보 ::: " + member.getDateOfBirth());
        System.out.println("회원정보 ::: " + member.getEmail());
        System.out.println("회원정보 ::: " + member.getEmailAllowingYn());
        System.out.println("개인정보동의 ::: " + member.getPerIAgreementYn());

        System.out.println("회원정보 ::: " + member.getInterest1());
        System.out.println("회원정보 ::: " + member.getInterest2());
        System.out.println("회원정보 ::: " + member.getInterest3());


        try{
            loginService.userInsert(member);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/";
        /*return "/login.html";*/
    }


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
    public String idCheckForm(){ return "/login/idCheckForm.html"; }

    /**
     * <p>아이디 중복 체크</p>
     * @param member
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    public int idCheck(Member member) throws  Exception{

        int result = loginService.idCheck(member);

        return result;
    }

    /**
     * <p>휴대폰 중복 체크</p>
     * @param member
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "phoneCheck", method = RequestMethod.POST)
    public int phoneCheck(@ModelAttribute Member member) throws Exception{

        int result = loginService.phoneCheck(member);

        return result;
    }
}
