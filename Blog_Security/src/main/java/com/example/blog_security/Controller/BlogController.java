package com.example.blog_security.Controller;

import com.example.blog_security.ApiResponse.ApiResponse;
import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.MyUser;
import com.example.blog_security.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getMyBlogs(myUser.getId()));
    }

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(blogService.getAllBlogs(myUser.getId()));
    }

    @GetMapping("/get-by-id/{blogId}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal MyUser myUser ,@PathVariable Integer blogId){
        return ResponseEntity.status(200).body(blogService.getBlogById(myUser.getId(),blogId));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal MyUser myUser,@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(myUser.getId(),title));
    }


    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myUser , @RequestBody @Valid Blog blog){
        blogService.addBlog(myUser.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added"));
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal MyUser myUser , @PathVariable Integer blogId,@RequestBody @Valid Blog blog){
        blogService.updateBlogs(myUser.getId(),blogId,blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated"));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal MyUser myUser ,@PathVariable Integer blogId){
        blogService.deleteBlog(myUser.getId(),blogId);
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted"));
    }



}
