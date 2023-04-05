package ba.unsa.etf.pnwt.service.experience;

import ba.unsa.etf.pnwt.dto.ExperienceDTO;
import ba.unsa.etf.pnwt.mapper.ExperienceMapper;
import ba.unsa.etf.pnwt.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService{
    @Autowired
    ExperienceRepository experienceRepository;

    @Override
    public List<ExperienceDTO> findExperienceByUser(Integer id){
        return ExperienceMapper.mapToProjections(experienceRepository.findByUserId(id));
    }

    @Override
    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {
        return ExperienceMapper.mapToProjection(experienceRepository.save(ExperienceMapper.mapToEntity(experienceDTO)));
    }

    @Override
    public String deleteExperience(Integer id) {
        experienceRepository.deleteExperience(id);
        return "Experience was successfully deleted";
    }

    @Override
    public List<ExperienceDTO> getAllExperience() {
        return ExperienceMapper.mapToProjections(experienceRepository.findAll());
    }
}
