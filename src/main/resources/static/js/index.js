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
	    autoplay:true,
	    loop:true,
	    nav:true,
	    items:1,
	    navText : ['<i class="fa fa-chevron-left" aria-hidden="true"></i>','<i class="fa fa-chevron-right" aria-hidden="true"></i>']
	});
});