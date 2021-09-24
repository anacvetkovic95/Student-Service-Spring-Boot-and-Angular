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

import ana.cvetkovic.springboot.app.be.dto.ProfessorDto;
import ana.cvetkovic.springboot.app.be.exception.MyApplicationException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.service.ProfessorService;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorRestController {

	private final ProfessorService professorService;
	
	@Autowired
	public ProfessorRestController(ProfessorService professorService) {
		this.professorService=professorService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ProfessorDto> professorDto=professorService.findById(id);
		if(professorDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Professor with id " +id+" does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		List<ProfessorDto> professorDto=professorService.getAll();
		if(professorDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no students in the list!");
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ProfessorDto professorDto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
		} catch (MyApplicationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ProfessorDto professorDto){
		Optional<ProfessorDto> professor;
		try {
			professor=professorService.update(professorDto);
			if (professor.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(professor.get());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(professorDto);
			}
		}catch(MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			professorService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted professor with code:" + id);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findByPage(pageable));
	}
}
