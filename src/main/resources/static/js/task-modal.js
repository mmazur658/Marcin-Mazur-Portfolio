//* * * hide edit section on page loading * * * 
$('#editTaskDataSection').hide();

//* * * change section on button click * * * 
$('#editTaskModalBtn').on('click', function(){	
	$('#task-container').fadeOut('600', function() {	
		$('#showTaskDataSection').hide();
		$('#editTaskDataSection').show();
	}).fadeIn('600');	
});

//* * * cancel editing on button click  * * * 
$('#cancelTaskEditBtn').on('click', function(){	
	showTaskDataSection();
});

//* * * confirm editing on button click * * * 
$('#editTaskForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault();   
    
    // get form URL
  	var $form = $( this ),
    url = $form.attr( 'action' );
  	  	
  	// get input data
  	var taskId = $('#taskIdHolder').val();
  	var taskName = $('#taskNameInputForm').val();
  	var taskCategory = $('#taskCategoryInputForm').val();
  	var taskDate = $('#taskDateOfAddedInputForm').val();
  	var taskDeadline = $('#taskDeadlineInputForm').val();
  	var taskDescription = $('#taskDescription').val();
  	
    var taskIsActive;
    if ($('#taskIsActiveInputForm').is(":checked")) 
    	taskIsActive = true;
    else
    	taskIsActive = false;   
    
    var taskIsCompleted;
    if ($('#taskIsCompletedInputForm').is(":checked")) 
    	taskIsCompleted = true;
    else
    	taskIsCompleted = false;
    

    // check if all required fields are not null
    if(taskId == "" || taskName == "" || taskCategory == "" )
    	showCustomAlert('error', "Puste pola", "Proszę wypełnić wszystkie pola", 'Blank fields', 'Please fill in all fields.');   
    else {
    	
    	// check if date has correct format
    	var isDeadlineValidated = (taskDeadline.length == 0) ? true : validateDateFormat(taskDeadline);
    	
    	if(validateDateFormat(taskDate) && isDeadlineValidated){

    		// send data to the server
    	    $.post(url, { taskId: taskId, taskName: taskName, taskCategory: taskCategory, taskDate: taskDate, taskDeadline: taskDeadline, taskDescription: taskDescription, taskIsActive: taskIsActive, taskIsCompleted: taskIsCompleted, _csrf: csrf_token }, function(data,status){
    	    	 
    	    	// show message depending on status
    	    	if(status == 'success')
    	    		showCustomAlert('success', "Sukces", "Zmiany zostały zatwierdzone", 'Success', 'The changes have been saved.');
    	    	else 
    	    		showCustomAlert('error', "Błąd", "Zmiany nie zostały zatwierdzone", "Error", 'The changes have not been saved.');   	    	  	
    	    });
    	    
    	    // reload task modal 
    		$('#task-details-modal-content').fadeOut('1000', function() {	
    			$('#task-details-modal-content').load('task/task', { taskId: taskId, _csrf: csrf_token });
    		}).fadeIn('1000');
    		
    		// show task data section
    		$('#confirmTaskEditBtn').on('click', function(){	
    			showTaskDataSection();
    		});    		
    		
    		// reload task table
    		loadTaskTable();
    		
    	} else 
    		
    		// show message if date has incorrect format
    		showCustomAlert('error', "Nieprawidłowy format daty", "Proszę wprowadzić datę zgodnie ze wzorem: yyyy-MM-dd HH:mm", 'Incorrect Date format', 'Please enter data according to the pattern: yyyy-MM-dd HH:mm');   	 	  	
    }  	
    	
});
// * * * FUNCTIONS * * * 
// * * * Show toastr alert depending on the language  * * * 
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}

//* * * show task data section * * * 
function showTaskDataSection(){
	$('#task-container').fadeOut('600', function() {	
		$('#editTaskDataSection').hide();
		$('#showTaskDataSection').show();
	}).fadeIn('600');	
}

//* * * validate date format * * * 
function validateDateFormat(date){
	
	// check if date has format: yyyy-MM-dd hh:mm	
		if(date.length==16 &&
		   $.isNumeric(date.charAt(0)) && 
		   $.isNumeric(date.charAt(1)) &&
		   $.isNumeric(date.charAt(2)) &&
		   $.isNumeric(date.charAt(3)) && 
		   date.charAt(4) == "-" && 
		   $.isNumeric(date.charAt(5)) && 
		   $.isNumeric(date.charAt(6)) &&
		   date.charAt(7) == "-" && 
		   $.isNumeric(date.charAt(8)) &&
		   $.isNumeric(date.charAt(9)) && 
		   date.charAt(10) == " " &&
		   $.isNumeric(date.charAt(11)) && 
		   $.isNumeric(date.charAt(12)) && 
		   date.charAt(13) == ":" && 
		   $.isNumeric(date.charAt(14)) &&
		   $.isNumeric(date.charAt(15))){
			
			return true;
			
		} else 		
			return false;
};