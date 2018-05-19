	$("#sendSubscriptionEmail").click(function(){
		var email = $("#emailSubscription").val();
		console.log(email)
		if(validateEmail()){
			$.ajax({
				  method: "POST",
				  url: "updateSubscriber",
				  data: { email: email, location: "Boston" }
				})
				  .done(function(data) {
				    if(data == "subscriber exist"){
				    	$("#subscribeError").text("You are already subscribed");
				    	$("#emailSubscription").css({"border-color":"red"})
				    }else if(data == "success"){
				    	$("#subscribeError").text("Thank you for subscribing wallistry");
				    	$("#emailSubscription").css({"border-color":"transparent"})
				    	$("#subscribeError").css({"color":"#000000"})
				    }else{
				    	$("#subscribeError").text("Sorry something went wrong. Try again later! ");
				    	$("#emailSubscription").css({"border-color":"red"})
				    }
				  });
		}
		
	})
	$("#emailSubscription").keypress(function(){
		$("#subscribeError").text("");
		$("#emailSubscription").css({"border-color":"transparent"})
	})
	function validateEmail(){
		
		var email = $("#emailSubscription").val();
		var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		    if (filter.test(email)) {
		        return true;
		    }
		    else {
		    	$("#subscribeError").text("Please enter a valid email address");
		    	$("#emailSubscription").css({"border-color":"red"})
		        return false;
		    }

	}