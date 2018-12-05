// * * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

//* * * hide change password form section on page loading * * *
$('#formSection').hide();

// * * * get number of notification * * * 
$.post("admin-action/get-number-of-notification", { _csrf: csrf_token}, function(data){	
	$('#notificationBadge').text(data);
});

//* * * get number of contact form messages * * * 
$.post("admin-action/get-number-of-contact-form-messages", { _csrf: csrf_token}, function(data){
	$('#contactFormMessageBadge').text(data);
});

//* * * switch to change password form on button click * * *
$('#changePasswordButton').on('click', function(){	
	$('#changePasswordSection').fadeOut('600', function() {	
		$('#buttonSection').hide();
		$('#formSection').show();
	}).fadeIn('600');
});
//* * * cancel editing password * * *
$('#cancelPasswordChangeButton').on('click', function(){	
	cancelActionButton();
});

//* * * change password * * *
$('#changePasswordForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault();    
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  
  	
  	// get input data
	var username = $('#usernameHolder').text();
	var oldPassword = $('#oldPassword').val();
	var newPassword = $('#newPassword').val();
	var confirmNewPassword = $('#confirmNewPassword').val();
	
	// check if old password is correct
	if(validateChangePasswordForm(username, oldPassword, newPassword , confirmNewPassword)){
		$.post(url , { oldPassword: $('#oldPassword').val(), username: username, _csrf: csrf_token}, function(data){
			
			if(data == false)
				showCustomAlert('error','Niepoprawne stare hasło','','Incorrect old password','');
			else{
				
				// change password
				$.post("admin-action/change-password",{ newPassword: $('#newPassword').val() ,username: username, _csrf: csrf_token}, function(data, status){
					
					if(status=="success")
						showCustomAlert('success','Sukces','Hasło zostało zmienione','Success','The password has been changed');
					else 
						showCustomAlert('error','Bład','Hasło nie zostało zmienione','Error','The password has not been changed');
										
					// clear and hide password change form
					cancelActionButton();
					
				});
			}
		});
	}
});	
	
//* * * logout on button click * * *
$('#logoutButton').click(function(){
	$('#logoutForm').submit();
});

//* * * FUNCTIONS * * *
//* * * cancel action button * * *
function cancelActionButton(){
	$('#changePasswordSection').fadeOut('600', function() {	
		$('#formSection').hide();
		$('#oldPassword').val("");
		$('#newPassword').val("");
		$('#confirmNewPassword').val("");
		$('#buttonSection').show();
	}).fadeIn('600');
};

//* * * validate password form * * *
function validateChangePasswordForm(username, oldPassword, newPassword , confirmNewPassword)	{
	
	var isFormValidate = true
	
	// check if all required fields are not null
	if ( username == "" || oldPassword == "" || newPassword == "" || confirmNewPassword == ""){
		showCustomAlert('error','Puste pola','Proszę uzupełnić wszystkie pola','Blank fields','Please fill in all fields');
		isFormValidate = false;
	} else {
		// check if the new passwords are the same
		if(newPassword != confirmNewPassword){
			showCustomAlert('error','Niepoprawne nowe hasło','Podane hasła nie są takie same','Incorrect new password','Passwords aren`t the same');
			isFormValidate = false;
		}
	}	
	// return result
	return isFormValidate;
};
//* * * Show toastr alert depending on the langauge  * * * 
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
};