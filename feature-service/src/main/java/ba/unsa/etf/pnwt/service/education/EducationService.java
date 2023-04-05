package ba.unsa.etf.pnwt.service.education;

import ba.unsa.etf.pnwt.dto.EducationDTO;

import java.util.List;

public interface EducationService {
    List<EducationDTO> findEducationByUser(Integer id);

    EducationDTO createEducation(EducationDTO educationDTO);

    String deleteEducation(Integer id);

    List<EducationDTO> getAllEducations();
}
