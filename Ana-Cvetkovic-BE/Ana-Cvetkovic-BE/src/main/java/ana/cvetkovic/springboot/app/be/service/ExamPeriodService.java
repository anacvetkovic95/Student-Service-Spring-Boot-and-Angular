package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import ana.cvetkovic.springboot.app.be.dto.ExamPeriodDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface ExamPeriodService {
	Optional<ExamPeriodDto> findById(Long id);
	List<ExamPeriodDto> getAll();
	ExamPeriodDto save(ExamPeriodDto examPeriodDto) throws MyEntityExistException;
	Optional<ExamPeriodDto> update(ExamPeriodDto examPeriodDto) throws MyEntityNotPresentedException, MyEntityExistException;
	void delete(Long id) throws MyEntityNotPresentedException;
}
