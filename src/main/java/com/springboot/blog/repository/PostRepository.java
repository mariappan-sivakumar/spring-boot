package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// no need to add @Repository annotation because SimpleJpaRepository internaly annotate with @Repository in it
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategoryId(Long categoryId);
}
