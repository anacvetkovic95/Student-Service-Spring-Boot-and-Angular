package ana.cvetkovic.springboot.app.be.service;

import java.util.List;

import ana.cvetkovic.springboot.app.be.dto.CityDto;

public interface CityService {
	List<CityDto> getAll();
}
