package com.example.blog_security.Service;

import com.example.blog_security.ApiResponse.ApiException;
import com.example.blog_security.Model.Blog;
import com.example.blog_security.Model.MyUser;
import com.example.blog_security.Repository.AuthRepository;
import com.example.blog_security.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
private final AuthRepository authRepository;
private final BlogRepository blogRepository;


    public List<Blog> getMyBlogs(Integer userId){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("Wrong User id");
        }
        return blogRepository.findAllByUser(myUser);
    }

    public List<Blog> getAllBlogs(Integer userId){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("Wrong User id");
        }
        return blogRepository.findAll();
    }


    public Blog getBlogById(Integer userId, Integer blogId){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("Wrong User id");
        }
        Blog blog = blogRepository.findBlogById(blogId);
        if(blog==null){
            throw new ApiException("Wrong blog id");
        }
        return blog;
    }

    public List<Blog> getBlogByTitle(Integer userId, String title){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("Wrong User id");
        }
        List<Blog> blogs = blogRepository.findBlogByTitle(title);
        return blogs;
    }

    public void addBlog(Integer userId, Blog blog){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){
            throw new ApiException("Wrong User id");
        }

        blog.setUser(myUser);
        blogRepository.save(blog);
    }


    public void updateBlogs(Integer userId ,Integer blogId, Blog blog){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){ throw new ApiException("Wrong User id");}
        Blog oldBlog = blogRepository.findBlogById(blogId);
        if(oldBlog == null){throw new ApiException("Wrong blog id");}

        if(oldBlog.getUser().getId() != myUser.getId()){
            throw new ApiException("You don't have this blog");
        }
        oldBlog.setBody(blog.getBody());
        oldBlog.setTitle(blog.getTitle());

        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId , Integer blogId){
        MyUser myUser = authRepository.findMyUserById(userId);
        if(myUser==null){ throw new ApiException("Wrong User id");}
        Blog blog = blogRepository.findBlogById(blogId);
        if(blog == null){throw new ApiException("Wrong blog id");}

        if(blog.getUser().getId()!= myUser.getId()){
            throw new ApiException("You don't have this blog");
        }
        blogRepository.delete(blog);

    }

}
