// * * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

// * * * send message * * * 
$(document).ready(function(){
	$('#contact-form').submit(function(event){
		
		// prevent form form default action
		event.preventDefault();
		
		// get form url
	    var $form = $( this ),
	    	url = $form.attr( 'action' );
		
	    // check if all required fields are not null
		if($.trim($('#contact-name').val()) == "" || $.trim($('#contact-email').val()) == "" ||	 $.trim($('#contact-subject').val()) == "" ||
		   $.trim($('#contact-message').val()) == "" ){
			
			// show message
			showCustomAlert('error','Niewypełnione pola','Proszę wypełnić wszystkie pola w formularzu kontaktowym','Blank fields','Please fill in all fields in contact form');
			
		} else {
			
			// send message
			$.post( url, { senderName: $('#contact-name').val(), senderEmail: $('#contact-email').val(), 
				messageSubject: $('#contact-subject').val(), messageText: $('#contact-message').val(), _csrf: csrf_token }, function(data, status) { 
					
					
					// show message depending on status and clear form fields
					if (status == "success"){						
						showCustomAlert('success','','Wiadomość została wysłana.','','The message has been sent.');
						$("#contact-name").val("").focus();
						$("#contact-email").val("").focus();
						$("#contact-subject").val("").focus();
						$("#contact-message").val("").focus().trigger('blur');
						
					} else 		
						showCustomAlert('error','','Błąd wysyłania wiadomości.','','Error: The message has not been sent.');
			});			
		}			
	});
});

// * * * FUNCTIONS * * *
// * * * Show toastr alert depending on the langauge  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}