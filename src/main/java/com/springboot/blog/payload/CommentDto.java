package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @NotEmpty(message = "Email is not empty")
    @Email
    private String email;
    @NotEmpty(message = "Comment body should not be empty or null")
    @Size(min = 10,message = "Comment should be at least 10 character")
    private String body;
}
