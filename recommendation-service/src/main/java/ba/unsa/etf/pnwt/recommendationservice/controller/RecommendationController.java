package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.RecommendationHelperDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.RecommendationServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {
    private final RecommendationServiceImp recommendationService;

    @Autowired
    public RecommendationController(RecommendationServiceImp recommendationServiceImp) {
        this.recommendationService = recommendationServiceImp;
    }
    @GetMapping
    public List<RecommendationEntity> getRecommendations(){
        return recommendationService.getRecommendation();
    }
    @PostMapping(path = "/addNewRecommendation")
    public ResponseEntity<RecommendationEntity> addNewRecommendation(@Valid @RequestBody RecommendationEntity recommendation){
          recommendationService.addNewRecommendation(recommendation);
        return ResponseEntity.ok(recommendation);

    }
    @PostMapping(path="/addRecomendationDTO")
    public ResponseEntity<RecommendationEntity> addNewRecommendation(@Valid @RequestBody RecommendationHelperDTO recommendation){
        RecommendationEntity addedRecommendation = recommendationService.addNewRecommendation(recommendation);
        return ResponseEntity.ok(addedRecommendation);
    }

    @DeleteMapping(path="/delete/{recommendationId}")
    public void deleteRecommendation(@PathVariable("recommendationId") Long id){
        recommendationService.deleteRecommendation(id);
    }
}
