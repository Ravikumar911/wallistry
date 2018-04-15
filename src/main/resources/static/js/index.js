$(document).ready(function(){
	
	
	$(".owl-carousel").owlCarousel({
		loop:true,
		autoplay:true,
		autoplayTimeout:2000,
	    items:1,
	    dots:false,
	    animateOut: 'fadeOut',
	    video:true,
	    nav    : true,
	     navText : ["<span class='icon icon-arrow-left'></span>","<span class='icon icon-arrow-right'></span>"]
	   
	});
	
	$("#sendSubscriptionEmail").click(function(){
		$.ajax({
			  method: "POST",
			  url: "some.php",
			  data: { name: "John", location: "Boston" }
			})
			  .done(function( msg ) {
			    alert( "Data Saved: " + msg );
			  });
	})
	
})