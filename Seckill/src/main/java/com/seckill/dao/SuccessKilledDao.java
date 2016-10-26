/**
 * 
 */
package com.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.seckill.model.SuccessKilled;

/** 
 * @ClassName: SuccessKilledDao 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月21日 上午9:36:53 
 *  
 */
public interface SuccessKilledDao {
	
	int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	SuccessKilled selectByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}
