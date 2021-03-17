package com.example.blackmomo.controller;

import com.example.blackmomo.domain.Member;
import com.example.blackmomo.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

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

        if (member.getCertificationYn() == null) {
            member.setCertificationYn("N");
        }

        try{
            loginService.userInsert(member);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/login/";
        /*return "/login.html";*/
    }

    //로그인 처리
    @RequestMapping(value="/loginForm", method = RequestMethod.POST)
    public String loginForm(Member member, HttpServletRequest request, RedirectAttributes rttr) throws Exception{

        logger.info("post login");



        HttpSession session = request.getSession();
        Member login = loginService.login(member);

        String re = null;
        
        if(login == null){
            session.setAttribute("member", null);
            rttr.addFlashAttribute("msg", false);
            re = "redirect:/login/";
        } else if (login != null){
            System.out.println("정보 확인 ::: " + member.getUserId());
            session.setAttribute("member", login);
            session.setAttribute("userId", login.getUserId());
            session.setAttribute("nickname", login.getNickname());
            rttr.addFlashAttribute("msg", "로그인 성공");
            re = "redirect:/";
        }
        return re;
    }

    // 로그아웃 처리
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {

        // 세션의 속성을 삭제하려면 session 객체의 removeAttribute() 메소드를 사용
        // 세션의 모든 속성을 삭제할 때는 session 객체의 invalidate() 메소드를 사용

        session.invalidate();
        return "redirect:/login/";
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
