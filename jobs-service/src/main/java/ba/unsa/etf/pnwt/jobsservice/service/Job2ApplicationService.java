package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.Job2ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;

import java.util.List;

public interface Job2ApplicationService {

    List<Job2ApplicationDTO> getAll();

    Job2ApplicationDTO getById(int id);

    Job2ApplicationDTO save(Job2ApplicationEntity j2a);

    Job2ApplicationDTO update(Job2ApplicationEntity j2a);

    void deleteById(int id);
}
