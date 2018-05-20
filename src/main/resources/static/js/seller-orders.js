$(".order-status div").on('click',function(){
	var status = $(this).text().toLowerCase();
	$.ajax({
		url:"fetchOrdersStatus",
		type:"GET",
		data:{status},
		success: function(data,success){
			$("#ordersTable tbody").remove();
			var jsonData = $.parseJSON(data);
			$.get("fetchPendingOrders",function(data,status){
				function formatItem(item) {
			
					if(item.status=="pending"){
						return '<td><input class="chkRow" type="checkbox"/></td><td>'+item.orderno + '</td> <td> ' + item.date + ' </td><td>' + item.customer_name+'</td><td>'+item.price+'</td><td>'+item.quantity+"</td><td>"+item.pincode+"</td><td>"+item.paymentmode+"</td><td id="+ item.orderno +"><span><button class='order-confirm'>Confirm</button></span><span><button class='order-cancel'>Cancel</button></span></td>";
					}else if(item.status=="active"){
						return '<td><input class="chkRow" type="checkbox"/></td><td>'+item.orderno + '</td> <td> ' + item.date + ' </td><td>' + item.customer_name+'</td><td>'+item.price+'</td><td>'+item.quantity+"</td><td>"+item.pincode+"</td><td>"+item.paymentmode+"</td><td id="+ item.orderno +"><span><button class='order-dispatch'>Dispatch</button></span></td>";

					}else if(item.status=="completed"){
						return '<td><input class="chkRow" type="checkbox"/></td><td>'+item.orderno + '</td> <td> ' + item.date + ' </td><td>' + item.customer_name+'</td><td>'+item.price+'</td><td>'+item.quantity+"</td><td>"+item.pincode+"</td><td>"+item.paymentmode+"</td><td>Delivery date to be done</td>"; 
					}else if(item.status=="cancelled"){
						return '<td><input class="chkRow" type="checkbox"/></td><td>'+item.orderno + '</td> <td> ' + item.date + ' </td><td>' + item.customer_name+'</td><td>'+item.price+'</td><td>'+item.quantity+"</td><td>"+item.pincode+"</td><td>"+item.paymentmode+"</td><td>"+ item.cancelReason +"</td>";
					}
					}
				$.each(jsonData, function (key, item) {
					console.log(jsonData);
		            $('<tr>', { html: formatItem(item) }).appendTo($("#ordersTable"));
		        });		
			})
		}
	});
	
	$(".order-status div").removeClass('active');
	if($(this).text() == "PENDING"){
		$('.bulk-action-pending').css({
			'display':'block'
		});
		$("#status-action-th").text("Status");
		$('.bulk-action-active').css({
			'display':'none'
		});
	}else if($(this).text() == "ACTIVE"){
		$('.bulk-action-pending').css({
			'display':'none'
		});
		$("#status-action-th").text("Action");
		$('.bulk-action-active').css({
			'display':'block'
		});
		
	}else if($(this).text() == "CANCELLED"){
		$('.bulk-action-pending').css({
			'display':'none'
		});
		$('.bulk-action-active').css({
			'display':'none'
		});
		$("#status-action-th").text("Reason");
	}
	else if ($(this).text() == "COMPLETED"){
		$('.bulk-action-pending').css({
			'display':'none'
		});
		$('.bulk-action-active').css({
			'display':'none'
		});
		$("#status-action-th").text("Delivery date");
	}
	$(this).addClass('active');
	
});
$(document).ready(function(){
	
$("#selectall").click(function () {
    var chkAll = this;
    var chkRows = $("#ordersTable").find(".chkRow");
    chkRows.each(function () {
        $(this)[0].checked = chkAll.checked;
    });
});
$(".chkRow").click(function () {
    var chkAll = $("#selectall");
    chkAll.attr("checked", "checked");
    var chkRows = $("#ordersTable").find(".chkRow");
    chkRows.each(function () {
        if (!$(this).is(":checked")) {
            chkAll.removeAttr("checked", "checked");

        }
    });
});
$.get("fetchPendingOrders",function(data,status){
		function formatItem(item) {
			return '<td><input class="chkRow" type="checkbox"/></td><td class"dynamicOrderno">'+item.orderno + '</td> <td> ' + item.date + ' </td><td>' + item.customer_name+'</td><td>'+item.price+'</td><td>'+item.quantity+"</td><td>"+item.pincode+"</td><td>"+item.paymentmode+"</td><td id="+ item.orderno +"><span><button class='order-confirm'>Confirm</button></span><span><button class='order-cancel'>Cancel</button></span></td>";
			}
		
		$.each(JSON.parse(data), function (key, item) {
			
            $('<tr>', { html: formatItem(item) }).appendTo($("#ordersTable"));
        });		
	})
});


