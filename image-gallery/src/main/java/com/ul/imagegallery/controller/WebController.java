package com.ul.imagegallery.controller;

import com.ul.imagegallery.database.entity.Post;
import com.ul.imagegallery.model.*;
import com.ul.imagegallery.services.picture.PictureService;
import com.ul.imagegallery.services.post.PostService;
import com.ul.imagegallery.services.user.UserService;
import com.ul.imagegallery.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {


    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(WebRequest request, Model model){
        WallDto wallDto = new WallDto();
        CommentDto commentDto = CommentDto.builder().build();
        model.addAttribute("wallDto", wallDto);
        model.addAttribute("comment", commentDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<PostDto> postsForUser = postService.getPostsForUser(authentication.getName());
        wallDto.setPostDtoList(postsForUser);
        wallDto.setImgUtil(new ImageUtil());
        return "index";
    }

}