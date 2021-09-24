package ana.cvetkovic.springboot.app.be.dto;

import java.io.Serializable;

public class SimpleSubjectDto implements Serializable, MyDto{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	
	public SimpleSubjectDto() {
		
	}

	public SimpleSubjectDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SimpleSubjectDto [id=" + id + ", name=" + name + "]";
	}
	
}
