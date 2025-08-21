package com.monthly.blogapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImplementation implements BlogService{
    private final BlogRepository blogRepository;
    public BlogServiceImplementation(BlogRepository blogRepository){
        this.blogRepository=blogRepository;
    }
    @Override
    public Blog addPost(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Blog updatedBlog, Long id) {
        return blogRepository.findById(id).map(b->{
            b.setTitle(updatedBlog.getTitle());
            b.setContent(updatedBlog.getContent());
            b.setCategory(updatedBlog.getCategory());
            b.setTags(updatedBlog.getTags());
            blogRepository.save(b);
            return b;
        }).orElse(null);
    }

    @Override
    public boolean deleteBlog(Long id) {
         Blog blog=blogRepository.findById(id).orElse(null);
         if(blog!=null){
             blogRepository.deleteById(blog.getId());
             return true;
         }
         return false;
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Override
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getBlogByTerm(String term) {
        return blogRepository.searchByTerm(term);
    }
}
