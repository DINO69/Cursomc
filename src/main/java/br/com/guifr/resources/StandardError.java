package br.com.guifr.resources;

import java.io.Serializable;

public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer status;
	private String msg;
	private Long timesStamp;
	
	public StandardError(Integer status, String msg, Long timesStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timesStamp = timesStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimesStamp() {
		return timesStamp;
	}

	public void setTimesStamp(Long timesStamp) {
		this.timesStamp = timesStamp;
	}
	
	

}
