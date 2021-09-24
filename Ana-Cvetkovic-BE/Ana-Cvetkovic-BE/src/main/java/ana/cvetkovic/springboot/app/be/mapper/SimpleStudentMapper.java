package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ana.cvetkovic.springboot.app.be.dto.SimpleStudentDto;
import ana.cvetkovic.springboot.app.be.entity.StudentEntity;

@Mapper(componentModel="spring", uses = CityMapStructMapper.class)
public interface SimpleStudentMapper {
	SimpleStudentDto toDto(StudentEntity entity);
	@Mapping( target="exams", ignore = true )
	StudentEntity toEntity(SimpleStudentDto dto);
}
