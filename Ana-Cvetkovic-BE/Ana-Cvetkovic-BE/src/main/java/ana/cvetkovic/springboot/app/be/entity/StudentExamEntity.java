package ana.cvetkovic.springboot.app.be.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import ana.cvetkovic.springboot.app.be.embeddable.StudentExamId;

@Entity
@Table(name = "student_exam")
public class StudentExamEntity {
	@EmbeddedId
	private StudentExamId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
	private StudentEntity student;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("examId")
	private ExamEntity exam;
	
	@Column(name = "created_on")
    private Date createdOn = new Date();
	
	public StudentExamEntity() {}

	public StudentExamEntity(StudentEntity student, ExamEntity exam) {
		super();
		this.id = new StudentExamId(student.getId(), exam.getId());
		this.student = student;
		this.exam = exam;
	}

	public StudentExamId getId() {
		return id;
	}

	public void setId(StudentExamId id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
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
		return Objects.hash(student, exam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentExamEntity other = (StudentExamEntity) obj;
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
		return "StudentExamEntity [id=" + id + ", student=" + student + ", exam=" + exam + ", createdOn=" + createdOn
				+ "]";
	}

}
