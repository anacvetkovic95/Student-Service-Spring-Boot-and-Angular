package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ana.cvetkovic.springboot.app.be.dto.StudentDto;
import ana.cvetkovic.springboot.app.be.entity.CityEntity;
import ana.cvetkovic.springboot.app.be.entity.StudentEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.CityMapStructMapper;
import ana.cvetkovic.springboot.app.be.mapper.StudentMapStructMapper;
import ana.cvetkovic.springboot.app.be.repository.CityRepository;
import ana.cvetkovic.springboot.app.be.repository.StudentRepository;
import ana.cvetkovic.springboot.app.be.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	private StudentRepository studentRepository;
	private StudentMapStructMapper studentMapper;
	private CityRepository cityRepository;
	
	@Autowired
	public StudentServiceImpl(
			StudentRepository studentRepository,
			StudentMapStructMapper studentMapper,
			CityRepository cityRepository,
			CityMapStructMapper cityMapper) {
		this.studentRepository=studentRepository;
		this.studentMapper=studentMapper;
		this.cityRepository=cityRepository;
	}

	@Override
	public Optional<StudentDto> findById(Long id) {
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		if (studentEntity.isPresent()) {
			return Optional.of(studentMapper.toDto(studentEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<StudentDto> getAll() {
		List<StudentEntity> studentEntities = studentRepository.findAll();
		return studentEntities.stream().map(studentEntity -> {
			return studentMapper.toDto(studentEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public StudentDto save(StudentDto studentDto) throws MyEntityNotPresentedException, MyEntityExistException {
		Optional<CityEntity> cityEntity= cityRepository.findById(studentDto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ studentDto.getCity().getCityCode()+" does not exist!");
		}
		List<StudentEntity> students = studentRepository.findAll();
		for(StudentEntity student : students) {
			if(student.getIndexNumber().equals(studentDto.getIndexNumber()) && student.getIndexYear().equals(studentDto.getIndexYear())) {
				throw new MyEntityExistException("Student must have unique combination of index number and index year!", studentDto);
			}
			if(student.getEmail().equals(studentDto.getEmail())) {
				throw new MyEntityExistException("Student must have unique email!", studentDto);
			}
			if(student.getIndexNumber().equals(studentDto.getIndexNumber()) && student.getIndexYear()==studentDto.getIndexYear()) {
				throw new MyEntityExistException("Student must have combination of index number and index year!", studentDto);
			}
		}
		StudentEntity student = studentRepository.save(studentMapper.toEntity(studentDto));
		return studentMapper.toDto(student);
	}

	@Override
	public Optional<StudentDto> update(StudentDto studentDto) throws MyEntityNotPresentedException{
		Optional<CityEntity> cityEntity= cityRepository.findById(studentDto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ studentDto.getCity().getCityCode()+" does not exist!");
		}
		Optional<StudentEntity> entity = studentRepository.findById(studentDto.getId());
		if (!entity.isPresent()) {			
			throw new MyEntityNotPresentedException("Student with id"+studentDto.getId()+"does not exists!");
		}
		StudentEntity studentEntity = studentRepository.save(studentMapper.toEntity(studentDto));
		return Optional.of(studentMapper.toDto(studentEntity));
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<StudentEntity> entity = studentRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Student with code " + id + " does not exist!");
		}
		studentRepository.delete(entity.get());
	}

	@Override
	public Page<StudentDto> findByPage(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toDto);
		return entites;
	}
	
	

}
