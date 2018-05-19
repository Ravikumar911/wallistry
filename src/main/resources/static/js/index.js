$(document).ready(function(){
	
	
	$("#home-owl-carousel").owlCarousel({
		loop:true,
		autoplay:true,
		autoplayTimeout:2000,
	    items:1,
	    animateOut: 'fadeOut',
	    video:true
	});
	$(".owl-carousel-gift").owlCarousel({
		margin:10,
	    autoWidth:false,
	    nav:true,
	    items:1,
	});
});