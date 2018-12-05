// * * * generate new  access code on button click* * * 
$('#generateNewValue').on('click', function(){
		
	// get random value form 100001 to 999999 
	var randomValue = getRandomInt(100001, 999999);
		
	// check if generated access code is unique
	$.post("access-code/check-access-code",{accessCodeValue: randomValue, _csrf: csrf_token }, function(data){
			
		if(data==true)
			$('#accessCodeDiv').removeClass("alert alert-danger my-0").addClass("alert alert-success my-0");
		else
			$('#accessCodeDiv').removeClass("alert alert-success my-0").addClass("alert alert-danger my-0");
	});
	
	// set generated value to input
	$('#newAccessCodeValue').val(randomValue);		
});	

//* * * Save new access code * * * 
$('#newAccessCodeForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault();  
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  
    
  	// get input data
    var newAccessCodeValue = $('#newAccessCodeValue').val();
    var newAccessCodeOwner = $('#newAccessCodeOwner').val();
    var newAccessCodeDescription = $('#newAccessCodeDescription').val();
    
    // check if all required fields are not null
    if( newAccessCodeValue == "" || newAccessCodeOwner == "" || newAccessCodeDescription == "")
    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.');   
    else {
    	// send data to the server
		$.post(url, { newAccessCodeValue: newAccessCodeValue, newAccessCodeOwner: newAccessCodeOwner, newAccessCodeDescription: newAccessCodeDescription, _csrf: csrf_token }, function(data,status){
    	    	
		// show message depending on status
    	if(status == 'success')
    	    showCustomAlert('success', "Sukces", "Kod dostępu został dodane", 'Success', 'The access code has been added');
    	else 
    	    showCustomAlert('error', "Błąd", "Kod dostępu nie zostało dodane", "Error", 'The access code has not been added');   	    	  	
   		 });
		
		// reaload access code table
		loadAccessCodeTable();
		
		// hide modal
		$('#newObjectModal').modal('hide');	    	   	    
    } 	  
    	
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

// * * * generate random value between given min and max value * * * 
function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

