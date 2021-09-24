package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ana.cvetkovic.springboot.app.be.dto.SubjectDto;
import ana.cvetkovic.springboot.app.be.entity.SubjectEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.SubjectMapStructMapper;
import ana.cvetkovic.springboot.app.be.repository.SubjectRepository;
import ana.cvetkovic.springboot.app.be.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{
	private SubjectRepository subjectRepository;
	private SubjectMapStructMapper subjectMapper;
	
	@Autowired 
	public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapStructMapper subjectMapper) {
		this.subjectRepository=subjectRepository;
		this.subjectMapper=subjectMapper;
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
		if (subjectEntity.isPresent()) {
			return Optional.of(subjectMapper.toDto(subjectEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<SubjectDto> getAll() {
		List<SubjectEntity> subjecEntities = subjectRepository.findAll();
		return subjecEntities.stream().map(subjectEntity -> {
			return subjectMapper.toDto(subjectEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public SubjectDto save(SubjectDto subjectDto) throws MyEntityExistException{
		Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectDto.getId());
		if(subjectEntity.isPresent()) {
			throw new MyEntityExistException("Subject with id"+subjectDto.getId()+"already exists!", subjectMapper.toDto(subjectEntity.get()));
		}
		SubjectEntity subject = subjectRepository.save(subjectMapper.toEntity(subjectDto));
		return subjectMapper.toDto(subject);	
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto subjectDto) throws MyEntityExistException {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectDto.getId());
		if (entity.isPresent()) {
			SubjectEntity subjectEntity = subjectRepository.save(subjectMapper.toEntity(subjectDto));
			return Optional.of(subjectMapper.toDto(subjectEntity));
		}
		throw new MyEntityExistException("Subject with id"+subjectDto.getId()+"does not exists!", subjectMapper.toDto(entity.get()));
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<SubjectEntity> entity = subjectRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Subject with code " + id + " does not exist!");
		}
		subjectRepository.delete(entity.get());
		
	}

	@Override
	public Page<SubjectDto> findByPage(Pageable pageable) {
		Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toDto);
		return entites;
	}
	
	

}
