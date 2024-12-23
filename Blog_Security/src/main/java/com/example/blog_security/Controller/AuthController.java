package com.example.blog_security.Controller;

import com.example.blog_security.ApiResponse.ApiResponse;
import com.example.blog_security.Model.MyUser;
import com.example.blog_security.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity RegisterUser(@RequestBody @Valid MyUser myUser){
        authService.RegisterUser(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("User Registered"));
    }

}
