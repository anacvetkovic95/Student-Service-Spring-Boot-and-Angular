package ana.cvetkovic.springboot.app.be.contorller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ana.cvetkovic.springboot.app.be.dto.SubjectDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.service.ProfessorService;
import ana.cvetkovic.springboot.app.be.service.SubjectService;

@RestController
@RequestMapping(path="/subject")
public class SubjectRestController {
	
	private final SubjectService subjectService;
	
	@Autowired
	public SubjectRestController(SubjectService subjectService, ProfessorService professorService) {
		this.subjectService=subjectService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<SubjectDto> subjectDto=subjectService.findById(id);
		if(subjectDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Subject with id " +id+" does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		List<SubjectDto> subjectsDto=subjectService.getAll();
		if(subjectsDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no subjects in the list!");
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody SubjectDto subjectDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
		} catch (MyEntityExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto) throws MyEntityExistException{
		Optional<SubjectDto> subject=subjectService.update(subjectDto);
		if (subject.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subject.get());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(subjectDto);
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			subjectService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted subject with code:" + id);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findByPage(pageable));
	}

}
