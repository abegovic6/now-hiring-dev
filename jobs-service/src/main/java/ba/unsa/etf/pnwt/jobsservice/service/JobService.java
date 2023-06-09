package ba.unsa.etf.pnwt.jobsservice.service;



import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;

import java.util.List;

public interface JobService {

    List<JobDTO> getAll();

    JobDTO getById(int id);

    JobDTO save(JobDTO job);

    JobDTO update(JobDTO job);

    String deleteById(int id);

    List<JobDTO> getByCompanyId(String id);

    String getCompanyId(int jobId);

    JobEntity findJobByTitle(String title);
}
