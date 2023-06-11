package ba.unsa.etf.pnwt.service.skill;

import ba.unsa.etf.pnwt.dto.SkillDTO;
import ba.unsa.etf.pnwt.entity.SkillEntity;

import java.util.List;

public interface SkillService {
    public List<SkillDTO> findByUserId(Integer id);

    public SkillEntity createSkill(SkillDTO skillDTO);

    String deleteSkill(Integer id);

    List<SkillDTO> getAllSkills();
}
