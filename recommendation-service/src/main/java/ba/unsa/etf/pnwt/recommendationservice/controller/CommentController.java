package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.CommentEntityHelper;
import ba.unsa.etf.pnwt.recommendationservice.entity.CommentEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.CommentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "recommendation-service/comment")
public class CommentController {
    private final CommentServiceImp commentService;

    @Autowired
    public CommentController(CommentServiceImp commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentEntity> getAllComments(){
        return commentService.getAllComments();
    }
    @GetMapping(path = "/byUser")
    public List<CommentEntity> getCommentsByUSer(@RequestParam(required = true) Long id){
        return commentService.getCommentByUser(id);
    }

    @GetMapping(path = "/byUserEmail")
    public List<CommentEntity> getCommentsByUserEmail(@RequestParam(required = true) String email){
        return commentService.getCommentByUserEmail(email);
    }
    @PostMapping(path="/addComment")
    public ResponseEntity<CommentEntity> addComment(@Valid @RequestBody CommentEntityHelper comment){
        CommentEntity addedComment = commentService.addNewComment(comment);
        return ResponseEntity.ok(addedComment);
    }
}
