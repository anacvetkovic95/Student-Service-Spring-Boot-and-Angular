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

import ana.cvetkovic.springboot.app.be.dto.ExamPeriodDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.service.ExamPeriodService;

@RestController
@RequestMapping(path = "/examperiod")
public class ExamPeriodRestContorller {
	private final ExamPeriodService examPeriodService;
	
	@Autowired
	public ExamPeriodRestContorller(ExamPeriodService examPeriodService) {
		this.examPeriodService=examPeriodService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<ExamPeriodDto> examPeriodDto=examPeriodService.findById(id);
		if(examPeriodDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Exam period with id " +id+" does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		List<ExamPeriodDto> examPeriodDto=examPeriodService.getAll();
		if(examPeriodDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no exam periods in the list!");
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody ExamPeriodDto examPeriodDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.save(examPeriodDto));
		} catch (MyEntityExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody ExamPeriodDto examPeriodDto) {
		Optional<ExamPeriodDto> subject;
		try {
			subject = examPeriodService.update(examPeriodDto);
			if (subject.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(subject.get());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(examPeriodDto);
			}
		} catch (MyEntityNotPresentedException | MyEntityExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			examPeriodService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted exam period with code:" + id);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
