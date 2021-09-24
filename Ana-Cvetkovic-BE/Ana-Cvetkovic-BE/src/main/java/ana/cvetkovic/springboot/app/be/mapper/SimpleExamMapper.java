package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ana.cvetkovic.springboot.app.be.dto.SimpleExamDto;
import ana.cvetkovic.springboot.app.be.entity.ExamEntity;

@Mapper(componentModel = "spring", uses= { ExamPeriodMapper.class, ProfessorMapStructMapper.class, SimpleSubjectMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SimpleExamMapper {
	SimpleExamDto toDto(ExamEntity entity);
	@Mapping( target="students", ignore = true )
	ExamEntity toEntity(SimpleExamDto dto);
}
