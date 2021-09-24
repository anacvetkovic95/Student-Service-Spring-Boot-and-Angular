package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ana.cvetkovic.springboot.app.be.dto.StudentDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface StudentService {
	Optional<StudentDto> findById(Long id);
	List<StudentDto> getAll();
	StudentDto save(StudentDto studentDto) throws MyEntityNotPresentedException, MyEntityExistException;
	Optional<StudentDto> update(StudentDto studentDto) throws MyEntityNotPresentedException;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<StudentDto> findByPage(Pageable pageable);
}
