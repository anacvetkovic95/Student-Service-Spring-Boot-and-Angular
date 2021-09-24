package ana.cvetkovic.springboot.app.be.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import ana.cvetkovic.springboot.app.be.dto.CityDto;
import ana.cvetkovic.springboot.app.be.entity.CityEntity;

@Mapper(componentModel = "spring")
public interface CityMapStructMapper {
		@Mappings({
	      @Mapping(target="cityCode", source="entity.code"),
	      @Mapping(target="cityName", source="entity.name")
	    })
	    CityDto toDto(CityEntity entity);
	
	    @Mappings({
	      @Mapping(target="code", source="dto.cityCode"),
	      @Mapping(target="name", source="dto.cityName")
	    })
	    CityEntity toEntity(CityDto dto);
}
