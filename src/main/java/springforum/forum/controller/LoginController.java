package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springforum.forum.dto.MemberLoginDto;
import springforum.forum.dto.SignupDto;
import springforum.forum.entity.Member;
import springforum.forum.service.MemberService;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("member") SignupDto member,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "login/signup";
        }

        memberService.save(member);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("member") MemberLoginDto memberLoginDto,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "login/login";
        }

        if (!memberService.checkMember(memberLoginDto.getLoginId(),
                memberLoginDto.getPassword())) {
            return "login/login";
        }

        Optional<Member> byLoginID = memberService.findByLoginID(memberLoginDto.getLoginId());
        Member member = byLoginID.get();

        request.getSession().invalidate();
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", member);
        session.setMaxInactiveInterval(1800);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
