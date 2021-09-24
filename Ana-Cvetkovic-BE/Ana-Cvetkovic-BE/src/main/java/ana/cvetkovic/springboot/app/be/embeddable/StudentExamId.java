package ana.cvetkovic.springboot.app.be.embeddable;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentExamId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "student_id")
	private Long studentId;
	@Column(name = "exam_id")
	private Long examId;
	
	public StudentExamId() {
				
	}

	public StudentExamId(Long studentId, Long examId) {
		super();
		this.studentId = studentId;
		this.examId = examId;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId,examId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentExamId other = (StudentExamId) obj;
		if (examId == null) {
			if (other.examId != null)
				return false;
		} else if (!examId.equals(other.examId))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		return true;
	}
	
}
