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