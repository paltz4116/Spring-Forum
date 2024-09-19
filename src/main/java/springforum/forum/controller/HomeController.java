package springforum.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springforum.forum.dto.MemberLoginDto;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;
import springforum.forum.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(Model model) {

        List<Post> posts = postService.findAll();

        model.addAttribute("posts", posts);

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
