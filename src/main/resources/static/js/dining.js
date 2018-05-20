$(document).ready(function(){
	$.get("/getDiningProducts",function(data,status){
		$.each(JSON.parse(data), function (key, item) {
			var image = "";
			var hrefVal = "";
			if(item.product_name == "Bhumi-terracotta Waterbottle"){
				image = "tab_waterbottle";
				hrefVal = "/bottle"
			}else if(item.product_name == "Vanna wooden coaster"){
				image = "tab_coasters";
				hrefVal = "/coasters"
			}
            var otherElement = $('<div class="col-lg-4 col-sm-12 homeware-item">'
					+'<div class="col-lg-12 card">'
					+'<a href="'+ hrefVal +'"><img class="item-img-size center-block" src="/images/'+image+'.jpg" alt="txt" /></a>'
					+'<div class="text-center item-type">'+ item.product_name +'</div>'
					+'<div class="text-center"> Rs.'+ item.product_price +'</div>'
					+'</div></div>');
            $("#dining-items").append(otherElement);
        });
	});
});