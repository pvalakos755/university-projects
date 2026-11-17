package myy803.springboot.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myy803.springboot.main.domainmodel.Company;
import myy803.springboot.main.domainmodel.Evaluation;
import myy803.springboot.main.domainmodel.EvaluationType;
import myy803.springboot.main.domainmodel.Student;
import myy803.springboot.main.domainmodel.TraineeshipPosition;
import myy803.springboot.main.mappers.CompanyMapper;
import myy803.springboot.main.mappers.StudentMapper;
import myy803.springboot.main.mappers.TraineeshipPositionsMapper;
import myy803.springboot.main.services.interfaces.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private TraineeshipPositionsMapper traineeshipPositionsMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyMapper companyMapper,TraineeshipPositionsMapper traineeshipPositionsMapper,StudentMapper studentMapper) {
		this.companyMapper=companyMapper;
		this.traineeshipPositionsMapper=traineeshipPositionsMapper;
		this.studentMapper=studentMapper;
	}
	
	public Company retrieveProfile(String username) {
		return companyMapper.findByUsername(username);
	}
	
	
	public void saveProfile(Company company) {
		companyMapper.save(company);
	}
	
	public void savePosition(TraineeshipPosition position,String username) {
		Company company=companyMapper.findByUsername(username);
		position.setCompany(company);
		List<TraineeshipPosition> positions=company.getPositions();
		positions.add(position);
		companyMapper.save(company);
	}
	
	public List<TraineeshipPosition> retrieveAvailablePositions(String username) {
		return traineeshipPositionsMapper.findByIsAssignedAndCompanyUsername(false, username);
	}
	
	public List<TraineeshipPosition> retrieveAssignedPositions(String username) {
		return traineeshipPositionsMapper.findByIsAssignedAndCompanyUsername(true, username);
	}
	
	public void deletePosition(Integer id) {
		traineeshipPositionsMapper.deleteById(id);
	}
	
	public void saveEvaluation(Integer positionId, Evaluation evaluation) {
		TraineeshipPosition position=traineeshipPositionsMapper.findById(positionId).get();
		List<Evaluation> evaluations=position.getEvaluations();
		evaluation.setPosition(position);
		evaluation.setEvaluationType(EvaluationType.FROM_COMPANY);
		evaluations.add(evaluation);
		position.setEvaluations(evaluations);
		position.setEvaluatedByCompany(true);
		traineeshipPositionsMapper.save(position);
	}
	
	public Student showStudentDetails(String studentUsername) {
		return studentMapper.findByUsername(studentUsername);
	}
}
