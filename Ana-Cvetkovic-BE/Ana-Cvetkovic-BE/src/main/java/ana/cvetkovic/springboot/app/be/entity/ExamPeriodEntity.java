package ana.cvetkovic.springboot.app.be.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_period")
public class ExamPeriodEntity implements MyEntity{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date startPeriod;
	private Date endPeriod;
	private boolean active;
	
	public ExamPeriodEntity() {
		
	}

	public ExamPeriodEntity(Long id, String name, Date startPeriod, Date endPeriod, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.startPeriod = startPeriod;
		this.endPeriod = endPeriod;
		this.active = active;
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

	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((endPeriod == null) ? 0 : endPeriod.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((startPeriod == null) ? 0 : startPeriod.hashCode());
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
		ExamPeriodEntity other = (ExamPeriodEntity) obj;
		if (active != other.active)
			return false;
		if (endPeriod == null) {
			if (other.endPeriod != null)
				return false;
		} else if (!endPeriod.equals(other.endPeriod))
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
		if (startPeriod == null) {
			if (other.startPeriod != null)
				return false;
		} else if (!startPeriod.equals(other.startPeriod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExamPeriodEntity [id=" + id + ", name=" + name + ", startPeriod=" + startPeriod + ", endPeriod="
				+ endPeriod + ", active=" + active + "]";
	}
	
}
