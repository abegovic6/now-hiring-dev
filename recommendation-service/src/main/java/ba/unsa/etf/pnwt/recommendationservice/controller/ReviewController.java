package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.CommentEntityHelper;
import ba.unsa.etf.pnwt.recommendationservice.dto.ReviewDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.CommentEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "recommendation-service/review")
public class ReviewController {

    @Autowired
    protected ReviewService reviewService;

    @GetMapping("{uuid}/all")
    public List<ReviewDTO> getAllComments(@PathVariable("uuid") String uuid){
        return reviewService.getAllReviews(uuid);
    }

    @PostMapping(path="/add")
    public ResponseEntity<ReviewDTO> addComment(@RequestBody ReviewDTO review){
        return ResponseEntity.ok(reviewService.addReview(review));
    }
}
