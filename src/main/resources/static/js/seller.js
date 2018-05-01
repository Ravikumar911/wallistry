$(document).ready(function(){
	
	
		$.get("/dashboardInfo",function(data,status){
			var countData = JSON.parse(data);
			$("#customerCount").text(countData[0].customerCount);
			$("#subscriberCount").text(countData[0].subscriberCount);
		})

		$.get("/getInventorydetails",function(data, status){
				function formatItem(item) {
					return '<td>'+item.name + '</td> <td> ' + item.category + ' </td><td>' + item.price+'</td><td>'+item.quantity+'</td><td>'+item.id+"</td><td>"+item.status+"</td><td><button class='editUser' id='product"+item.id+"' >Edit</button></td>";
					}
				
				$.each(data, function (key, item) {

		            $('<tr>', { html: formatItem(item) }).appendTo($("#tbdata"));
		        });
			})
		$.get("/getsubscriptioninfo",function(data,status){
			function formatItem(item) {
				return '<td>'+item.id + '</td> <td> ' + item.date + ' </td><td>' + item.email+'</td>';
				}
			$.each(data, function (key, item) {

	            $('<tr>', { html: formatItem(item) }).appendTo($("#subscriberData"));
	        });
		})
		$.get("/getCustomerinfo",function(data,status){
			var customer = JSON.parse(data);
			
			function formatItem(item) {
				
				return '<td><a class="customerInfo" id='+item.customer_id+'>' +item.customer_name + '</a></td> <td> ' + item.cutomer_totPrice + ' </td><td>' + item.customer_totQty+'</td>';
				}
			$.each(customer, function (key, item) {
	            $('<tr>', { html: formatItem(item) }).appendTo($("#customerData"));
	        });
		})
		
});
/////////////


/////////////

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
	
textUpload = $("#uploadTextcontent").dialog({
	autoOpen: false,
    height: 400,
    width: 450,
    modal: true,
    close: function() {
    	dialog.dialog( "close" );
    },
    open: function() {
    	var paramId = $("#textId").val();
    	$.ajax({
    		url:"fetchBannerText",
    		type:"GET",
    		data:{paramId},
    		success: function(data,success){
    			$("#bannerText").val(data);
    		}
    	})
    },
    buttons: {
    	Update: function(){
    		var paramId = $("#textId").val();
    		var uploadText = $("#bannerText").val();
    		$.ajax({
        		url:"uploadBannerText",
        		type:"POST",
        		data:{paramId,uploadText},
        		success: function(data,success){
        			alert("success");
        		}
        	})
    	},
    	Cancel: function(){
    		$("#uploadTextcontent").dialog("close");
    	}
    }
})	
	
