package ana.cvetkovic.springboot.app.be.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class SubjectEntity implements MyEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Column(name="number_of_espb")
	private Integer noOfESPB;
	private Integer yearOfStudy;
	@Enumerated(EnumType.STRING)
	private Semester semester;
	

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="subjects")
	private List<ProfessorEntity> engagedProfessors;
	
	public SubjectEntity() {
		engagedProfessors= new ArrayList<ProfessorEntity>();
	}
	
	public void add(ProfessorEntity professorEntity) {
		engagedProfessors.add(professorEntity);
	}

	public SubjectEntity(Long id, String name, String description, int noOfESPB, int yearOfStudy, Semester semester) {
		super();
		this.id=id;
		this.name = name;
		this.description = description;
		this.noOfESPB = noOfESPB;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
	}
	
	
	
	public List<ProfessorEntity> getEngagedProfessors() {
		return engagedProfessors;
	}

	public void setEngagedProfessors(List<ProfessorEntity> engagedProfessors) {
		this.engagedProfessors = engagedProfessors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNoOfESPB() {
		return noOfESPB;
	}

	public void setNoOfESPB(Integer noOfESPB) {
		this.noOfESPB = noOfESPB;
	}

	public Integer getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(Integer yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((noOfESPB == null) ? 0 : noOfESPB.hashCode());
		result = prime * result + ((engagedProfessors == null) ? 0 : engagedProfessors.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + ((yearOfStudy == null) ? 0 : yearOfStudy.hashCode());
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
		SubjectEntity other = (SubjectEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noOfESPB == null) {
			if (other.noOfESPB != null)
				return false;
		} else if (!noOfESPB.equals(other.noOfESPB))
			return false;
		if (engagedProfessors == null) {
			if (other.engagedProfessors != null)
				return false;
		} else if (!engagedProfessors.equals(other.engagedProfessors))
			return false;
		if (semester != other.semester)
			return false;
		if (yearOfStudy == null) {
			if (other.yearOfStudy != null)
				return false;
		} else if (!yearOfStudy.equals(other.yearOfStudy))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", name=" + name + ", description=" + description + ", noOfESPB=" + noOfESPB
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", engagedProfessors=" + engagedProfessors + "]";
	}
	
}
