package ba.unsa.etf.pnwt.service.education;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.entity.EducationEntity;

import java.util.List;

public interface EducationService {
    List<EducationDTO> findEducationByUser(Integer id);

    EducationEntity createEducation(EducationDTO educationDTO);

    String deleteEducation(Integer id);

    List<EducationDTO> getAllEducations();
}
