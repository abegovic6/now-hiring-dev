package ba.unsa.etf.pnwt.service.skill;

import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.mapper.SkillMapper;
import ba.unsa.etf.pnwt.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService{

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<SkillDTO> findByUserId(Integer id) {
        return SkillMapper.mapToProjections(skillRepository.findByUserId(id));
    }

    @Override
    public SkillDTO createSkill(SkillDTO skillDTO) {
        return SkillMapper.mapToProjection(skillRepository.save(SkillMapper.mapToEntity(skillDTO)));
    }

    @Override
    public String deleteSkill(Integer id) {
        skillRepository.deleteSkill(id);
        return "Skill was successfully deleted";
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        return SkillMapper.mapToProjections(skillRepository.findAll());
    }
}
