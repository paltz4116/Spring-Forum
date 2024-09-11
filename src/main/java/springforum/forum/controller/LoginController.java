package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springforum.forum.entity.Member;
import springforum.forum.service.MemberService;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(Member member) {

        memberService.save(member);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam("loginId") String loginId,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {

        if (!memberService.checkMember(loginId, password)) {
            return "login/login";
        }

        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("userLoginId", loginId);
        session.setMaxInactiveInterval(3200);

        return "redirect:/";
    }
}
