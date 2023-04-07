package ba.unsa.etf.pnwt.service.experience;

import ba.unsa.etf.pnwt.dto.ExperienceDTO;
import ba.unsa.etf.pnwt.entity.ExperienceEntity;
import ba.unsa.etf.pnwt.entity.SkillEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;
import ba.unsa.etf.pnwt.mapper.ExperienceMapper;
import ba.unsa.etf.pnwt.mapper.SkillMapper;
import ba.unsa.etf.pnwt.repository.ExperienceRepository;
import ba.unsa.etf.pnwt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceServiceImpl implements ExperienceService{
    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ExperienceDTO> findExperienceByUser(Integer id){
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity != null)
            return ExperienceMapper.mapToProjections(experienceRepository.findByUserId(id));
        else throw new EntityNotFoundException("User with provided id not found");
    }

    @Override
    public ExperienceDTO createExperience(ExperienceDTO experienceDTO) {
        return ExperienceMapper.mapToProjection(experienceRepository.save(ExperienceMapper.mapToEntity(experienceDTO)));
    }

    @Override
    public String deleteExperience(Integer id) {
        Optional<ExperienceEntity> experienceEntity = experienceRepository.findById(id);
        if (experienceEntity.isPresent()) {
            experienceRepository.deleteExperience(id);
            return "Experience was successfully deleted";
        }else throw new EntityNotFoundException("Experience with provided id not found");
    }

    @Override
    public List<ExperienceDTO> getAllExperience() {
        return ExperienceMapper.mapToProjections(experienceRepository.findAll());
    }
}
