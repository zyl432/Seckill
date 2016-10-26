/**
 * 
 */
package com.seckill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seckill.model.Seckill;
import com.seckill.model.vo.Exposer;
import com.seckill.model.vo.SeckillExecution;


/** 
 * @ClassName: SeckillService 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月21日 下午2:37:48 
 *  
 */
@Service
public interface SeckillService {
    List<Seckill> getSeckillList();
    
    Seckill selectById(long seckillId);
    
    Exposer exposeSeckillUrl(long seckillId);
    
    Seckill getById(Long seckillId);
    
    SeckillExecution executeSeckillByProcedure(long seckillId,long userPhone,String md5);
}
