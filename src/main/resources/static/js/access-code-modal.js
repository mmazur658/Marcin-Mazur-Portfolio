// * * * hide access code data and history section on page loading * * * 
$('#editAccessCodeDataSection').hide();
$('#accessCodeHistorySection').hide();

// * * * switch to access code edit section * * * 
$('#editAccessCodeModalBtn').on('click', function(){	
	$('#access-code-container').fadeOut('600', function() {	
		$('#showAccessCodeDataSection').hide();
		$('#editAccessCodeDataSection').show();
	}).fadeIn('600');	
});

//* * * switch to access code history section * * * 
$('#accessCodeHistoryBtn').on('click', function(){
	
	// load access code history data
	$('#accessCodeHistoryTable').load('access-code/access-code-history', { accessCodeValue: $('#accessCodeValueInputForm').val(), _csrf : csrf_token});	
	$('#access-code-container').fadeOut('600', function() {	
		$('#showAccessCodeDataSection').hide();
		$('#accessCodeHistorySection').show();
	}).fadeIn('600');	
});


//* * * cancel access code editing * * * 
$('#cancelAccessCodeEditBtn').on('click', function(){	
	showAccessCodeDataSection();
});

//* * * back to access code data section * * * 
$('#backButton').on('click', function(){	
	$('#access-code-container').fadeOut('600', function() {	
		$('#accessCodeHistorySection').hide();
		$('#showAccessCodeDataSection').show();
	}).fadeIn('600');	
});

//* * * confirm access code editing * * * 
$('#editAccessCodeForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault();   
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  
     
  	// get input data
    var accessCodeId = $('#accessCodeIdHolder').val();
    var accessCodeValue = $('#accessCodeValueInputForm').val();
    var accessCodeDate = $('#accessCodeDateInputForm').val();
    var accessCodeDescription = $('#accessCodeDescription').val();
    var accessCodeOwner = $('#accessCodeOwnerInputForm').val();
    var accessCodeIsActive;
    if ($('#accessCodeIsActiveInputForm').is(":checked")) 
    	accessCodeIsActive = true;
    else
    	accessCodeIsActive = false;
    
    // check if all required fields are not null
    if(accessCodeId == "" || accessCodeValue == "" || accessCodeDate == "" || accessCodeOwner == "")
    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.');   
    else {
    	
    	// check if the date has the required format
    	if(accessCodeDate.length == 16 && $.isNumeric(accessCodeDate.charAt(0)) && $.isNumeric(accessCodeDate.charAt(1)) && $.isNumeric(accessCodeDate.charAt(2)) &&
			$.isNumeric(accessCodeDate.charAt(3)) && accessCodeDate.charAt(4) == "-" && $.isNumeric(accessCodeDate.charAt(5)) && $.isNumeric(accessCodeDate.charAt(6)) &&
			accessCodeDate.charAt(7) == "-" && $.isNumeric(accessCodeDate.charAt(8)) && $.isNumeric(accessCodeDate.charAt(9)) && accessCodeDate.charAt(10) == " " &&
			$.isNumeric(accessCodeDate.charAt(11)) && $.isNumeric(accessCodeDate.charAt(12)) && accessCodeDate.charAt(13) == ":" && $.isNumeric(accessCodeDate.charAt(14)) &&
			$.isNumeric(accessCodeDate.charAt(15)) 	){

    		// send data to the server 
    	    $.post(url, {accessCodeId: accessCodeId, accessCodeValue: accessCodeValue, accessCodeDate:accessCodeDate , accessCodeIsActive: accessCodeIsActive, accessCodeDescription: accessCodeDescription, accessCodeOwner: accessCodeOwner, _csrf: csrf_token }, function(data,status){
    	    	 
    	    	// show message depending on response status
    	    	if(status == 'success')
    	    		showCustomAlert('success', "Sukces", "Zmiany zostały zatwierdzone", 'Success', 'The changes have been saved.');
    	    	else 
    	    		showCustomAlert('error', "Błąd", "Zmiany nie zostały zatwierdzone", "Error", 'The changes have not been saved.');   	    	  	
    	    });
    	    
    	    // reaload access code modal
    		$('#access-code-details-modal-content').fadeOut('1000', function() {	
    			$('#access-code-details-modal-content').load('access-code/access-code', { accessCodeId: accessCodeId, _csrf: csrf_token });
    		}).fadeIn('1000');
    		
    		// hide access code edit section
    		$('#confirmAccessCodeEditBtn').on('click', function(){	
    			showAccessCodeDataSection();
    		});    		
    		
    	} else 
    		// show message if date has incorrect format
    		showCustomAlert('error', "Nieprawidłowy format daty", "Proszę wprowadzić datę zgodnie ze wzorem: yyyy-MM-dd HH:mm", 'Incorrect Date format', 'Please enter data according to the pattern: yyyy-MM-dd HH:mm');   	 	
    }     	
});
//* * * FUNCTIONS * * * 
//* * * show access code data section * * * 
function showAccessCodeDataSection(){
	$('#access-code-container').fadeOut('600', function() {	
		$('#editAccessCodeDataSection').hide();
		$('#showAccessCodeDataSection').show();
	}).fadeIn('600');	
}
//* * * Show toastr alert depending on the language  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}