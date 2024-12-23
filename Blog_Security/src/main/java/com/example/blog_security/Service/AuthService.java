package com.example.blog_security.Service;

import com.example.blog_security.Model.MyUser;
import com.example.blog_security.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void RegisterUser(MyUser myUser){
        myUser.setRole("USER");
        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);
        authRepository.save(myUser);
    }


}
