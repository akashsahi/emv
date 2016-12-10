package com.akash.evm.exception;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7683029990811903181L;
	private String message;
	private String rootCuase;
	private String resolution;
	private String errorCode;
	private Throwable throwable;

	public BaseException(String message, String rootCuase, String resolution, String errorCode, Throwable throwable) {
		super();
		this.message = message;
		this.rootCuase = rootCuase;
		this.resolution = resolution;
		this.errorCode = errorCode;
		this.throwable = throwable;
	}

	public BaseException(String message, String rootCuase, String resolution, String errorCode) {
		super();
		this.message = message;
		this.rootCuase = rootCuase;
		this.resolution = resolution;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getRootCuase() {
		return rootCuase;
	}

	public String getResolution() {
		return resolution;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Throwable getThrowable() {
		return throwable;
	}

}
