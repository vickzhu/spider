$(document).ready(function(){
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
})
$.ajaxSetup({
	beforeSend: function(xhr, settings) {
		if (!csrfSafeMethod(settings.type) && sameOrigin(settings.url)) {
			var csrftoken = jQuery.cookie('_c_t');
			xhr.setRequestHeader("X-CSRFToken", csrftoken);
		}
	},
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

/*判断是否为csrf安全方法*/
function csrfSafeMethod(method) {
	return (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
}

/*判断是否同源*/
function sameOrigin(url) {
    var host = document.location.host;
    var protocol = document.location.protocol;  
    var sr_origin = '//' + host;  
    var origin = protocol + sr_origin;
    return (url == origin || url.slice(0, origin.length + 1) == origin + '/') ||  
        (url == sr_origin || url.slice(0, sr_origin.length + 1) == sr_origin + '/') ||
        !(/^(\/\/|http:|https:).*/.test(url));  
} 