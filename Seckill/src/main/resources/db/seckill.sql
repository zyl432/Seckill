DELIMITER $$

create procedure seckill.execute_seckill(in v_seckill_id bigint,in v_phone bigint,in v_kill_time timestamp,out r_result int)
	begin 
		declare insert_count int default 0;
	start transaction;
	insert ignore into success_seckilled(seckill_id,user_phone,create_time,state)
		value(v_seckill_id,v_phone,v_kill_time,0);
	select row_count() into insert_count;
	if(insert_count = 0) then
		rollback;
		set r_result = -1;
	elseif(insert_count < 0) then
		rollback;
		set r_result = -2;
	else
		update seckill
		set number = number - 1
		where seckill_id = v_seckill_id
		and number > 0
		and	end_time > v_kill_time
		and start_time < v_kill_time;
		select row_count() into insert_count;
		if(insert_count = 0) then
			rollback;
			set r_result = 0;
		elseif(insert_count < 0) then
			rollback;
			set r_result = -1;
		else
			commit;
			set r_result = 1;
		end if;
	end if;
end;

$$

delimiter ;
		
			
		
		
	