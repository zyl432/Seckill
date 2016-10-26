/**
 * 
 */
package com.seckill.enums;

/** 
 * @ClassName: StateEnums 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月25日 下午4:15:19 
 *  
 */
public enum StateEnum {
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAT_KILL(-1,"重复秒杀"),
	INNER_ERROR(-2,"系统异常"),
	DATA_REWRITE(-3,"数据篡改");
	
	private int state;
	private String stateInfo;
	
	private StateEnum(int state, String stateInfo) {
//		super();
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	
	public static StateEnum stateOf(int index)
	{
		for(StateEnum state : values())
		{
			if(state.getState() == index)
			{
				return state;
			}
		}
		return null;
	}
	
}
