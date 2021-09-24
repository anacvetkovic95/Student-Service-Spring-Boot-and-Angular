package ana.cvetkovic.springboot.app.be.dto;

import java.util.Date;

public class SimpleExamDto {
	private Long id;
	private ExamPeriodDto examPeriod;
	private SubjectDto subject;
	private ProfessorDto professor;
	private Date examDate;
	
	public SimpleExamDto() {
		
	}

	public SimpleExamDto(Long id, ExamPeriodDto examPeriod, SubjectDto subject, Date examDate, ProfessorDto professor) {
		super();
		this.examPeriod = examPeriod;
		this.subject = subject;
		this.examDate = examDate;
		this.id=id;
		this.professor=professor;
	}

	public ProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExamPeriodDto getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodDto examPeriod) {
		this.examPeriod = examPeriod;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result + ((examPeriod == null) ? 0 : examPeriod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		SimpleExamDto other = (SimpleExamDto) obj;
		if (examDate == null) {
			if (other.examDate != null)
				return false;
		} else if (!examDate.equals(other.examDate))
			return false;
		if (examPeriod == null) {
			if (other.examPeriod != null)
				return false;
		} else if (!examPeriod.equals(other.examPeriod))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SimpleExamDto [id=" + id + ", examPeriod=" + examPeriod + ", subject=" + subject + ", professor="
				+ professor + ", examDate=" + examDate + "]";
	}
}
