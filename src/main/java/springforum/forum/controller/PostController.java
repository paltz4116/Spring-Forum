package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springforum.forum.dto.PostResponseDto;
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

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {

        PostResponseDto post = postService.findPost(id);

        model.addAttribute("post", post);

        return "post/postDetail";
    }
}
