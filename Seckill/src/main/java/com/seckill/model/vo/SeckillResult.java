/**
 * 
 */
package com.seckill.model.vo;

/** 
 * @ClassName: SeckillResult 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月24日 上午11:36:10 
 *  
 */
public class SeckillResult<T> {
	private boolean success;
	private T data;
	private String error;
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	public boolean isSuccess()
	{
		return success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
