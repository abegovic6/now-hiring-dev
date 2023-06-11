package ba.unsa.etf.pnwt.service.skill;

import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.entity.SkillEntity;
import ba.unsa.etf.pnwt.entity.UserEntity;
import ba.unsa.etf.pnwt.mapper.SkillMapper;
import ba.unsa.etf.pnwt.mapper.UserMapper;
import ba.unsa.etf.pnwt.repository.SkillRepository;
import ba.unsa.etf.pnwt.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static ba.unsa.etf.pnwt.mapper.SkillMapper.mapToEntity;

@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SkillDTO> findByUserId(Integer id) {
        UserEntity userEntity = userRepository.findById(id);
        List<SkillEntity> skillEntity = skillRepository.findByUserId(id);
        if (userEntity != null)
            return SkillMapper.mapToProjections(skillEntity);
        else throw new EntityNotFoundException("User with provided id not found");
    }

    @Override
    public SkillEntity createSkill(SkillDTO skillDTO) {


        Optional<UserEntity> user = skillRepository.findUserEntityByEmail(skillDTO.getUser().getEmail());
        SkillEntity skill = new SkillEntity(skillDTO.getTitle(), user.get());
        skillRepository.save(skill);
        return skill;
    }

    @Override
    public String deleteSkill(Integer id) {
        Optional<SkillEntity> skillEntity = skillRepository.findById(id);
        if (skillEntity.isPresent()) {
            skillRepository.deleteSkill(id);
            return "Skill was successfully deleted";
        }else throw new EntityNotFoundException("Skill with provided id not found");
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        return SkillMapper.mapToProjections(skillRepository.findAll());
    }
}
