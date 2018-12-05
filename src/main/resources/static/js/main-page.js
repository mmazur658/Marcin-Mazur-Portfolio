//* * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

// * * * load career section on page loading  * * *
$(document).ready(function() {
	loadCareerSection();
});

//* * * check if given access code is correct * * *
$('#accessCodeForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault(); 
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  

  	// get input value
    var accessCodeValue = $('#accessCodeValue').val();
    
    // check if input is null 
    if( accessCodeValue == "" ){
    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.'); 
    	$('#accessCodeValue').val("");
    } else {
    	
    	// check if input value has correct length
    	if(accessCodeValue.length != 6){
    		showCustomAlert('error', "Niepoprawny format kod", "Proszę wprowadzić kod ponownie", 'Incorrect access code format', 'Please enter your code again');
    		$('#accessCodeValue').val("");
    		  		
    	} else {
    		// check given access code
    		$.post(url, { accessCodeValue: accessCodeValue, _csrf: csrf_token }, function(data){
    	    	
    	    var isAccessCodeCorrect = data;   	    
    	    
    	    if(isAccessCodeCorrect)
    	    	showCustomAlert('success', "Dostęp przynany", "", 'Access granted', '');
    	    else 	
    	    	showCustomAlert('error', "Brak dostępu", " ", "Access denied", '');   	    	  	
    	   	
    	    // reload career section
    	    loadCareerSection();
    	});    	   	    
   	 }  
   }
});  

//* * * FUNCTIONS  * * *
//* * * load career section * * *
function loadCareerSection(){
	$('#careerSection').fadeOut('1000', function() {				
		$('#careerSection').load('/career-section', { _csrf : csrf_token});
	}).fadeIn('1000');
}	

// * * * Show toastr alert, depending on the langauge  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}