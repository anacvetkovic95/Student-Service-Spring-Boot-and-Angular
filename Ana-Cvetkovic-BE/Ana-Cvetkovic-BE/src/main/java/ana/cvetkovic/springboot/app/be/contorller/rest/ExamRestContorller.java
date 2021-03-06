package ana.cvetkovic.springboot.app.be.contorller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import ana.cvetkovic.springboot.app.be.dto.ExamDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.service.ExamService;

@RestController
@RequestMapping(path = "/exam")
public class ExamRestContorller {
	private final ExamService examService;
	
	@Autowired
	public ExamRestContorller(ExamService examService) {
		this.examService=examService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamDto> examDto=examService.findById(id);
		if(examDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Exam with id " +id+" does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		List<ExamDto> examDto=examService.getAll();
		if(examDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no exams in the list!");
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamDto examDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examService.save(examDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ExamDto examDto){
		Optional<ExamDto> exam;
		try {
			exam = examService.update(examDto);
			if (exam.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(exam.get());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examDto);
			}
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			examService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted exam with code:" + id);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
