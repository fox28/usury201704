package cn.kkk.usury.model.bean;

import java.io.Serializable;

public class Result implements Serializable {
	private int retCode = -1;
	private String retMsg;
	private Object retData;
	public Result() {
	}
	public Result(String retMsg, int retCode){
		this.retMsg = retMsg;
		this.retCode = retCode;
	}
	public Result(int retCode, String retMsg, Object retData) {
		super();
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.retData = retData;
	}
	public int getRetCode() {
		return retCode;
	}
	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public Object getRetData() {
		return retData;
	}
	public void setRetData(Object retData) {
		this.retData = retData;
	}
	@Override
	public String toString() {
		return "Result [retCode=" + retCode + ", retMsg=" + retMsg + ", retData=" + retData + "]";
	}
}
