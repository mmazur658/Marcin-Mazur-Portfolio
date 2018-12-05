// * * * Access Form: verifying given code * * *
$('#accessCodeForm').submit(function(event){

		// prevent form form default action
	    event.preventDefault();   
	    
	    // get form url
	  	var $form = $( this ),
	    url = $form.attr( 'action' );  	 

	  	// get given access code
	    var accessCodeValue = $('#accessCodeValue').val();
	    
	    // check if all required fields are not null
	    if( accessCodeValue == "" )
	    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.');   
	    else {
	    		
	    		// verify given code 
	    		$.post(url, { accessCodeValue: accessCodeValue, _csrf: csrf_token }, function(data){
	    	    	
	    		// get verifying result
	    	    var isAccessCodeCorrect = data;   	
	    	    
	    	    // show a message depending on the result
	    	    if(isAccessCodeCorrect)
	    	    	showCustomAlert('success', "Dostęp przynany", "", 'Access granted', '');
	    	    else 	
	    	    	showCustomAlert('error', "Brak dostępu", "Kod niepoprawny", "Access denied", 'Incorrect access code');   	    	  	
	    	   		
	    	    // reload section
	    	    loadCareerSection();
	    	});   	  	    
	   }
});  