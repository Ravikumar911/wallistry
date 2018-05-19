	$("#sendSubscriptionEmail").click(function(){
		var email = $("#emailSubscription").val();
		console.log(email)
		$.ajax({
			  method: "POST",
			  url: "updateSubscriber",
			  data: { email: email, location: "Boston" }
			})
			  .done(function( msg ) {
			    alert("Subscription was"+ msg);
			  });
	})