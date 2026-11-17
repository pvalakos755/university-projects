package myy803.springboot.main.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.Location;

public interface LocationMapper extends JpaRepository<Location, Integer> {

}
