package ba.unsa.etf.pnwt.service.skill;

import ba.unsa.etf.pnwt.dto.SkillDTO;

import java.util.List;

public interface SkillService {
    public List<SkillDTO> findByUserId(Integer id);

    public SkillDTO createSkill(SkillDTO skillDTO);

    String deleteSkill(Integer id);

    List<SkillDTO> getAllSkills();
}
