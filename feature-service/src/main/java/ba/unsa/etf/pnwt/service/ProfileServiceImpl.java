package ba.unsa.etf.pnwt.service;

import ba.unsa.etf.pnwt.dto.ProfileDTO;
import ba.unsa.etf.pnwt.entity.UserEntity;
import ba.unsa.etf.pnwt.repository.UserRepository;
import ba.unsa.etf.pnwt.service.education.EducationService;
import ba.unsa.etf.pnwt.service.experience.ExperienceService;
import ba.unsa.etf.pnwt.service.skill.SkillService;
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
