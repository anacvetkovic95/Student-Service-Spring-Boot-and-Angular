package ana.cvetkovic.springboot.app.be.exception;

import ana.cvetkovic.springboot.app.be.dto.MyDto;

public class MyEntityExistException extends MyApplicationException {

	private static final long serialVersionUID = 1L;
	
	private final MyDto entity;

	public MyEntityExistException(String message, MyDto entity) {
		super(message);
		this.entity  = entity;
	}
	
	public Object getEntity() {
		return entity;
	}
}
