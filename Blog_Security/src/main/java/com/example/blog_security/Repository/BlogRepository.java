package com.example.blog_security.Repository;

import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findAllByUser(MyUser myUser);
    Blog findBlogById(Integer id);
    List<Blog> findBlogByTitle(String title);
}
