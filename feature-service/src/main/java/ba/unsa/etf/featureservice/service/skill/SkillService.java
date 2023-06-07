package ba.unsa.etf.featureservice.service.skill;

import ba.unsa.etf.featureservice.dto.SkillDTO;

import java.util.List;

public interface SkillService {
    public List<SkillDTO> findByUserId(Integer id);

    public SkillDTO createSkill(SkillDTO skillDTO);

    String deleteSkill(Integer id);

    List<SkillDTO> getAllSkills();
}
