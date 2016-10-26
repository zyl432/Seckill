/**
 * 
 */
package com.seckill.dao.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.model.SuccessKilled;

/** 
 * @ClassName: SuccessKilledDaoTest 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月21日 上午10:05:08 
 *  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SuccessKilledDaoTest {

	@Resource
	SuccessKilledDao successKilledDao;
	/**
	 * Test method for {@link com.seckill.dao.SuccessKilledDao#insertSuccessKilled(long, long)}.
	 */
	@Test
	public void testInsertSuccessKilled() {
		int i = successKilledDao.insertSuccessKilled(1000L, 12345678901L);
		System.out.println("i="+i);
	}

	/**
	 * Test method for {@link com.seckill.dao.SuccessKilledDao#selectByIdWithSeckill(long, long)}.
	 */
	@Test
	public void testSelectByIdWithSeckill() {
		SuccessKilled successKilled = successKilledDao.selectByIdWithSeckill(1000L, 12345678901L);
		System.out.println("successKilled="+successKilled);
	}

}
