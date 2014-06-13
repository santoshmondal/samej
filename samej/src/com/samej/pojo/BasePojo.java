package com.samej.pojo;

import java.io.Serializable;
import java.util.Date;

public class BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _id;
	private Date ctime;
	private Date utime;

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

}
