/**
 * 
 */
package com.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.seckill.model.Seckill;

/** 
 * @ClassName: SeckillDao 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月20日 下午5:41:28 
 *  
 */
public interface SeckillDao {

	public int updateNumber(@Param("secKillId")long secKillId,@Param("killDate")Date killDate);
	
	public Seckill selectById(long secKillId);
	
	public List<Seckill> selectAll(@Param("offset")int offset,@Param("limit")int limit);
	
	public void seckillProcedure(Map<String,Object> paramMap);
}
