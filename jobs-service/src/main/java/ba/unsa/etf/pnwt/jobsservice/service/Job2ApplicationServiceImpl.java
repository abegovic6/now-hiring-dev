package ba.unsa.etf.pnwt.jobsservice.service;

import ba.unsa.etf.pnwt.jobsservice.dto.Job2ApplicationDTO;
import ba.unsa.etf.pnwt.jobsservice.dto.JobDTO;
import ba.unsa.etf.pnwt.jobsservice.entity.Job2ApplicationEntity;
import ba.unsa.etf.pnwt.jobsservice.mapper.Job2ApplicationMapper;
import ba.unsa.etf.pnwt.jobsservice.mapper.JobMapper;
import ba.unsa.etf.pnwt.jobsservice.repository.Job2ApplicationRepository;
import ba.unsa.etf.pnwt.jobsservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Job2ApplicationServiceImpl implements Job2ApplicationService {

    @Autowired
    private Job2ApplicationRepository job2ApplicationRepository;

    @Override
    public List<Job2ApplicationDTO> getAll() {
        return Job2ApplicationMapper.mapToProjections(job2ApplicationRepository.findAll());
    }

    @Override
    public Job2ApplicationDTO getById(int id) {
        Optional<Job2ApplicationEntity> j2a = job2ApplicationRepository.findById((long) id);
        if (j2a.isPresent())
            return Job2ApplicationMapper.mapToProjection(j2a.get());
        else return null;
    }

    @Override
    public Job2ApplicationDTO save(Job2ApplicationEntity j2a) {
        return Job2ApplicationMapper.mapToProjection(job2ApplicationRepository.save(j2a));
    }

    @Override
    public Job2ApplicationDTO update(Job2ApplicationEntity j2a) {
        return Job2ApplicationMapper.mapToProjection(job2ApplicationRepository.save(j2a));
    }

//    @Override
//    public void delete(Job2ApplicationEntity j2a) {
//        job2ApplicationRepository.delete(j2a);
//    }

    @Override
    public void deleteById(int id) {
        job2ApplicationRepository.deleteById((long) id);
    }
}
