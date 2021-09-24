package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.StudentDto;
import ana.cvetkovic.springboot.app.be.entity.StudentEntity;

@Mapper(componentModel="spring", uses= {CityMapStructMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapStructMapper {
	    StudentDto toDto(StudentEntity entity);
	    StudentEntity toEntity(StudentDto dto);
}
