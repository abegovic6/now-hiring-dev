package ba.unsa.etf.featureservice.service.experience;

import ba.unsa.etf.featureservice.dto.ExperienceDTO;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> findExperienceByUser(Integer id);

    ExperienceDTO createExperience(ExperienceDTO experienceDTO);

    String deleteExperience(Integer id);

    List<ExperienceDTO> getAllExperience();
}
