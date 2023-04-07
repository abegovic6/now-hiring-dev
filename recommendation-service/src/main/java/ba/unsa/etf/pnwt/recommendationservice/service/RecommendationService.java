package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.dto.RecommendationHelperDTO;
import ba.unsa.etf.pnwt.recommendationservice.entity.RecommendationEntity;

import java.util.List;

public interface RecommendationService {
    List<RecommendationEntity> getRecommendation();

    void addNewRecommendation(RecommendationEntity recommendation);

    void deleteRecommendation(Long id);

    RecommendationEntity addNewRecommendation(RecommendationHelperDTO recommendation);
}
