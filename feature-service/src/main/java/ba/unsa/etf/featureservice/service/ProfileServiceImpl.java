package ba.unsa.etf.featureservice.service;

import ba.unsa.etf.featureservice.dto.ProfileDTO;
import ba.unsa.etf.featureservice.entity.UserEntity;
import ba.unsa.etf.featureservice.repository.UserRepository;
import ba.unsa.etf.featureservice.service.education.EducationService;
import ba.unsa.etf.featureservice.service.experience.ExperienceService;
import ba.unsa.etf.featureservice.service.skill.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    protected ExperienceService experienceService;

    @Autowired
    protected EducationService educationService;

    @Autowired
    protected SkillService skillService;

    @Autowired
    protected UserRepository userRepository;

    @Override
    public ProfileDTO getUserProfile(String uuid) {
        UserEntity userEntity = userRepository.findByUuid(uuid);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setEducations(educationService.findEducationByUser(userEntity.getId()));
        profileDTO.setSkills(skillService.findByUserId(userEntity.getId()));
        profileDTO.setExperiences(experienceService.findExperienceByUser(userEntity.getId()));
        return profileDTO;
    }
}
