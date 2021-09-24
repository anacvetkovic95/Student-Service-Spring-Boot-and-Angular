package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.TitleDto;
import ana.cvetkovic.springboot.app.be.entity.TitleEntity;

@Mapper(componentModel = "spring")
public interface TitleMapStructMapper {
	TitleEntity toEntity(TitleDto dto);
	TitleDto toDto(TitleEntity entity);
}
