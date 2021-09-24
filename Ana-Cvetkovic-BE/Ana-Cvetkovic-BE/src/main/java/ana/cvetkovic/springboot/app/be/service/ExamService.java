package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import ana.cvetkovic.springboot.app.be.dto.ExamDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface ExamService {
	Optional<ExamDto> findById(Long id);
	List<ExamDto> getAll();
	ExamDto save(ExamDto examDto) throws Exception;
	Optional<ExamDto> update(ExamDto examDto) throws MyEntityNotPresentedException;
	void delete(Long id) throws MyEntityNotPresentedException;
	List<ExamDto> getActive();
}
