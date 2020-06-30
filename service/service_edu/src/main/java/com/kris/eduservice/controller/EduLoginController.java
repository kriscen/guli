package com.kris.eduservice.controller;

import com.kris.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){

        return R.ok().data("roles","{admin}")
                .data("name","admin")
                .data("avatar","http://media-cdn.tripadvisor.com/media/photo-s/01/3e/05/40/the-sandbar-that-links.jpg");
    }

}
