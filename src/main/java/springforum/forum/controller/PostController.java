package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;
import springforum.forum.service.PostService;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public String post(Post post, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        Member loginUser = (Member) session.getAttribute("loginUser");
        post.setMember(loginUser);

        postService.save(post);

        return "redirect:/";
    }
}
