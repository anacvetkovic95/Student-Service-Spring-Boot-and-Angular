package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ana.cvetkovic.springboot.app.be.dto.ProfessorDto;
import ana.cvetkovic.springboot.app.be.entity.ProfessorEntity;

@Mapper(componentModel="spring",uses= {CityMapStructMapper.class, TitleMapStructMapper.class, SimpleSubjectMapper.class},injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfessorMapStructMapper {
	 @Mapping(source = "subjects", target = "subjects")
	ProfessorDto toDto(ProfessorEntity entity);
	@InheritInverseConfiguration ProfessorEntity toEntity(ProfessorDto dto);
}
