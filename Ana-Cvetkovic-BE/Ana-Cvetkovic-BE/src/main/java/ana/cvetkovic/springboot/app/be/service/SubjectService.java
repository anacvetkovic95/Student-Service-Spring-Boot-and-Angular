package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ana.cvetkovic.springboot.app.be.dto.SubjectDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface SubjectService {
	Optional<SubjectDto> findById(Long id);
	List<SubjectDto> getAll();
	SubjectDto save(SubjectDto subjectDto) throws MyEntityExistException;
	Optional<SubjectDto> update(SubjectDto subjectDto) throws MyEntityExistException;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<SubjectDto> findByPage(Pageable pageable);
}