$(function() {
    var dialog, form,
	dialog = $( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 300,
	      width: 350,
	      modal: true,
	      buttons: {
	        Cancel: function() {
	          dialog.dialog( "close" );
	        }
	      },
	      close: function() {
	        $("form")[0].reset();
	      }
	    });
	
	form = dialog.find( "form" ).on( "submit", function( event ) {
	      event.preventDefault();
	    });
	
	confirmOrder = $("#confirmOrderDialog").dialog({
		autoOpen: false,
	    width: 350,
	    modal: true,
	    buttons: {
	    	Yes: function(){
	    		var orderno = $("#confirmOrderno").val();
	    		console.log("orederf"+orderno);
	    		$.ajax({
	    			url:"updateOrderStatus",
	    			type:"GET",
	    			data:{orderno:orderno,status:"active"},
	    			success:function(data){
	    				$(".order-status .active").click();
	    				$("#confirmOrderDialog").dialog("close");
	    			},
	    			failure:function(){
	    				alert("order not updated something went wrong!");
	    			}
	    		})
	    	},
	    	No: function(){
	    		$("#confirmOrderDialog").dialog("close");
	    	}
	    }
	    
	});
	dispatchOrder = $("#confirmDispatchDialog").dialog({
		autoOpen: false,
	    width: 350,
	    modal: true,
	    buttons: {
	    	Yes: function(){
	    		var orderno = $("#dispatchOrderno").val();
	    		$.ajax({
	    			url:"updateOrderStatus",
	    			type:"GET",
	    			data:{orderno:orderno,status:"completed"},
	    			success:function(){
	    				$(".order-status .active").click();
	    				$("#confirmDispatchDialog").dialog("close")
	    			},
	    			failure:function(){
	    				alert("order dispatch failed something went wrong!");
	    			}
	    		})
	    	}
	    }
	});
	cancelOrder = $("#cancelOrderDialog").dialog({
		autoOpen: false,
	    width: 350,
	    modal: true,
	    buttons: {
	    	Submit: function(){
	    		var orderno = $("#cancelOrderno").val();
	    		var reason = $("#cancelReason").val();
	    		console.log(reason);
	    		console.log("orederf"+orderno);
	    		$.ajax({
	    			url:"updateOrderStatus",
	    			type:"GET",
	    			data:{orderno:orderno,status:"cancelled",reason:reason},
	    			success:function(data){
	    				$(".order-status .active").click();
	    				$("#cancelOrderDialog").dialog("close");
	    			},
	    			failure:function(){
	    				alert("order not updated something went wrong!");
	    			}
	    		})
	    	},
	    	Cancel: function(){
	    		$("#confirmOrderDialog").dialog("close");
	    	}
	    }
	});
	
	$("#ordersTable").on('click',"button.order-confirm",function(){
		var orderno = $(this).parent().parent()[0].id;
		$("#confirmOrderno").val(orderno);
		confirmOrder.dialog( "open" );
	});
	$("#ordersTable").on('click',"button.order-cancel",function(){
		var orderno = $(this).parent().parent()[0].id;
		$("#cancelOrderno").val(orderno);
		cancelOrder.dialog( "open" );
	});
	$("#ordersTable").on('click',"button.order-dispatch",function(){
		var orderno = $(this).parent().parent()[0].id;
		$("#dispatchOrderno").val(orderno);
		dispatchOrder.dialog("open");
	});
	var ordernos = [];
	$("#bulk-confirm-btn").on('click',function(){
		$('#ordersTable tbody').find('input[type="checkbox"]:checked').each(function () {
		       //this is the current checkbox
			ordernos.push($(this).closest('tr').find('td:nth-child(2)').text());
		    });
		if(ordernos.length != 0){
			bulkOrder.dialog("open");
		}
		

	});
		$("#bulk-dispatch-btn").on('click',function(){
		$('#ordersTable tbody').find('input[type="checkbox"]:checked').each(function () {
		       //this is the current checkbox
			ordernos.push($(this).closest('tr').find('td:nth-child(2)').text());
			console.log(ordernos)
		    });
		if(ordernos.length != 0){
			bulkOrder.dialog("open");
		}
		

	});
	bulkOrder = $("#bulkOrderAction").dialog(
				{
				autoOpen: false,
		    width: 350,
		    modal: true,
		    buttons: {
		    	Yes: function(){
		    		var status ="";
		    		if($(".order-status .active").text().toLowerCase() == "pending"){
		    		status = "active";
		    		}else if($(".order-status .active").text().toLowerCase() == "active"){
		    			status = "completed";
		    		}
		    		$.ajax({
		    			url:"bulkorderaction",
		    			type:"POST",
		    			datatype:'json',
		    			data:{ordernos:ordernos,status:status},
		    			success:function(){
		    			
		    				$("#bulkOrderAction").dialog("close");
		    				$(".order-status .active").click();
		    				$("#selectall").removeAttr("checked","checked");
		    				ordernos.length = 0;
		    			},
		    			failure:function(){
		    				$("#bulkOrderAction").dialog("close");
		    				alert("Bulk confirmation failed something went wrong.")
		    			}
		    		})
		    	}
		    }
		});
	
});

