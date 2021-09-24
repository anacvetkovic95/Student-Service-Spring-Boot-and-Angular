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

import ana.cvetkovic.springboot.app.be.dto.ExamDto;
import ana.cvetkovic.springboot.app.be.dto.StudentDto;
import ana.cvetkovic.springboot.app.be.exception.MyEntityExistException;
import ana.cvetkovic.springboot.app.be.exception.MyEntityNotPresentedException;
import ana.cvetkovic.springboot.app.be.service.ExamService;
import ana.cvetkovic.springboot.app.be.service.StudentService;

@RestController
@RequestMapping(path = "/student")
public class StudentRestController {

	private final StudentService studentService;
	private final ExamService examService;
	
	@Autowired
	public StudentRestController(StudentService studentService, ExamService examService) {
		this.studentService=studentService;
		this.examService=examService;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<StudentDto> studentDto=studentService.findById(id);
		if(studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Student with id " +id+" does not exist!");
		}
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		List<StudentDto> subjectsDto=studentService.getAll();
		if(subjectsDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no students in the list!");
		}
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Object> save(@RequestBody StudentDto studentDto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentDto));
		} catch (MyEntityNotPresentedException | MyEntityExistException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<Object> update(@RequestBody StudentDto studentDto){
		Optional<StudentDto> student;
		try {
			student=studentService.update(studentDto);
			if (student.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(student.get());
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentDto);
			}
		}catch(MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
		try {
			studentService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted student with code:" + id);
		} catch (MyEntityNotPresentedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<StudentDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findByPage(pageable));
	}
	
	@GetMapping("/student-register-form/{id}")
	public @ResponseBody ResponseEntity<Object> getActive(){
		List<ExamDto> examDto=examService.getActive();
		if(examDto.size()>0) {			
			return ResponseEntity.status(HttpStatus.OK).body(examService.getAll());
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no exams in the list!");
		}
	}
}
