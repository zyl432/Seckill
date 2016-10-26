/**
 * 
 */
package com.seckill.model.vo;

import com.seckill.enums.StateEnum;
import com.seckill.model.SuccessKilled;

/** 
 * @ClassName: SeckillExecution 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月25日 下午4:11:12 
 *  
 */
public class SeckillExecution {
	private long seckillId;
	private int state;
	private String stateInfo;
	private SuccessKilled successKilled;
	public SeckillExecution() {
//		super();
	}
	public SeckillExecution(long seckillId, int state, String stateInfo) {
//		super();
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	public SeckillExecution(long seckillId,StateEnum states,SuccessKilled successKilled)
	{
		this.seckillId = seckillId;
		this.state = states.getState();
		this.stateInfo = states.getStateInfo();
		this.successKilled = successKilled;
	}
	
	public SeckillExecution(long seckillId,StateEnum states)
	{
		this.seckillId = seckillId;
		this.state = states.getState();
		this.stateInfo = states.getStateInfo();
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}
	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}
	
}
