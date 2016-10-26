/**
 * 
 */
package com.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dao.cache.RedisDao;
import com.seckill.enums.StateEnum;
import com.seckill.model.Seckill;
import com.seckill.model.SuccessKilled;
import com.seckill.model.vo.Exposer;
import com.seckill.model.vo.SeckillExecution;
import com.seckill.service.SeckillService;

/** 
 * @ClassName: SeckillServiceImpl 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月21日 下午2:39:00 
 *  
 */
@Component("seckillServiceImpl")
public class SeckillServiceImpl implements SeckillService {
	private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
	@Autowired
	private SeckillDao seckillDao;
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;
	
	private final String salt = "kdhnkdjfo_+%^&&&&#$@JDKDLDMEMSNI=-";
	
	@Override
	public List<Seckill> getSeckillList() {
		List<Seckill> seckills = seckillDao.selectAll(0, 3);
		return seckills;
	}


	@Override
	public Seckill selectById(long seckillId) {
		return seckillDao.selectById(seckillId);
	}

	private String getMD5(long seckillId)
	{
		String base = seckillId + "/" + salt;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
	@Override
	public Exposer exposeSeckillUrl(long seckillId) {
		Seckill seckill = redisDao.getSeckill(seckillId);
		if(seckill == null)
		{
			seckill = getById(seckillId);
			if(seckill == null)
			{
				return new Exposer(false,seckillId);
			}
			else
			{
				redisDao.putSeckill(seckill);
			}
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		if(now.getTime()<startTime.getTime() || now.getTime()>endTime.getTime())
		{
			return new Exposer(false,seckillId,now.getTime(),startTime.getTime(),endTime.getTime());
		}
		String md5 = getMD5(seckillId);
		return new Exposer(true,md5,seckillId);
	}


	@Override
	public Seckill getById(Long seckillId) {

		return seckillDao.selectById(seckillId);
	}



	@Override
	public SeckillExecution executeSeckillByProcedure(long seckillId,
			long userPhone, String md5) {
		if(md5==null || !md5.equals(getMD5(seckillId)))
		{
			return new SeckillExecution(seckillId,StateEnum.DATA_REWRITE);
		}
		
		Date killTime = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", killTime);
		map.put("result", null);
		
		try {
			seckillDao.seckillProcedure(map);
			int result = MapUtils.getInteger(map, "result",-2);
			if(result == 1)
			{
				SuccessKilled sk = successKilledDao.selectByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId,StateEnum.SUCCESS,sk);
			}
			else
			{
				return new SeckillExecution(seckillId,StateEnum.stateOf(result));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new SeckillExecution(seckillId,StateEnum.INNER_ERROR);
		}
	}

}
