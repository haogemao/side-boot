/**
 * 
 */
package com.side.basic.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author gmc
 * @see 数据返回对象，用于返回后端数据对象
 */ 
public class ResultDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 返回码
	 */
	private String retCode;
	
	/**
	 * 返回信息
	 */
	private String retMsg;
	
	/**
	 * 返回数据
	 */
	private List<T> record;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public List<T> getRecord() {
		return record;
	}

	public void setRecord(List<T> record) {
		this.record = record;
	}
	
}