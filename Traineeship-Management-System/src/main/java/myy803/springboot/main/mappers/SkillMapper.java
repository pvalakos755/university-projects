package myy803.springboot.main.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.Skill;

public interface SkillMapper extends JpaRepository<Skill, Integer> {

}
