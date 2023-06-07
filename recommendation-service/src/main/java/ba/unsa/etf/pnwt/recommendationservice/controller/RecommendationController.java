package ba.unsa.etf.pnwt.recommendationservice.controller;

import ba.unsa.etf.pnwt.recommendationservice.dto.RecommendationHelperDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;
import ba.unsa.etf.pnwt.recommendationservice.service.RecommendationServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/recommendation-service/recommendation")
public class RecommendationController {
    private final RecommendationServiceImp recommendationService;

    @Autowired
    public RecommendationController(RecommendationServiceImp recommendationServiceImp) {
        this.recommendationService = recommendationServiceImp;
    }
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping
    public List<RecommendationEntity> getRecommendations(){
        return recommendationService.getRecommendation();
    }
    @PostMapping(path = "/addNewRecommendation")
    public ResponseEntity<RecommendationEntity> addNewRecommendation(@Valid @RequestBody RecommendationEntity recommendation){
          recommendationService.addNewRecommendation(recommendation);
        var response = ResponseEntity.ok(recommendation);
        if(response.getStatusCode().is2xxSuccessful()){
            String url = "http://userservice/user-service/notification/" + recommendation.getUserEntity().getUuid() +"/recommend/"+recommendation.getRecommendedUser().getUuid();
            restTemplate.postForObject(url, null, String.class);
        }
        return response;

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
