package ba.unsa.etf.featureservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileDTO implements Serializable {

    private List<ExperienceDTO> experiences = new ArrayList<>();
    private List<EducationDTO> educations = new ArrayList<>();
    private List<SkillDTO> skills = new ArrayList<>();

    public List<ExperienceDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperienceDTO> experiences) {
        this.experiences = experiences;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }
}