customerData = $("#customer_dialog").dialog({
	 autoOpen: false,
    height: 600,
    width: 850,
    modal: true,
    close: function() {
    	dialog.dialog( "close" );
    },
    open: function(){
    	var cur_customerId = $("#customerId").text();
    	$.ajax({
    		url:"getCustomerBillinginfo",
    		type:"GET",
    		data:{cur_customerId},
    		success:function(data,success){
    			$(".removableClass").parent().remove();
    			var parsedData = JSON.parse(data)
    		$("#cust-info-name").text(parsedData[0].customer_name);
    		$("#cust-info-address").text(parsedData[0].customer_address);
    		$("#cust-info-email").text(parsedData[0].customer_email);
    		$("#cust-info-phone").text(parsedData[0].customer_phone);
    		//for(var i=0; i<parsedData[0].billInfo.length; i++){
    			function formatItem(item){
    				return "<div class='removableClass'><div style='margin:20px 0; text-align:center'>Order Summary <span style='padding:0 20px'>Date: "+ item.date +"</span> <span>Order no:"+ item.orderno +"</span> </div>"+
    				"<table style='width:100%'><tr><th>S.No</th><th>Item</th><th>SKU</th><th>Price</th><th>Quantity</th><th>Total</th></tr><tr><td>  12 " +
    				"</td><td>"+ item.itemname +"</td><td>"+ item.itemname +"</td><td>"+item.price+"</td><td>"+ item.quantity +"</td><td>"+ (item.price*item.quantity) +"</td></tr></table></div>";
    			}
    			$.each(parsedData[0].billInfo, function (key, item) {
    				
    	            $('<div>', { html: formatItem(item) }).appendTo($(".billing-customer-info"));
    	        });
    		//}
    		},
    		failure:function(){
    			alert(cur_customerId)
    		}
    	})
    }
   
});
edit_dialogs = $( "#edit-dialog-form" ).dialog({
    autoOpen: false,
    height: 300,
    width: 350,
    modal: true,
    buttons: {
    	Submit:function(){
    		var name = $("#edit_name").text();
    		var price = $("#edit_price").val();
    		var quantity = $("#edit_quantity").val();
    		var id = $("#prod_id").val();
    		$.ajax({
    			url:"updateProductDetails",
    			type:"POST",
    			data:{prodPrice:price,prodQty:quantity,prodId:id},
    			success:function(data){
    				alert(data);
    			},
    			error:function(data){
    				alert(data);
    			}
    		})
    	},
    	Cancel: function() {
        edit_dialogs.dialog( "close" );
      }
    },
    close: function() {
    	$("form")[0].reset();
    }
  });
   
  edit_form = edit_dialogs.find( "form" ).on( "submit", function( event ) {
    event.preventDefault();
    editUser();
  });
  
  var current_edit_row_details={};
   
   function editUser() {
    var valid = true;
    var tds = $(current_edit_row_details.current_row).children('td');    
    var name = $('#edit_name').val();//current_edit_row_details.name;
    var price = $('#edit_price').val();//current_edit_row_details.email;
    var quantity = $('#edit_quantity').val(); //current_edit_row_details.password;


      tds[0].innerHTML = name;           
      tds[1].innerHTML = price;    
      tds[2].innerHTML = quantity;   
      edit_dialogs.dialog( "close" );
       
    return valid;
  }
   
$("#customerData").on('click','a.customerInfo',function(){
	$("#customerId").text(this.id);
	customerData.dialog( "open" );
})

$(".uploadText").on('click',function(){
	var paramid = $(this).attr('id');
	$("#textId").val(paramid);
	textUpload.dialog( "open" );
})




$("#tbdata").on('click',"button.editUser",function(){
	var tds = $(this).closest('tr').children('td');
	var name = tds[0].innerHTML;
	var price = tds[2].innerHTML;
	var quantity = tds[3].innerHTML;
	var id=tds[4].innerHTML;
	$('#edit_name').text(name);
    $('#edit_price').val(price);
    $('#edit_quantity').val(quantity);
    $("#prod_id").val(id);
    
    current_edit_row_details.name = name;
    current_edit_row_details.price = price;
    current_edit_row_details.quantity = quantity;
    current_edit_row_details.id = id;
    current_edit_row_details.current_row = $(this).closest('tr'); 
     
    edit_dialogs.dialog( "open" );
	
 });


});




$(".uploadImage").click(function(e){
	$(this).prev().children()[0].click();
});
$('input[type=file]').on('change', fileUpload);
function fileUpload(event){
	files = event.target.files;
	fileName = event.target.id;
	var data = new FormData(); 
	 var error = 0; 
	 for (var i = 0; i < files.length; i++) {
		  var file = files[i];
		  if(!file.type.match('image.*')) { 
			  alert("oly imges")
			  error = 1;
		  }else{
			  data.append(fileName,file);
		  }
	 }
	 $.ajax({
		    url: '/uploadBannerImage',
		    data: data,
		    type: 'POST',
		    contentType: false, // NEEDED, DON'T OMIT THIS (requires jQuery 1.6+)
		    processData: false, // NEEDED, DON'T OMIT THIS
		    enctype: 'multipart/form-data',
		    // ... Other options like success and etc
		});
}