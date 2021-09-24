package ana.cvetkovic.springboot.app.be.dto;

import java.util.Date;

import ana.cvetkovic.springboot.app.be.embeddable.StudentExamId;

public class StudentExamDto {
	
	private StudentExamId id;
	private SimpleStudentDto student;
	private SimpleExamDto exam;
	private Date createdOn = new Date();
	
	public StudentExamDto() {
		
	}

	public StudentExamDto(StudentExamId id, SimpleStudentDto student, SimpleExamDto exam, Date createdOn) {
		super();
		this.id = id;
		this.student = student;
		this.exam = exam;
		this.createdOn = createdOn;
	}

	public StudentExamId getId() {
		return id;
	}

	public void setId(StudentExamId id) {
		this.id = id;
	}

	public SimpleStudentDto getStudent() {
		return student;
	}

	public void setStudent(SimpleStudentDto student) {
		this.student = student;
	}

	public SimpleExamDto getExam() {
		return exam;
	}

	public void setExam(SimpleExamDto exam) {
		this.exam = exam;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		StudentExamDto other = (StudentExamDto) obj;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (exam == null) {
			if (other.exam != null)
				return false;
		} else if (!exam.equals(other.exam))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentExamDto [id=" + id + ", student=" + student + ", exam=" + exam + ", createdOn=" + createdOn
				+ "]";
	}
	
	
}
