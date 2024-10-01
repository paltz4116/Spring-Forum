package springforum.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springforum.forum.dto.CommentSaveDto;
import springforum.forum.dto.PostResponseDto;
import springforum.forum.entity.Comment;
import springforum.forum.entity.Member;
import springforum.forum.entity.Post;
import springforum.forum.service.CommentService;
import springforum.forum.service.PostService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/posting")
    public String post(Model model) {
        model.addAttribute("post", new Post());
        return "post/post";
    }

    @PostMapping("/posting")
    public String post(Post post, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        Member loginUser = (Member) session.getAttribute("loginUser");
        post.setMember(loginUser);

        postService.save(post);

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {

        PostResponseDto post = postService.findPostDto(id);

        model.addAttribute("post", post);

        return "post/postDetail";
    }

    @PostMapping("/post/comment/{id}")
    public String commentPost(@PathVariable("id") Long postId,
                              HttpServletRequest request,
                              @Validated @ModelAttribute CommentSaveDto commentDto,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "redirect:/post/" + postId;
        }

        HttpSession session = request.getSession(false);
        Member loginUser = (Member) session.getAttribute("loginUser");

        Post post = postService.findPostForComment(postId);
        Comment comment = new Comment(commentDto.getContent(), loginUser.getName(), post);

        commentService.saveComment(comment);

        return "redirect:/post/" + postId;
    }
}
