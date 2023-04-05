package ba.unsa.etf.pnwt.service.experience;

import ba.unsa.etf.pnwt.dto.ExperienceDTO;

import java.util.List;

public interface ExperienceService {
    List<ExperienceDTO> findExperienceByUser(Integer id);

    ExperienceDTO createExperience(ExperienceDTO experienceDTO);

    String deleteExperience(Integer id);

    List<ExperienceDTO> getAllExperience();
}
