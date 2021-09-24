package ana.cvetkovic.springboot.app.be.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfessorDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String adress;
	private CityDto city;
	private Date reelectionDate;
	private TitleDto title;
	private String phone;
	
	private List<SimpleSubjectDto> subjects;
	
	public ProfessorDto() {
		city = new CityDto();
		subjects =  new ArrayList<SimpleSubjectDto>();
	}

	public ProfessorDto(Long id, String firstName, String lastName, String email, String adress, CityDto city,
			Date reelectionDate, TitleDto title, String phone) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.reelectionDate = reelectionDate;
		this.title=title;
		this.phone=phone;
		this.subjects = new ArrayList<SimpleSubjectDto>();
	}
	
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SimpleSubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SimpleSubjectDto> subjects) {
		this.subjects = subjects;
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

	public Date getReelectionDate() {
		return reelectionDate;
	}

	public void setReelectionDate(Date reelectionDate) {
		this.reelectionDate = reelectionDate;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reelectionDate == null) ? 0 : reelectionDate.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ProfessorDto other = (ProfessorDto) obj;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reelectionDate == null) {
			if (other.reelectionDate != null)
				return false;
		} else if (!reelectionDate.equals(other.reelectionDate))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", adress=" + adress + ", city=" + city + ", reelectionDate=" + reelectionDate + ", title=" + title
				+ ", phone=" + phone + ", subjects=" + subjects + "]";
	}
	
	
}
