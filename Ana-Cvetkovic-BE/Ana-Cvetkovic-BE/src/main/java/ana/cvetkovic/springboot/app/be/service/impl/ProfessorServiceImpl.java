package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ana.cvetkovic.springboot.app.be.dto.ProfessorDto;
import ana.cvetkovic.springboot.app.be.dto.SimpleSubjectDto;
import ana.cvetkovic.springboot.app.be.entity.CityEntity;
import ana.cvetkovic.springboot.app.be.entity.ProfessorEntity;
import ana.cvetkovic.springboot.app.be.entity.SubjectEntity;
import ana.cvetkovic.springboot.app.be.entity.TitleEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.CityMapStructMapper;
import ana.cvetkovic.springboot.app.be.mapper.ProfessorMapStructMapper;
import ana.cvetkovic.springboot.app.be.mapper.SimpleSubjectMapper;
import ana.cvetkovic.springboot.app.be.mapper.TitleMapStructMapper;
import ana.cvetkovic.springboot.app.be.repository.CityRepository;
import ana.cvetkovic.springboot.app.be.repository.ProfessorRepository;
import ana.cvetkovic.springboot.app.be.repository.SubjectRepository;
import ana.cvetkovic.springboot.app.be.repository.TitleRepository;
import ana.cvetkovic.springboot.app.be.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService{
	private ProfessorRepository professorRepository;
	private ProfessorMapStructMapper professorMapper;
	private CityRepository cityRepository;
	private TitleRepository titleRepository;
	private SubjectRepository subjectRepository;
	
	@Autowired
	public ProfessorServiceImpl(
			ProfessorRepository professorRepository,
			ProfessorMapStructMapper professorMapper,
			CityRepository cityRepository,
			CityMapStructMapper cityMapper,
			TitleRepository titleRepository,
			TitleMapStructMapper titleMapper,
			SubjectRepository subjectRepository,
			SimpleSubjectMapper simpleSubjectMapper) {
		this.professorRepository=professorRepository;
		this.professorMapper=professorMapper;
		this.cityRepository=cityRepository;
		this.titleRepository=titleRepository;
		this.subjectRepository=subjectRepository;
	}
	

	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professorEntity = professorRepository.findById(id);
		if (professorEntity.isPresent()) {
			return Optional.of(professorMapper.toDto(professorEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ProfessorDto> getAll() {
		List<ProfessorEntity> professorEntities = professorRepository.findAll();
		return professorEntities.stream().map(professorEntity -> {
			return professorMapper.toDto(professorEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public ProfessorDto save(ProfessorDto professorDto) throws MyEntityNotPresentedException, MyEntityExistException {
		System.out.println(professorDto);
		Optional<CityEntity> cityEntity= cityRepository.findById(professorDto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ professorDto.getCity().getCityCode() +" does not exist!");
		}
		Optional<TitleEntity> titleEntity= titleRepository.findById(professorDto.getTitle().getId());
		if(!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with code "+ professorDto.getTitle().getId() +" does not exist!");
		}
		List<SimpleSubjectDto> professorDtoSubject = professorDto.getSubjects();
		ProfessorEntity professor = professorMapper.toEntity(professorDto);
		List<SubjectEntity> allSubjects = subjectRepository.findAll();
		for(SimpleSubjectDto subjectDto : professorDtoSubject) {
			System.out.println(subjectDto);
			Optional<SubjectEntity> subjectOptional = subjectRepository.findById(subjectDto.getId());
			SubjectEntity subject = subjectOptional.get();
			System.out.println(subject);
			if(!allSubjects.contains(subject)) {
				throw new MyEntityNotPresentedException("Subject with code "+ subject.getId() +" does not exist!");
			}
			if(professor.getSubjects().size() > 0 && professor.getSubjects().contains(subject)) {
				throw new MyEntityExistException("Professor is already engaged for subject with code "+ subject.getId() +"!",null);
			}
		}
		List<ProfessorEntity> allProfessors = professorRepository.findAll();
		for(ProfessorEntity professorEnt : allProfessors) {
			if(professorEnt.getEmail().equals(professorDto.getEmail())) {
				throw new MyEntityExistException("Email must be unique!", null);
			}
		}
		
		professorRepository.save(professor);

		System.out.println(professor);
		ProfessorDto professorDtoo = professorMapper.toDto(professor);
		System.out.println(professorDtoo);
		return professorDtoo;
	}

	@Override
	public Optional<ProfessorDto> update(ProfessorDto professorDto) throws MyEntityNotPresentedException {
		Optional<CityEntity> cityEntity= cityRepository.findById(professorDto.getCity().getCityCode());
		if(!cityEntity.isPresent()) {
			throw new MyEntityNotPresentedException("City with code "+ professorDto.getCity().getCityCode()+" does not exist!");
		}
		Optional<TitleEntity> titleEntity= titleRepository.findById(professorDto.getTitle().getId());
		if(!titleEntity.isPresent()) {
			throw new MyEntityNotPresentedException("Title with code "+ professorDto.getTitle().getId()+" does not exist!");
		}
		Optional<ProfessorEntity> entity = professorRepository.findById(professorDto.getId());
		if (!entity.isPresent()) {			
			throw new MyEntityNotPresentedException("Professor with id"+professorDto.getId()+"does not exists!");
		}
		ProfessorEntity professor = professorRepository.save(professorMapper.toEntity(professorDto));
		return Optional.of(professorMapper.toDto(professor));
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ProfessorEntity> entity = professorRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Professor with code " + id + " does not exist!");
		}
		professorRepository.delete(entity.get());
	}

	@Override
	public Page<ProfessorDto> findByPage(Pageable pageable) {
		Page<ProfessorDto> entites = professorRepository.findAll(pageable).map(professorMapper::toDto);
		return entites;
	}

}
