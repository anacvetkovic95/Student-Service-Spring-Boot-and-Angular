package ana.cvetkovic.springboot.app.be.contorller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ana.cvetkovic.springboot.app.be.service.TitleService;

@RestController
@RequestMapping(path = "/title")
public class TitleRestController {
	private final TitleService titleService;
	
	@Autowired
	public TitleRestController(TitleService titleService) {
		this.titleService = titleService;
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(titleService.getAll());

	}

}
	






