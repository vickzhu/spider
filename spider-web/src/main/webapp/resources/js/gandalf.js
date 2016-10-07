$(document).ready(function(){
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$.ajaxSetup({
		complete:function(xhr, ts){
			var status = xhr.status;
			if(status == 200){
				return;
			}
			if(status == 500){
				alert("系统错误，请稍后再试!");
			} else if (status == 499){
				alert("登录超时，请重新登录！");
			} else if (status == 405){
				alert("访问方法不对！");			
			} else if (status == 404){
				alert("您访问的资源未找到！");
			} else if (status == 403){
				alert("抱歉，禁止访问!");
			} else {
				alert("未知异常["+status+"]，请稍后再试！");
			}
		}
	});
})