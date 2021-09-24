package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ana.cvetkovic.springboot.app.be.dto.CityDto;
import ana.cvetkovic.springboot.app.be.entity.CityEntity;
import ana.cvetkovic.springboot.app.be.mapper.CityMapStructMapper;
import ana.cvetkovic.springboot.app.be.repository.CityRepository;
import ana.cvetkovic.springboot.app.be.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {
	
	private CityRepository cityRepository;
	private CityMapStructMapper cityMapper;
	
	@Autowired
	public CityServiceImpl(CityRepository cityRepository,CityMapStructMapper cityMapper ) {
		this.cityRepository = cityRepository;
		this.cityMapper = cityMapper;
	}

	public List<CityDto> getAll() {
		List<CityEntity> entities = cityRepository.findAll();
		return entities.stream().map(entity -> {
			return cityMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}
