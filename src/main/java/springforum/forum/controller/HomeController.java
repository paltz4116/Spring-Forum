package springforum.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springforum.forum.dto.MemberLoginDto;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("member", new Member());
        return "login/signup";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("member", new MemberLoginDto());
        return "login/login";
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("post", new Post());
        return "post/post";
    }
}
