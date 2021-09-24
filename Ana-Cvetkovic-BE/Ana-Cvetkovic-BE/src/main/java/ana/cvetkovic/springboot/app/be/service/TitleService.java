package ana.cvetkovic.springboot.app.be.service;

import java.util.List;

import ana.cvetkovic.springboot.app.be.dto.TitleDto;

public interface TitleService {
	List<TitleDto> getAll();
}
