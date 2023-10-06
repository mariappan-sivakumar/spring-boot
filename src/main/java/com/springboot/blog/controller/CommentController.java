package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                   @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommandByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,@PathVariable(value = "id") long commendId){
        CommentDto commentDto=commentService.getCommentById(postId,commendId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") long postId,
            @PathVariable(value = "id") long commentId,
            @Valid @RequestBody CommentDto commentDto){
        CommentDto commentDtoUpdated=commentService.updateComment(postId, commentId, commentDto);
        return ResponseEntity.ok(commentDtoUpdated);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String > deleteComment(@PathVariable long postId,@PathVariable long commentId){
        commentService.deleteComment(postId,commentId);
        return ResponseEntity.ok("Comment is deleted successfully");
    }
}
