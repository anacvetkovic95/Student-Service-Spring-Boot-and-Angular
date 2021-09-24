package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.StudentExamDto;
import ana.cvetkovic.springboot.app.be.entity.StudentExamEntity;

@Mapper(componentModel = "spring")
public interface StudentExamMapper {
	StudentExamEntity toEntity(StudentExamDto dto);
	StudentExamDto toDto(StudentExamEntity entity);
}
