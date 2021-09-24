package ana.cvetkovic.springboot.app.be.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class ExamEntity implements MyEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private ExamPeriodEntity examPeriod;
	@ManyToOne
	private SubjectEntity subject;
	@ManyToOne
	private ProfessorEntity professor;
	private Date examDate;
	
	@OneToMany(
	        mappedBy = "exam",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<StudentExamEntity> students = new ArrayList<>();
	
	public ExamEntity() {
		
	}

	public ExamEntity(Long id, ExamPeriodEntity examPeriod, SubjectEntity subject, ProfessorEntity professor,
			Date examDate) {
		super();
		this.id = id;
		this.examPeriod = examPeriod;
		this.subject = subject;
		this.professor = professor;
		this.examDate = examDate;
	}

	public List<StudentExamEntity> getStudents() {
		return students;
	}

	public void setStudents(List<StudentExamEntity> students) {
		this.students = students;
	}

	public ProfessorEntity getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExamPeriodEntity getExamPeriod() {
		return examPeriod;
	}

	public void setExamPeriod(ExamPeriodEntity examPeriod) {
		this.examPeriod = examPeriod;
	}

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
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
		ExamEntity other = (ExamEntity) obj;
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
		return "ExamEntity [id=" + id + ", examPeriod=" + examPeriod + ", subject=" + subject + ", professor="
				+ professor + ", examDate=" + examDate + "]";
	}

	
}
