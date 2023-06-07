package ba.unsa.etf.featureservice.service.education;

import ba.unsa.etf.featureservice.dto.EducationDTO;

import java.util.List;

public interface EducationService {
    List<EducationDTO> findEducationByUser(Integer id);

    EducationDTO createEducation(EducationDTO educationDTO);

    String deleteEducation(Integer id);

    List<EducationDTO> getAllEducations();
}
