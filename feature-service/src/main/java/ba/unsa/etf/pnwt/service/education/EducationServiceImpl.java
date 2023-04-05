package ba.unsa.etf.pnwt.service.education;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.mapper.EducationMapper;
import ba.unsa.etf.pnwt.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService{

    @Autowired
    EducationRepository educationRepository;

    @Override
    public List<EducationDTO> findEducationByUser(Integer id){
        return EducationMapper.mapToProjections(educationRepository.findByUserId(id));
    }

    @Override
    public EducationDTO createEducation(EducationDTO educationDTO) {
        return EducationMapper.mapToProjection(educationRepository.save(EducationMapper.mapToEntity(educationDTO)));
    }

    @Override
    public String deleteEducation(Integer id) {
        educationRepository.deleteEducationById(id);
        return "Education succesfully deleted";
    }

    @Override
    public List<EducationDTO> getAllEducations() {
        return EducationMapper.mapToProjections(educationRepository.findAll());
    }
}
