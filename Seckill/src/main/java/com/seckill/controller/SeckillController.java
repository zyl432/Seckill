/**
 * 
 */
package com.seckill.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.enums.StateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.model.Seckill;
import com.seckill.model.vo.Exposer;
import com.seckill.model.vo.SeckillExecution;
import com.seckill.model.vo.SeckillResult;
import com.seckill.service.SeckillService;

/** 
 * @ClassName: SeckillController 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月21日 下午2:34:00 
 *  
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
	private static final Logger logger = LoggerFactory.getLogger(SeckillController.class);
	
	@Autowired
	@Qualifier("seckillServiceImpl")
	SeckillService seckillService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model)
	{
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId")Long seckillId,Model model)
	{
		if(seckillId==null)
		{
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.selectById(seckillId);
		if(seckill==null)
		{
			return "redirect:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time()
	{
		Date now = new Date();
		return new SeckillResult<>(true,now.getTime());
	}
	
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId)
	{
		SeckillResult<Exposer> result;
		
		try {
			Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
			result = new SeckillResult<>(true,exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result = new SeckillResult<>(false,e.getMessage());
		}
		return result;
	}
	@RequestMapping(value="/{seckillId}/{md5}/execute",method=RequestMethod.POST,produces={"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId")Long seckillId,
												@PathVariable("md5")String md5,
												@CookieValue(value="killPhone",required=false)Long userPhone)
	{
		if(userPhone == null)
		{
			return new SeckillResult<>(false,"未注册");
		}
		
		try {
			SeckillExecution execution = seckillService.executeSeckillByProcedure(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true,execution);
		} catch (SeckillCloseException e1) {
			SeckillExecution execution = new SeckillExecution(seckillId,StateEnum.END);
			return new SeckillResult<>(true,execution);
		} catch (RepeatKillException e2) {
			SeckillExecution execution = new SeckillExecution(seckillId,StateEnum.REPEAT_KILL);
			return new SeckillResult<>(true,execution);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			SeckillExecution execution = new SeckillExecution(seckillId,StateEnum.INNER_ERROR);
			return new SeckillResult<>(true,execution);
		}
	}
}
