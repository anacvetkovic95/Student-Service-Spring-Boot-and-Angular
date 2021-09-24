package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ana.cvetkovic.springboot.app.be.dto.ExamPeriodDto;
import ana.cvetkovic.springboot.app.be.entity.ExamPeriodEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.ExamPeriodMapper;
import ana.cvetkovic.springboot.app.be.repository.ExamPeriodRepository;
import ana.cvetkovic.springboot.app.be.service.ExamPeriodService;

@Service
@Transactional
public class ExamPeriodServiceImpl implements ExamPeriodService{
	private ExamPeriodRepository examPeriodRepository;
	private ExamPeriodMapper examPeriodMapper;
	
	@Autowired
	public ExamPeriodServiceImpl(ExamPeriodRepository examPeriodRepository, ExamPeriodMapper examPeriodMapper) {
		this.examPeriodRepository=examPeriodRepository;
		this.examPeriodMapper=examPeriodMapper;
	}

	@Override
	public Optional<ExamPeriodDto> findById(Long id) {
		Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(id);
		if (examPeriodEntity.isPresent()) {
			return Optional.of(examPeriodMapper.toDto(examPeriodEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamPeriodDto> getAll() {
		List<ExamPeriodEntity> examPeriodEntities = examPeriodRepository.findAll();
		return examPeriodEntities.stream().map(examPeriodEntity -> {
			return examPeriodMapper.toDto(examPeriodEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamPeriodDto save(ExamPeriodDto examPeriodDto)
			throws MyEntityExistException {
		Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(examPeriodDto.getId());
		if(examPeriodEntity.isPresent()) {
			throw new MyEntityExistException("Exam period with id"+examPeriodDto.getId()+"already exists!", examPeriodMapper.toDto(examPeriodEntity.get()));
		}
		if(examPeriodDto.isActive()) {			
			List<ExamPeriodEntity> examPeriodEntities = examPeriodRepository.findAll();
			ExamPeriodEntity examPeriod = examPeriodEntity.get();
			for(ExamPeriodEntity entity : examPeriodEntities) {
				if(entity.isActive()) {
					throw new MyEntityExistException("Exam period can't be active because there is other exam period that is allready active", null);
				}
				if( (entity.getStartPeriod().getTime() <= examPeriod.getEndPeriod().getTime()) && (examPeriod.getStartPeriod().getTime() <= entity.getEndPeriod().getTime())) {
					throw new MyEntityExistException("Exam is overlaping with other exam", null);
				}
			}
		}
		ExamPeriodEntity examPEriod = examPeriodRepository.save(examPeriodMapper.toEntity(examPeriodDto));
		return examPeriodMapper.toDto(examPEriod);	
	}

	@Override
	public Optional<ExamPeriodDto> update(ExamPeriodDto examPeriodDto) throws MyEntityNotPresentedException, MyEntityExistException {
		if(examPeriodDto.isActive()) {			
			List<ExamPeriodEntity> examPeriodEntities = examPeriodRepository.findAll();
			for(ExamPeriodEntity entity : examPeriodEntities) {
				if(entity.isActive()) {
					throw new MyEntityExistException("Exam period can't be active because there is other exam period that is allready active",null);
				}
			}
		}
		Optional<ExamPeriodEntity> examPeriodEntity = examPeriodRepository.findById(examPeriodDto.getId());
		if (examPeriodEntity.isPresent()) {
			ExamPeriodEntity examPeriod = examPeriodRepository.save(examPeriodMapper.toEntity(examPeriodDto));
			return Optional.of(examPeriodMapper.toDto(examPeriod));
		}
		throw new MyEntityNotPresentedException("Exam period with id"+examPeriodDto.getId()+"does not exists!");
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamPeriodEntity> entity = examPeriodRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam period with code " + id + " does not exist!");
		}
		examPeriodRepository.delete(entity.get());
		
	}

}
