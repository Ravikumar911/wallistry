$('.btn-number').click(function(e) {
	e.preventDefault();

	fieldName = $(this).attr('data-field');
	type = $(this).attr('data-type');
	var input = $("input[name='" + fieldName + "']");
	var currentVal = parseInt(input.val());
	if (!isNaN(currentVal)) {
		if (type == 'minus') {

			if (currentVal > input.attr('min')) {
				input.val(currentVal - 1).change();
			}
			if (parseInt(input.val()) == input.attr('min')) {
				// $(this).attr('disabled', true);
			}

		} else if (type == 'plus') {

			if (currentVal < input.attr('max')) {
				input.val(currentVal + 1).change();
			}
			if (parseInt(input.val()) == input.attr('max')) {
				// $(this).attr('disabled', true);
			}

		}
	} else {
		input.val(0);
	}
});
$("input[type='number']").keypress(function(evt) {
	evt.preventDefault();
});
$(document).ready(function() {
	 $('#fullImg img')
	    .wrap('<span style="display:inline-block"></span>')
	    .css('display', 'block')
	    .parent()
	    .zoom();
});

$('.previewImg').click(function(e) {
	var newImage = $(this).attr('href');
	$('#fullImg img').attr({
		src : newImage
	});
	return false;
});

$('.thumbnail-slider').bxSlider({
	mode : 'vertical',
	minSlides : 3,
	slideMargin : 10,
	infiniteLoop : false,
	pager : false,
	prevText : "<i class='arrow up'></i>",
	nextText : "<i class='arrow down'></i>",
	hideControlOnEnd : true
});
if ($(window).width() > 740) {
//	 $('#fullImg img').zoom();
};