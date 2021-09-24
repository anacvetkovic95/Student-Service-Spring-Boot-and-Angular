package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ana.cvetkovic.springboot.app.be.dto.ExamDto;
import ana.cvetkovic.springboot.app.be.entity.ExamEntity;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.mapper.ExamMapper;
import ana.cvetkovic.springboot.app.be.repository.ExamPeriodRepository;
import ana.cvetkovic.springboot.app.be.repository.ExamRepository;
import ana.cvetkovic.springboot.app.be.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
	private ExamRepository examRepository;
	private ExamMapper examMapper;

@Autowired
public ExamServiceImpl(ExamRepository examRepository, ExamMapper examMapper, ExamPeriodRepository examPeriodRepository) {
	this.examRepository=examRepository;
	this.examMapper=examMapper;
}

	@Override
	public Optional<ExamDto> findById(Long id) {
		Optional<ExamEntity> examEntity = examRepository.findById(id);
		if (examEntity.isPresent()) {
			return Optional.of(examMapper.toDto(examEntity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<ExamDto> getAll() {
		List<ExamEntity> examEntities = examRepository.findAll();
		return examEntities.stream().map(examEntity -> {
			return examMapper.toDto(examEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public ExamDto save(ExamDto examDto) throws Exception {
		Optional<ExamEntity> examEntity = examRepository.findById(examDto.getId());
		if (examEntity.isPresent()) {
			throw new MyEntityExistException("Exam with id" + examDto.getId() + "already exists!",
					examMapper.toDto(examEntity.get()));
			
		}
		List<ExamEntity> exams = examRepository.findAll();
		for(ExamEntity exam : exams) {
			if(exam.getSubject().equals(examDto.getSubject()) && exam.getExamPeriod().equals(examDto.getExamPeriod())) {
				throw new MyEntityExistException("Exam for that subject already exists in that exam period!",
						examMapper.toDto(examEntity.get()));
			}
		}
		if( examDto.getExamDate().getTime() > examDto.getExamPeriod().getEndPeriod().getTime() || examDto.getExamDate().getTime() < examDto.getExamPeriod().getStartPeriod().getTime() ) {
			throw new Exception("Exam date not in range of exam period!");
		}
		
		ExamEntity exam = examRepository.save(examMapper.toEntity(examDto));
		return examMapper.toDto(exam);
	}

	@Override
	public Optional<ExamDto> update(ExamDto examDto) throws MyEntityNotPresentedException {
		Optional<ExamEntity> entity = examRepository.findById(examDto.getId());
		if (entity.isPresent()) {
			ExamEntity examEntity = examRepository.save(examMapper.toEntity(examDto));
			return Optional.of(examMapper.toDto(examEntity));
		}
		throw new MyEntityNotPresentedException("Exam with id" + examDto.getId() + "does not exists!");
	}

	@Override
	public void delete(Long id) throws MyEntityNotPresentedException {
		Optional<ExamEntity> entity = examRepository.findById(id);
		if (!entity.isPresent()) {
			throw new MyEntityNotPresentedException("Exam  with code " + id + " does not exist!");
		}
		examRepository.delete(entity.get());

	}
	
	public List<ExamDto> getActive() {
		List<ExamEntity> examEntities = examRepository.findAll();
		List<ExamEntity> exams = new ArrayList<ExamEntity>();
		for(ExamEntity exam : exams) {
			if(exam.getExamPeriod().isActive()) {
				exams.add(exam);
			}
		}
		return exams.stream().map(examEntity -> {
			return examMapper.toDto(examEntity);
		}).collect(Collectors.toList());
	}

}
