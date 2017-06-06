$(document).ready(function(){
	
	
	$(".owl-carousel").owlCarousel({
		loop:true,
		autoplay:true,
		autoplayTimeout:2000,
	    items:1,
	    animateOut: 'fadeOut',
	    video:true
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