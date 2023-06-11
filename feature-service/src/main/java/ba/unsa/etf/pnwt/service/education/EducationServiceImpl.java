package ba.unsa.etf.pnwt.service.education;

import ba.unsa.etf.pnwt.dto.EducationDTO;
import ba.unsa.etf.pnwt.entity.EducationEntity;
import ba.unsa.etf.pnwt.entity.ExperienceEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;
import ba.unsa.etf.pnwt.mapper.EducationMapper;
import ba.unsa.etf.pnwt.mapper.ExperienceMapper;
import ba.unsa.etf.pnwt.repository.EducationRepository;
import ba.unsa.etf.pnwt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService{

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<EducationDTO> findEducationByUser(Integer id){
        UserEntity userEntity = userRepository.findById(id);
        if (userEntity != null)
            return EducationMapper.mapToProjections(educationRepository.findByUserId(id));
        else throw new EntityNotFoundException("User with provided id not found");
    }

    @Override
    public EducationEntity createEducation(EducationDTO educationDTO) {
        Optional<UserEntity> user= educationRepository.findUserEntityByEmail(educationDTO.getUser().getEmail());
        EducationEntity education = new EducationEntity(user.get(), educationDTO.getTitle(), educationDTO.getDescription(), educationDTO.getStartingMonth(), educationDTO.getStartingYear(), educationDTO.getEndMonth(), educationDTO.getEndYear());
        educationRepository.save(education);
        return education;
        //return EducationMapper.mapToProjection(educationRepository.save(EducationMapper.mapToEntity(educationDTO)));
    }

    @Override
    public String deleteEducation(Integer id) {
        Optional<EducationEntity> educationEntity = educationRepository.findById(id);
        if (educationEntity.isPresent()) {
            educationRepository.deleteEducationById(id);
            return "Education succesfully deleted";
        }else throw new EntityNotFoundException("Education with provided id not found");
    }

    @Override
    public List<EducationDTO> getAllEducations() {
        return EducationMapper.mapToProjections(educationRepository.findAll());
    }
}
