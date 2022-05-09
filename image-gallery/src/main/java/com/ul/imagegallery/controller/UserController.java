package com.ul.imagegallery.controller;

import com.ul.imagegallery.model.UserDto;
import com.ul.imagegallery.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") UserDto userDto,
            HttpServletRequest request,
            Errors errors) {


        String firstName = userDto.getFirstName();
        userService.saveUser(firstName, userDto.getPassword());


        return new ModelAndView("successRegister", "user", userDto);
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping(path = "/user/follow", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String likePost(@RequestParam Long id) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.followUser(authentication.getName(), id);
        return "redirect:/";
    }
}
