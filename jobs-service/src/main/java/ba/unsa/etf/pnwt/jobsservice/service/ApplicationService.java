package ba.unsa.etf.pnwt.jobsservice.service;



import ba.unsa.etf.pnwt.jobsservice.dto.ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.entity.JobEntity;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDTO> getAll();

    ApplicationDTO getById(int id);

    ApplicationDTO save(ApplicationDTO app);

    ApplicationDTO update(ApplicationDTO app);

    String deleteById(int id);

    ApplicationDTO createApplication(ApplicationDTO app);

    List<ApplicationDTO> getApplicationsForUserId(String userId);

    List<ApplicationDTO> getApplicationsForJobId(int jobId);
}
