package ana.cvetkovic.springboot.app.be.dto;

import java.io.Serializable;

public class CityDto implements Serializable, MyDto {
	private static final long serialVersionUID = 1L;
	
	private Long cityCode;
	private String cityName;

	public CityDto() {
	}

	public CityDto(Long cityCode, String cityName) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
	}

	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityDto [cityCode=" + cityCode + ", cityName=" + cityName + "]";
	}
	
}
