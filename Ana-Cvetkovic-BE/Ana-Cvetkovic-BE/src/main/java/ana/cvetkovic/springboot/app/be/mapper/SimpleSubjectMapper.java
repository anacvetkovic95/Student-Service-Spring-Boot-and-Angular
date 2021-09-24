package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.SimpleSubjectDto;
import ana.cvetkovic.springboot.app.be.entity.SubjectEntity;

@Mapper(componentModel = "spring")
public interface SimpleSubjectMapper {
	SimpleSubjectDto toDto(SubjectEntity entity);
	SubjectEntity toEntity(SimpleSubjectDto dto);
}
