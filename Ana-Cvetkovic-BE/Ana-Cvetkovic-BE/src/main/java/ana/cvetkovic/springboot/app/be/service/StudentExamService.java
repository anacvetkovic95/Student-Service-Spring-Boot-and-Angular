package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import ana.cvetkovic.springboot.app.be.dto.StudentExamDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface StudentExamService {
	Optional<StudentExamDto> findById(Long id);
	List<StudentExamDto> getAll();
	StudentExamDto save(StudentExamDto studentExamDto) throws MyEntityNotPresentedException, MyEntityExistException;
	Optional<StudentExamDto> update(StudentExamDto studentExamDto) throws MyEntityNotPresentedException;
	void delete(Long id) throws MyEntityNotPresentedException;
}
