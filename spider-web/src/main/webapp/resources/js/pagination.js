function Pagination(currentPage, totalPage, callback, style) {
	this.currentPage = currentPage;
	this.totalPage = totalPage;
	this.btnCount = 5;
	this.callback = callback;
	if(style){
		this.style=style;
	}else{
		this.style={"fontColor":"#333333",
					"borderColor":"#ceced0",
					"bgColor":"white",
					"selectedFontColor":"white",
					"selectedBorderColor":"#ceced0",
					"selectedBgColor":"#c52937"
				};
	}
	this.getHtml = function() {
		window.pagination_callback = this.callback;
		if(currentPage != null && isNaN(currentPage)){
			alert("错误页码!");
			this.currentPage=1;
		}else{
			this.currentPage = parseInt(this.currentPage);
		}
		if(this.currentPage > this.totalPage){
			this.currentPage = this.totalPage;
		}
		var str = "<form id='form_pagination' onsubmit='if(document.getElementById(\"pagination_input\").value.match(\"^\\\\d+$\")==null||document.getElementById(\"pagination_input\").value==\"0\"){alert(\"页码错误\");return false;} if(document.getElementById(\"pagination_input\").value>"+this.totalPage+"){window.pagination_callback("+this.totalPage+");return false;} window.pagination_callback(document.getElementById(\"pagination_input\").value); return false;' action='"+this.href+"' method='post'>";
		str+="<div style='text-align: right;height:25px;'>";
		str+="<span style='font-size:14px;color: "+this.style.fontColor+"; height: 25px; '>第"+this.currentPage+"/"+this.totalPage+"页</span>&nbsp;";
		if(this.currentPage>1){
			str+="<span onclick='window.pagination_callback(1)' style='cursor:pointer;background: "+this.style.bgColor+"; color: "+this.style.fontColor+"; padding:2px 5px; border: 1px solid "+this.style.borderColor+";display:inline-block;'>首页</span>";
			str+=" <span onclick='window.pagination_callback("+(this.currentPage-1)+")' style='cursor:pointer; background: "+this.style.bgColor+"; color: "+this.style.fontColor+"; padding:2px 5px; border: 1px solid "+this.style.borderColor+";display:inline-block;'>上一页</span> ";
		}
		var start = 1;
		var end = 0;
		if(this.currentPage <= Math.floor(this.btnCount/2)){
			end=this.totalPage>this.btnCount?this.btnCount:this.totalPage;
		}else if((this.currentPage+Math.floor(this.btnCount/2))<=this.totalPage){
			end = this.currentPage + Math.floor(this.btnCount/2);
		}else{
			end = this.totalPage;
		}
		if(this.currentPage<this.totalPage){
			str+=" <span onclick='window.pagination_callback("+(this.currentPage+1)+")' style='cursor:pointer; background: "+this.style.bgColor+"; color: "+this.style.fontColor+"; border: 1px solid "+this.style.borderColor+";padding:2px 5px;display:inline-block;'>下一页</span>";
			str+=" <span onclick='window.pagination_callback("+this.totalPage+")' style='cursor:pointer;background: "+this.style.bgColor+"; color: "+this.style.fontColor+"; border: 1px solid "+this.style.borderColor+";padding:2px 5px;display:inline-block;'>尾页</span>";
		}
		str+=" <input id='pagination_input' name='page' type='text' style='width: 30px;"+this.style.borderColor+";margin:0px;padding:0px;'/>";
		str+="</div></form>";
		return str;
	}
}