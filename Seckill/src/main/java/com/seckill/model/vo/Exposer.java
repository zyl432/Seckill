/**
 * 
 */
package com.seckill.model.vo;

/** 
 * @ClassName: Exposer 
 * @Description: TODO
 * @author lionel zhangyongliang@tiantanhehe.com
 * @date 2016年10月24日 下午2:49:36 
 *  
 */
public class Exposer {

	private boolean isExposed;
	
	private String md5;
	
	private Long seckillId;
	
	private Long start;
	
	private Long end;
	
	private Long now;
	
	public Exposer()
	{
		
	}

	public Exposer(boolean isExposed, String md5, Long seckillId) {
//		super();
		this.isExposed = isExposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public Exposer(boolean isExposed, Long seckillId, Long start,
			Long end, Long now) {
//		super();
		this.isExposed = isExposed;
		this.seckillId = seckillId;
		this.start = start;
		this.end = end;
		this.now = now;
	}

	public Exposer(boolean isExposed, Long seckillId) {
//		super();
		this.isExposed = isExposed;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return isExposed;
	}

	public void setExposed(boolean isExposed) {
		this.isExposed = isExposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public Long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public Long getNow() {
		return now;
	}

	public void setNow(Long now) {
		this.now = now;
	}

	@Override
	public String toString() {
		return "Exposer [isExposed=" + isExposed + ", md5=" + md5
				+ ", seckillId=" + seckillId + ", start=" + start + ", end="
				+ end + ", now=" + now + "]";
	}
	
	
}
