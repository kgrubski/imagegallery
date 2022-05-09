package com.ul.imagegallery.controller;

import com.ul.imagegallery.model.PostDto;
import com.ul.imagegallery.model.UserDto;
import com.ul.imagegallery.services.picture.PictureService;
import com.ul.imagegallery.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UploadController {

    @Autowired
    private PostService postService;

    @GetMapping("/upload")
    public String homepage(WebRequest request, Model model) {
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "upload";
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadFile(@ModelAttribute("post") PostDto postDto,
                             HttpServletRequest request,
                             Errors errors) throws IOException {
        // check if file is empty
        if (postDto.getPicture().isEmpty()) {
            return "errorCustom";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        postService.addPost(postDto.getPicture().getBytes(), authentication.getName(), postDto.getTitle(),
                postDto.getDescription(), postDto.isForFriendsOnly(), postDto.isPrivate());
        return "index";
    }
}
