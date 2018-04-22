$(document).ready(function(){
	$.get("dashboardinfo",function(data, status){
		alert(data)
	})
})
$(".uploadImage").click(function(e){
	$(this).prev().children()[0].click();
});
$('input[type=file]').on('change', fileUpload);
function fileUpload(event){
	files = event.target.files;
	fileName = event.target.id;
	console.log(fileName)
	var data = new FormData(); 
	 var error = 0; 
	 for (var i = 0; i < files.length; i++) {
		  var file = files[i];
		  console.log(file)
		  if(!file.type.match('image.*')) { 
			  alert("oly imges")
			  error = 1;
		  }else{
			  console.log("kjkjakja" + fileName)
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