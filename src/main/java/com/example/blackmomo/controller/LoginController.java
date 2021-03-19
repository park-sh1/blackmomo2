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

    /**
     * <p>id찾기 화면으로</p>
     * @return
     */
    @GetMapping("/idFind")
    public String idFind() { return "/login/idFind.html"; }

    @RequestMapping(value="/idFind", method = RequestMethod.POST)
    public String idFindForm(Member member, RedirectAttributes rttr, Model model) {

        System.out.println("name ::: " + member.getName() + "email ::: " + member.getEmail());

        Member userId = loginService.idFind(member);

        String re = null;

        if(userId == null){
            rttr.addFlashAttribute("msg", false);
            re = "redirect:/login/idFind";
        } else if ( userId != null){
            rttr.addFlashAttribute("msg", true);
            model.addAttribute("member", userId);
            re = "/login/idFind2.html";
        }

        return re;
    }

    /**
     * <p>pw찾기 화면으로</p>
     * @return
     */
    @GetMapping("/pwFind")
    public String pwFind() {
        return "/login/pwFind.html";
    }

    /**
     * <p>비밀번호 찾기 처리</p>
     * @param member
     * @param rttr
     * @param model
     * @return
     */
    @RequestMapping(value = "/pwFind", method = RequestMethod.POST)
    public String pwFindForm(Member member, RedirectAttributes rttr, Model model) {

        System.out.println("id ::: " + member.getUserId() + ", name ::: " + member.getName() + ", email ::: " + member.getEmail());

        Member pass = loginService.pwFindForm(member);

        System.out.println("비번확인 ::: " + pass.getPass());

        String re = null;

        if(pass == null){
            rttr.addFlashAttribute("msg", false);
            re = "redirect:/login/pwFind";
        } else if (pass != null) {
            rttr.addFlashAttribute("msg", true);
            model.addAttribute("member",  pass);
            re = "/login/pwFind2.html";
            /*re = "redirect:/login/pwFind2";*/
        }

        return re;
    }
/*    @RequestMapping(value = "/pwFind2", method = RequestMethod.POST)
    public String pwFind2(Member member, Model model) {

        return null;
    }*/



    // 비밀번호 변경
    @RequestMapping(value="/pwCheck", method = RequestMethod.POST)
    public String pwCheck(Member member) {

        System.out.println("비밀번호 변경");

        loginService.pwChange(member);

        return "redirect:/login/";
    }



    /**
     * <p>아이디 중복체크 팝업창으로 이동</p>
     * @return
     */
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
