package com.ul.imagegallery.controller;

import com.ul.imagegallery.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping(path = "/comment/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String addComment(@RequestParam Long id,
                             @RequestParam(required = false) String value) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        postService.addComment(id, authentication.getName(), value);
        return "redirect:/";
    }

    @PostMapping(path = "/comment/like", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String likePost(@RequestParam Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        postService.likeOrUnlikeComment(id,authentication.getName());
        return "redirect:/";
    }
}
