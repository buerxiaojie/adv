$(function(){
	//表格行，鼠标放上去变色
	$(".tr:odd").css("background", "#F2F2F2");
	$(".tr:odd").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#FFE1FF");
		}, function(){
			$(this).css("background-color", "#F2F2F2");
		});
	});
	$(".tr:even").each(function(){
		$(this).hover(function(){
			$(this).css("background-color", "#F2F2F2");
		}, function(){
			$(this).css("background-color", "#fff");
		});
	}); 

	/*ie6,7下拉框美化*/
    if ( $.browser.msie ){
    	if($.browser.version == '7.0' || $.browser.version == '6.0'){
    		$('.select').each(function(i){
			   $(this).parents('.select_border,.select_containers').width($(this).width()+5); 
			 });
    		
    	}
    }
 
});