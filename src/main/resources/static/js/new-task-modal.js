
// * * * Add new task * * * 
$('#newTaskForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault(); 
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  
	 
  	// get given access code
    var newTaskName = $('#newTaskName').val();
	var newTaskSelect = $('#newTaskSelect').val();
	var newTaskDeadline = $('#newTaskDeadline').val();
	var newTaskDescription = $('#newTaskDescription').val();

	// check if all required fields are not null
    if( newTaskName == "" || newTaskSelect == "" || newTaskDescription == "")
    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.');   
    else {
    	// send data to the server
		$.post(url, { taskName: newTaskName, taskCategory: newTaskSelect, taskDeadline: newTaskDeadline, taskDescription: newTaskDescription, _csrf: csrf_token }, function(data,status){
    	    	
		// show message depending on status
    	if(status == 'success')
    	    showCustomAlert('success', "Sukces", "Zadanie został dodane", 'Success', 'The task has been added');
    	else 
    	    showCustomAlert('error', "Błąd", "Zadanie nie zostało dodane", "Error", 'The task has not been added');   	    	  	
   		 });
		
		// reload task table
		loadTaskTable();
		
		// hide new task modal
		$('#newObjectModal').modal('hide');	    	   	    
    }  	   
    	
});
//* * * FUNCTIONS * * *
//* * * Show toastr alert, depends on the langauge  * * * 
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}
