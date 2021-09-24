package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;

import ana.cvetkovic.springboot.app.be.dto.ExamPeriodDto;
import ana.cvetkovic.springboot.app.be.entity.ExamPeriodEntity;

@Mapper(componentModel = "spring")
public interface ExamPeriodMapper {
	ExamPeriodDto toDto(ExamPeriodEntity entity);
	ExamPeriodEntity toEntity(ExamPeriodDto dto);
}
