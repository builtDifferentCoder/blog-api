package com.monthly.blogapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService){
        this.blogService=blogService;
    }
    @PostMapping
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
        Blog addedBlog=blogService.addPost(blog);
        if(addedBlog!=null)
            return new ResponseEntity<>(addedBlog,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Blog>>getAllBlogs(){
        List<Blog> blogs=blogService.getBlogs();
        if(blogs!=null)
            return new ResponseEntity<>(blogs,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog>getBlogById(@PathVariable Long id){
        Blog blog=blogService.getBlogById(id);
        if(blog!=null)
            return new ResponseEntity<>(blog,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Blog>updateBlog(@RequestBody Blog blog,@PathVariable Long id){
       Blog updatedBlog=blogService.updateBlog(blog,id);
       if(updatedBlog!=null)
           return new ResponseEntity<>(updatedBlog,HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteBlog(@PathVariable Long id){
        boolean isDeleted= blogService.deleteBlog(id);
        if(isDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<Blog>>getBlogByTerm(@RequestParam String term){
        List<Blog> blogs=blogService.getBlogByTerm(term);
        if(blogs!=null)
            return new ResponseEntity<>(blogs,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
