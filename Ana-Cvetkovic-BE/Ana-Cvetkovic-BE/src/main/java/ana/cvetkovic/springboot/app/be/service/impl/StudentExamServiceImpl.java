package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import ana.cvetkovic.springboot.app.be.dto.StudentExamDto;
import ana.cvetkovic.springboot.app.be.entity.ProfessorEntity;
import ana.cvetkovic.springboot.app.be.entity.StudentExamEntity;
import ana.cvetkovic.springboot.app.be.entity.SubjectEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.StudentExamMapper;
import ana.cvetkovic.springboot.app.be.repository.StudentExamRepository;
import ana.cvetkovic.springboot.app.be.service.StudentExamService;

public class StudentExamServiceImpl implements StudentExamService{
	private StudentExamRepository studentExamRepository;
	private StudentExamMapper studentExamMapper;
	
	@Autowired
	public StudentExamServiceImpl(StudentExamRepository studentExamRepository, StudentExamMapper studentExamMapper) {
		this.studentExamRepository=studentExamRepository;
		this.studentExamMapper=studentExamMapper;
	}

	@Override
	public Optional<StudentExamDto> findById(Long id) {
		Optional<StudentExamEntity> studentExamEntity = studentExamRepository.findById(id);
		if (studentExamEntity.isPresent()) {
			return Optional.of(studentExamMapper.toDto(studentExamEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<StudentExamDto> getAll() {
		List<StudentExamEntity> studentExamEntities = studentExamRepository.findAll();
		return studentExamEntities.stream().map(studentExamEntity -> {
			return studentExamMapper.toDto(studentExamEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public StudentExamDto save(StudentExamDto studentExamDto)
			throws MyEntityNotPresentedException, MyEntityExistException {
		Optional<StudentExamEntity> studentExamEntity = studentExamRepository.findById(studentExamDto.getId().getExamId());
		/*if(studentExamEntity.isPresent()) {
			throw new MyEntityExistException("Subject with id"+studentExamDto.getId()+"already exists!", subjectMapper.toDto(studentExamEntity.get()));
		}
		SubjectEntity subject = studentExamRepository.save(subjectMapper.toEntity(studentExamDto));
		return subjectMapper.toDto(subject);*/
		return null;
	}

	@Override
	public Optional<StudentExamDto> update(StudentExamDto studentExamDto) throws MyEntityNotPresentedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		// TODO Auto-generated method stub
		
	}

}
