package ana.cvetkovic.springboot.app.be.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ana.cvetkovic.springboot.app.be.dto.TitleDto;
import ana.cvetkovic.springboot.app.be.entity.TitleEntity;
import ana.cvetkovic.springboot.app.be.mapper.TitleMapStructMapper;
import ana.cvetkovic.springboot.app.be.repository.TitleRepository;
import ana.cvetkovic.springboot.app.be.service.TitleService;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {
	
	private TitleRepository titleRepository;
	private TitleMapStructMapper titleMapper;
	
	@Autowired
	public TitleServiceImpl(TitleRepository titleRepository,TitleMapStructMapper titleMapper ) {
		this.titleRepository = titleRepository;
		this.titleMapper = titleMapper;
	}

	@Override
	public List<TitleDto> getAll() {
		List<TitleEntity> entities = titleRepository.findAll();
		return entities.stream().map(entity -> {
			return titleMapper.toDto(entity);
		}).collect(Collectors.toList());
	}

}
