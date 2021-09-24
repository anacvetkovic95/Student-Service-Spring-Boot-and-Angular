package ana.cvetkovic.springboot.app.be.contorller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import ana.cvetkovic.springboot.app.be.service.CityService;

@RestController
@RequestMapping(path = "/city")
public class CityRestController {
	private final CityService cityService;
	
	@Autowired
	public CityRestController(CityService cityService) {
		this.cityService = cityService;
	}
	
	@GetMapping
	public @ResponseBody ResponseEntity<Object> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(cityService.getAll());

	}

}
	






