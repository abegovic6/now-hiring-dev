package ba.unsa.etf.pnwt.recommendationservice.service;

import ba.unsa.etf.pnwt.recommendationservice.entity.JobEntity;

import java.util.List;

public interface JobService {
    List<JobEntity> getJobs();

    void addNewJob(JobEntity jobEntity);

    void deleteRecommendation(Long id);
}
