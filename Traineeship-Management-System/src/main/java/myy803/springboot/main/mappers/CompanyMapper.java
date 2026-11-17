package myy803.springboot.main.mappers;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.springboot.main.domainmodel.Company;

public interface CompanyMapper extends JpaRepository<Company, Integer> {
	public Company findByUsername(String username);
}
