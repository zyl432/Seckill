/**
 * 
 */
package com.seckill.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.seckill.model.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/** 
 * @ClassName: RedisDao 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月24日 下午3:42:55 
 *  
 */
public class RedisDao {
	private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);
	
	private final JedisPool jedisPool;
	private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);
	
	public RedisDao(String ip,int port)
	{
		jedisPool = new JedisPool(ip,port);
	}
	
	public Seckill getSeckill(Long seckillId)
	{
		try
		{
			Jedis jedis = jedisPool.getResource();
			
			try {
				String key = "seckill:"+seckillId;
				
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes!=null)
				{
					Seckill seckill = schema.newMessage();
					
					ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
					return seckill;
				}
			} finally {
				jedis.close();
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	
	public String putSeckill(Seckill seckill)
	{
		try {
			Jedis jedis = jedisPool.getResource();
			
			try {
				String key = "seckill:"+seckill.getSeckillId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(
						LinkedBuffer.DEFAULT_BUFFER_SIZE));
				
				int timeout = 60*60;
				String result = jedis.setex(key.getBytes(), timeout, bytes);
				return result;
			} finally{
				jedis.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
}
