var seckill = {
		URL:{
			now:function()
			{
				return '/Seckill/seckill/time/now';
			},
			exposer:function(seckillId)
			{
				return '/Seckill/seckill/'+seckillId+'/exposer';
			},
			excution:function(seckillId,md5)
			{
				return '/Seckill/seckill/'+seckillId+'/'+md5+'/excution';
			}
		},
		handleSeckill:function(seckillId,node)
		{
			node.hide().html('<button class="btn bg-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post(seckill.URL.exposer(seckillId),{},function(result){
				if(result && result['success']){
					var exposer = result['data'];
					if(exposer['isExposed'])
					{
						var md5 = exposer['md5'];
						var killUrl = seckill.URL.execution(seckillId,md5);
						$('#killBtn').one('click',function(){
							$(this).addClass("disabled");
							$.post(killUrl,{},function(result){
								if(result && result['success'])
								{
									var killResult = result['data'];
									var state = result['state'];
									var stateInfo  = result['stateInfo'];
									node.html('<span class="label label-success">'+stateInfo+'</span>');
								}
							});
						});
						node.show();
					}
					else
					{
						var now = exposer['now'];
						var start = exposer['start'];
						var end = exposer['end'];
						seckill.countdown(seckillId, now, start, end);
					}
				}
				else
				{
					console.log("result"+result);
				}
			});
		},
		validatePhone:function(phone)
		{
			if(phone && phone.length == 11 && !isNaN(phone))
			{
				return true;
			}
			else
			{
				return false;
			}
		},
		countdown : function(seckillId,nowTime,startTime,endTime)
		{
			var seckillBox = $("#seckill-box");
			if(nowTime>endTime)
			{alert("abc");
				seckillBox.html("秒杀结束");
			}else if(nowTime < startTime)
			{alert("abcddd");
				var killTime = new Date(startTime+1000);
				seckillBox.countdown(killTime,function(event){
					var format = event.strftime('秒杀倒计时:%D天%H小时%M分钟%S秒');
					seckillBox.html(format);
				}).on('finish.countdown',function(){
					seckill.handleSeckill(seckillId,seckillBox);
				});
				
			}else
			{alert("abcsss");
				seckill.handleSeckill(seckillId,seckillBox);	
			}
		},
		detail:{
			init : function(params)
			{
				var killPhone = $.cookie('killPhone');
				
				if(!seckill.validatePhone(killPhone))
				{
					var killPhoneModal = $("#killPhoneModal");
					
					killPhoneModal.modal({
						show:true,
						backdrop:'static',
						keyboard:false
					});
					
					$("#killPhoneBtn").click(function(){
						var killphoneKey = $("#killphoneKey").val();
						if(seckill.validatePhone(killphoneKey))
						{
							$.cookie('killPhone',killphoneKey,{expires:7,path:'/Seckill'});
							window.location.reload();
						}
						else
						{
							$("#killphoneMessage").hide().html('<label class="label label-danger">手机号错误</label>').show(300);
						}
					});
				}
				
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['seckillId'];
				
				$.get(seckill.URL.now(),{},function(result){
					if(result && result['success'])
					{
						var nowTime = result['data'];console.log("now:"+nowTime+" start:"+startTime+" end:"+endTime);
						seckill.countdown(seckillId,nowTime,startTime,endTime);
					}
					else
					{
						console.log("result:"+result);
					}
				});
			}
		}
};