package com.monthly.blogapi;

import java.util.List;

public interface BlogService {
    Blog addPost(Blog blog);
    Blog updateBlog(Blog blog,Long id);
    boolean deleteBlog(Long id);
    Blog getBlogById(Long id);
    List<Blog> getBlogs();
    List<Blog> getBlogByTerm(String term);
}
