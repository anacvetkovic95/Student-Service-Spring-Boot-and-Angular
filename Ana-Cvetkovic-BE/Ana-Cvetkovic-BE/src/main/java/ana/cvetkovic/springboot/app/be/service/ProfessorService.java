package ana.cvetkovic.springboot.app.be.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ana.cvetkovic.springboot.app.be.dto.ProfessorDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;

public interface ProfessorService {
	Optional<ProfessorDto> findById(Long id);
	List<ProfessorDto> getAll();
	ProfessorDto save(ProfessorDto professorDto) throws MyEntityNotPresentedException, MyEntityExistException;
	Optional<ProfessorDto> update(ProfessorDto professorDto) throws MyEntityNotPresentedException;
	void delete(Long id) throws MyEntityNotPresentedException;
	Page<ProfessorDto> findByPage(Pageable pageable);
}
