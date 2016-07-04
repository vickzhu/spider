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
		if(isNaN(currentPage)){
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
		str+="<span style='font-size:14px;color: "+this.style.fontColor+"; height: 25px; '>共"+this.totalPage+"页</span>&nbsp;";
		if(this.currentPage>1){
			str+="<input onclick='window.pagination_callback(1)' type='button' value='首页' style='cursor:pointer;background: "+this.style.bgColor+"; width: 40px; color: "+this.style.fontColor+"; height: 25px; border: 1px solid "+this.style.borderColor+";'/>";
			str+=" <input onclick='window.pagination_callback("+(this.currentPage-1)+")' type='button' value='上一页' style='cursor:pointer; background: "+this.style.bgColor+"; width: 51px; color: "+this.style.fontColor+"; height: 25px; border: 1px solid "+this.style.borderColor+";'/> ";
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
		for (i = (end-this.btnCount+1)>0?(end-this.btnCount+1):1; i <= end; i++) {
			if(this.currentPage==i){
				str+="<input type='button' value='"+i+"' style='background: "+this.style.selectedBgColor+"; width: 25px; color: "+this.style.selectedFontColor+"; height: 25px; border: 1px solid "+this.style.selectedBorderColor+";'/>";
			}else{
				str+="<input onclick='window.pagination_callback("+i+")' type='button' value='"+i+"' style='cursor:pointer;background: "+this.style.bgColor+"; width: 25px; color: "+this.style.fontColor+"; height: 25px; border: 1px solid "+this.style.borderColor+";'/>";
			}
		}
		if(this.currentPage<this.totalPage){
			str+=" <input onclick='window.pagination_callback("+(this.currentPage+1)+")' type='button' value='下一页' style='cursor:pointer; background: "+this.style.bgColor+"; width: 51px; color: "+this.style.fontColor+"; height: 25px; border: 1px solid "+this.style.borderColor+";'/>";
			str+=" <input onclick='window.pagination_callback("+this.totalPage+")' type='button' value='尾页' style='cursor:pointer;background: "+this.style.bgColor+"; width: 40px; color: "+this.style.fontColor+"; height: 25px; border: 1px solid "+this.style.borderColor+";'/>";
		}
		str+=" <input id='pagination_input' name='page' type='text' style='width: 30px; border: 1px solid "+this.style.borderColor+";vertical-align: top;padding:0;'/>";
		str+="</div></form>";
		return str;
	}
}