/**
 * 
 */
package com.seckill.exception;

/** 
 * @ClassName: SeckillException 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月25日 下午5:23:48 
 *  
 */
public class SeckillException extends RuntimeException {

	public SeckillException(String message)
	{
		super(message);
	}
	
	public SeckillException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
