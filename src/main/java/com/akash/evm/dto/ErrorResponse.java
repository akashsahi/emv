package com.akash.evm.dto;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5051418703497933501L;
	private String message;
	private String rootCuase;
	private String resolution;
	private String errorCode;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String message, String rootCuase, String resolution, String errorCode) {
		super();
		this.message = message;
		this.rootCuase = rootCuase;
		this.resolution = resolution;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRootCuase() {
		return rootCuase;
	}

	public void setRootCuase(String rootCuase) {
		this.rootCuase = rootCuase;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
