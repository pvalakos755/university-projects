package myy803.springboot.main.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.Interest;

public interface InterestMapper extends JpaRepository<Interest, Integer> {

}
