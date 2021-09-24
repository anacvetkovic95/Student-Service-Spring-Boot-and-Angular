package ana.cvetkovic.springboot.app.be.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDto implements Serializable, MyDto{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String indexNumber;
	private Integer indexYear;
	private String firstName;
	private String lastName;
	private String email;
	private String adress;
	private CityDto city;
	private Integer currentYearOfStudy;
	private List<StudentExamDto> exams = new ArrayList<>();
	
	public StudentDto() {
		
	}

	public StudentDto(Long id, String indexNumber, Integer indexYear, String firstName, String lastName, String email,
			String adress, CityDto city, Integer currentYearOfStudy) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public List<StudentExamDto> getExams() {
		return exams;
	}

	public void setExams(List<StudentExamDto> exams) {
		this.exams = exams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public Integer getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(Integer indexYear) {
		this.indexYear = indexYear;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	public Integer getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(Integer currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((currentYearOfStudy == null) ? 0 : currentYearOfStudy.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((exams == null) ? 0 : exams.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indexNumber == null) ? 0 : indexNumber.hashCode());
		result = prime * result + ((indexYear == null) ? 0 : indexYear.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (currentYearOfStudy == null) {
			if (other.currentYearOfStudy != null)
				return false;
		} else if (!currentYearOfStudy.equals(other.currentYearOfStudy))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (exams == null) {
			if (other.exams != null)
				return false;
		} else if (!exams.equals(other.exams))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indexNumber == null) {
			if (other.indexNumber != null)
				return false;
		} else if (!indexNumber.equals(other.indexNumber))
			return false;
		if (indexYear == null) {
			if (other.indexYear != null)
				return false;
		} else if (!indexYear.equals(other.indexYear))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", adress=" + adress + ", city=" + city
				+ ", currentYearOfStudy=" + currentYearOfStudy + ", exams=" + exams + "]";
	}

		
}
