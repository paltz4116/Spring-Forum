package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springforum.forum.dto.MemberLoginDto;
import springforum.forum.dto.PostDto;
import springforum.forum.entity.Member;
import springforum.forum.service.PostService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Pageable pageable, Model model) {

        Page<PostDto> paging = postService.findPage(0, pageable);
        List<PostDto> posts = paging.getContent();

        model.addAttribute("paging", paging);
        model.addAttribute("posts", posts);
        model.addAttribute("loginUser", request.getAttribute("loginUser"));

        return "home";
    }

    @GetMapping("/page/{num}")
    public String page(@PathVariable("num") int num, HttpServletRequest request,
                       Pageable pageable, Model model) {

        Page<PostDto> paging = postService.findPage(num - 1, pageable);
        List<PostDto> posts = paging.getContent();

        model.addAttribute("paging", paging);
        model.addAttribute("posts", posts);
        model.addAttribute("loginUser", request.getAttribute("loginUser"));

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
}
