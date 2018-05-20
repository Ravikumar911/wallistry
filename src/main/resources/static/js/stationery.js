$(document).ready(function(){
	$.get("/getStationeryProducts",function(data,status){
		$.each(JSON.parse(data), function (key, item) {
			var image = "";
			var hrefVal = "";
			if(item.product_name == "Kriya-Wooden notebook (mini)"){
				image = "tab_notebook";
				hrefVal = "/kriyaa6?price="+item.product_price
			}else if(item.product_name == "Kriya-Wooden notebook (Premium)"){
				image = "tab_notebook";
				hrefVal = "/kriyaa5?price="+item.product_price
			}else if(item.product_name == "Punai-DIY notebook"){
				image = "tab_coasters";
				hrefVal = "/diybook?price="+item.product_price
			}
            var otherElement = $('<div class="col-lg-4 col-sm-12 homeware-item">'
					+'<div class="col-lg-12 card">'
					+'<a href="'+ hrefVal +'"><img class="item-img-size center-block" src="/images/'+image+'.jpg" alt="txt" /></a>'
					+'<div class="text-center item-type">'+ item.product_name +'</div>'
					+'<div class="text-center"> Rs.'+ item.product_price +'</div>'
					+'</div></div>');
            $("#stationery-items").append(otherElement);
        });
	});
});