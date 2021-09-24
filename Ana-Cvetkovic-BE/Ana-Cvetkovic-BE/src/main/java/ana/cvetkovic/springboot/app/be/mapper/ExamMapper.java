package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.ExamDto;
import ana.cvetkovic.springboot.app.be.entity.ExamEntity;

@Mapper(componentModel = "spring", uses= { ExamPeriodMapper.class, ProfessorMapStructMapper.class, SimpleSubjectMapper.class, CityMapStructMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamMapper {
	    ExamDto toDto(ExamEntity entity);
	    ExamEntity toEntity(ExamDto dto);
}
