/**
 * 
 */
package com.seckill.dao.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillDao;
import com.seckill.model.Seckill;

/** 
 * @ClassName: SeckillDaoTest 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月20日 下午8:33:17 
 *  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class SeckillDaoTest {

	@Resource
	SeckillDao seckillDao;
	/**
	 * Test method for {@link com.seckill.dao.SeckillDao#updateNumber(long, java.util.Date)}.
	 */
	@Test
	public void testUpdateNumber() {
		int i = seckillDao.updateNumber(1000L, new Date());
		System.out.println("i="+i);
	}

	/**
	 * Test method for {@link com.seckill.dao.SeckillDao#selectById(long)}.
	 */
	@Test
	public void testSelectById() {
		long id=1000L;
		Seckill seckill = seckillDao.selectById(id);
		System.out.println("number:"+seckill.getName());
		System.out.println("seckill:"+seckill);
	}

	/**
	 * Test method for {@link com.seckill.dao.SeckillDao#selectAll(int, int)}.
	 */
	@Test
	public void testSelectAll() {
		List<Seckill> seckillList = seckillDao.selectAll(1, 3);
		
		for(Seckill seckill:seckillList)
		{
			System.out.println("seckill:"+seckill);
		}
	}

	/**
	 * Test method for {@link com.seckill.dao.SeckillDao#seckillProcedure(java.util.Map)}.
	 */
	@Test
	public void testSeckillProcedure() {
		fail("Not yet implemented");
	}

}
