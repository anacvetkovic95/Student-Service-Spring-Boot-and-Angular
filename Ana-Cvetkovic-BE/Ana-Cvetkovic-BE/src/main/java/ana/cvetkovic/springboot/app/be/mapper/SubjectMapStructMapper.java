package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ana.cvetkovic.springboot.app.be.dto.SubjectDto;
import ana.cvetkovic.springboot.app.be.entity.SubjectEntity;

@Mapper(componentModel = "spring", uses = ProfessorMapStructMapper.class)
public interface SubjectMapStructMapper {
	SubjectDto toDto(SubjectEntity entity);
    @Mapping(target="engagedProfessors", ignore = true)
    SubjectEntity toEntity(SubjectDto dto);
}
