// * * *  stores ids of selected rows * * * 
var selectedIds = []; 
// * * *  current opened message id * * * 
var currentId=0; 
//* * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

//* * * open message and load message content * * * 
	$("#openBtn").on('click', function(event){
		
		// get number of selected messages
		var amountOfSelectedCheckboxs=0;		
		$('input[name="table-chbox"]:checked').each(function() {
			amountOfSelectedCheckboxs = amountOfSelectedCheckboxs + 1;			
		});
				
		if(amountOfSelectedCheckboxs > 0) {
			
			// get sele*cted ids and store them in array
			selectedIds = [];
			currentId = 0;					
			$('input[name="table-chbox"]:checked').each(function() {
				selectedIds.push($(this).val());
				$(this).prop('checked', false);
			});
			
			// show modal for first selected id
			$('#contactFormMessageModal').modal('show');			
			currentId=selectedIds[0];
			
			// load modal content
			$('#contact-form-message-modal').fadeOut('1000', function() {	
				$('#contact-form-message-modal').load('get-message-modal', { contactFormMessageId: selectedIds[0], _csrf: csrf_token });
			}).fadeIn('1000');
			
			// change isRead status for selected message id
			setReadStatusTrue(currentId);			
		} else 			
			showCustomAlert('error','Pusty wybór','Proszę zaznaczyć więcej wierszy.','Empty Select','Please select more items.');	
	});

// * * * load list of messages on page loading * * *
$(document).ready(function(){
	
	$('#message-list-table').fadeOut('1000', function() {	
		$('#message-list-table').load('get-contact-form-messages', { listType: "new", _csrf: csrf_token });
	}).fadeIn('1000');
	
	$('#resultStartRange').val(0);
	$("#result-label").text("1  -  20");	
});

//* * * Load list of messages when type of list is changed * * *
	$('input[name=options]').change(function(){

		// get listtpe and load list depending on selected type
		var listType = $( 'input[name=options]:checked' ).val();
		$('#message-list-table').fadeOut('1000', function() {	
			$('#message-list-table').load('get-contact-form-messages', { listType: listType, resultStartRange: $('#resultStartRange').val(), _csrf: csrf_token  });
		}).fadeIn('1000');

		// set default result range
		$('#resultStartRange').val(0);
		$("#result-label").text("1  -  20");	
		
		// clear search form fields
		$("#searchFormName").val("").focus();
  		$("#searchFormEmail").val("").focus();
  		$("#searchFormSubject").val("").focus();
  		$("#searchFormStartDate").val("").focus();
  		$("#searchFormEndDate").val("").focus().trigger('blur');				
		$("#setParams").text(0); 	  		
});

// * * * close modal on button click  * * *
$("#close-modal-button").on('click', function(){
	currentId=0;
	selectedIds=[];
});

//* * * show more results on button click * * *
$("#more-btn").on('click', function(){

		// get current result range, total results and selected listType
		var resultStartRange = parseInt($("#resultStartRange").val());
		var totalResults = $("#totalResults").val();
		var listType = $( 'input[name=options]:checked' ).val();
		
		// show error if operation is not possible
		if(totalResults < (resultStartRange+20)){			
			showCustomAlert('error','Nie ma więcej wyników','Dotarłeś do końca listy','No more results','You have reached the end of the list');				
		} else {	
			
			// set new result range
			resultStartRange = resultStartRange+20;			
			
			// loda message list for new range
			$('#message-list-table').fadeOut('1000', function() {	
				$('#message-list-table').load('get-contact-form-messages', { listType: listType, resultStartRange:  resultStartRange, _csrf: csrf_token });
			}).fadeIn('1000');					
			
			// set new resul range description
			if(resultStartRange+20 < totalResults)
				$("#result-label").text((resultStartRange+1)+"  -  "+(resultStartRange+20));
			else 
				$("#result-label").text((resultStartRange+1)+"  -  "+totalResults);
			
			// set new value in hidden input
			$("#resultStartRange").val(resultStartRange);
		}			
	});

// * * * show less results on button click * * *
	$("#less-btn").on('click', function(){

		// get current result range, total results and selected listType
		var resultStartRange = parseInt($("#resultStartRange").val());
		var totalResults = $("#totalResults").val();
		var listType = listType = $( 'input[name=options]:checked' ).val();	
		
		// show error if action is not possible
		if((resultStartRange - 20) < 0)			
			showCustomAlert('error','Nie ma mniej wyników','Dotarłeś do początku listy','No less results','You have reached the start of the list');
		
		else {
			// set new result range
			resultStartRange=resultStartRange-20;
			
			// loda message list for new range
			$('#message-list-table').fadeOut('1000', function() {	
				$('#message-list-table').load('get-contact-form-messages', { listType: listType, resultStartRange: resultStartRange, _csrf: csrf_token  });
			}).fadeIn('1000');	
			
			// set new resul range description				
			$("#result-label").text((resultStartRange+1)+"  -  "+(resultStartRange+20));	
			
			// set new value in hidden input
			$("#resultStartRange").val(resultStartRange);
		}			
	});
	
// * * * change isRead status on button click * * *
$("#readBtn").on('click', function(){

	// get number of selected messages
	var amountOfSelectedCheckboxs=0;		
	$('input[name="table-chbox"]:checked').each(function() {
		amountOfSelectedCheckboxs = amountOfSelectedCheckboxs + 1;			
	});
		
	if(amountOfSelectedCheckboxs > 0) {
		
		// change is read status for every selected message
		$('input[name="table-chbox"]:checked').each(function() {

			// get row for given id
			var checkboxValue = $(this).val();		
			var $row = $(this).closest("tr");       
			   
			// change row style
			  if($row.find("td:nth-child(7)").html() == "true"){
			    	$row.fadeOut('600', function(){
			    		$row.find("td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(5)").addClass( "font-weight-bold" );
			    		$row.find("td:nth-child(7)").text("false");
			    	}).fadeIn('600')

			   	} else {
			    	$row.fadeOut('600', function(){
			    	$row.find("td:nth-child(2),td:nth-child(3),td:nth-child(4), td:nth-child(5)").removeClass( "font-weight-bold" );
			   		$row.find("td:nth-child(7)").text("true");			    			
			    	}).fadeIn('600')
			    }
			    
			    // send changes to the server and deselect checkbox
	    		$.post("admin-action/change-contact-form-message-read-status", {selectedCheckboxValue: checkboxValue,  _csrf: csrf_token });
			    $(this).prop('checked', false);
			});			
			showCustomAlert('success','Zrobione','Status wiadomości został zmieniony.','Done','The message status has been changed.');			
		} else 			
			showCustomAlert('error','Pusty wybór','Proszę zaznaczyć więcej wierszy.','Empty Select','Please select more items.');	
	});

// * * * change isReplied status on button click * * *
$("#changeReplyStatusBtn").on('click', function(){

	// get number of selected messages
	var amountOfSelectedCheckboxs=0;			
	$('input[name="table-chbox"]:checked').each(function() {
		amountOfSelectedCheckboxs = amountOfSelectedCheckboxs + 1;			
	});
			
	if(amountOfSelectedCheckboxs > 0) {
		
		// change isRead status for every selected message
		$('input[name="table-chbox"]:checked').each(function() {
			
			// get row for given id
			var checkboxValue = $(this).val();		
			var $row = $(this).closest("tr");      
					
			// change checkbox and row style if message is not read
			if($row.find("td:nth-child(8)").html() == "false"){
						
				$row.find("td:nth-child(6)").fadeOut('600', function(){
					// send changes to the server
					$.post("admin-action/change-contact-form-message-replied-status", {selectedCheckboxValue: checkboxValue,  _csrf: csrf_token });
					$(this).html('<i class="far fa-check-square"></i>');
				}).fadeIn('600');
						
				if($row.find("td:nth-child(7)").html() == "false"){				
					$row.fadeOut('600', function(){
						$row.find("td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5)").removeClass( "font-weight-bold" );
					}).fadeIn('600')	
				}
				// change hidden td value to true
				$row.find("td:nth-child(7), td:nth-child(8)").text("true");
				
			
			} else {		
				$row.find("td:nth-child(6)").fadeOut('600', function(){
					$(this).html('<i class="far fa-square"></i>');
				}).fadeIn('600');	
				
				// send changes to the server
				$.post("admin-action/change-contact-form-message-replied-status", {selectedCheckboxValue: checkboxValue, _csrf: csrf_token });
				
				// change hidden td value to false
				$row.find("td:nth-child(8)").text("false");						
			}		
			
			// deselect checkbox
			$(this).prop('checked', false);
					
		});				
		showCustomAlert('success','Zrobione','Status wiadomości został zmieniony.','Done','The message status has been changed.');
	
	} else 
		showCustomAlert('error','Pusty wybór','Proszę zaznaczyć więcej wierszy.','Empty Select','Please select more items.');	
});

// * * * delete message on button click * * *
$("#deleteBtn").on('click', function(){
		
	// get selected messages ids
	var messagesIds=[];		
	$('input[name="table-chbox"]:checked').each(function() {
		messagesIds.push($(this).val());
	});

	if(messagesIds.length > 0){
			
		// delete every selected message
		for(var i=0; i<messagesIds.length; i++)
			$.post( "admin-action/delete-contact-form-message", { contactFormMessageId: messagesIds[i], _csrf: csrf_token  });
		
		// get selected listType
		var listType = $( 'input[name=options]:checked' ).val();
			
		// reaload messages list
		$('#message-list-table').fadeOut('1000', function() {				
			$('#message-list-table').load('get-contact-form-messages', { listType: listType, resultStartRange: 0, _csrf: csrf_token  });
		}).fadeIn('1000');
			
		// clear serach parameters badge
		$("#setParams").text(0); 

		if(messagesIds.length > 1)
			showCustomAlert('success','Zrobione','Wiadomości zostały usunięte.','Done','The messages have been deleted.');
		 else 
			showCustomAlert('success','Zrobione','Wiadomość została usunięta.','Done','The message has been deleted.');
	} else 			
		showCustomAlert('error','Pusty wybór','Proszę zaznaczyć więcej wierszy.','Empty Select','Please select more items.');
});

// * * * clear search parameters on button click * * *
$('#clearSearchParametersBtn').on('click', function() {
    $("#searchFormName").val("").focus();
  	$("#searchFormEmail").val("").focus();
  	$("#searchFormSubject").val("").focus();
  	$("#searchFormStartDate").val("").focus();
  	$("#searchFormEndDate").val("").focus().trigger('blur');	
});

// * * * get search results * * *
$('#searchForm').submit(function(event){
	
	// prevent form form default action
	event.preventDefault();      
	
	// get form url
	var $form = $( this ),
	url = $form.attr( 'action' );  
		  	
	// get input data
	var searchFormName = $("#searchFormName").val();
	var searchFormEmail = $("#searchFormEmail").val();
	var searchFormSubject = $("#searchFormSubject").val();
	var searchFormStartDate = $("#searchFormStartDate").val();
	var searchFormEndDate = $("#searchFormEndDate").val();	  		
	var listType = $( 'input[name=options]:checked' ).val();
			
	// load message list for given search parameters
	$('#message-list-table').fadeOut('1000', function() {				
		$('#message-list-table').load('get-search-contact-form-messages', { listType: listType, searchFormName: searchFormName,
			searchFormEmail: searchFormEmail, searchFormSubject: searchFormSubject,	searchFormStartDate: searchFormStartDate,
			searchFormEndDate: searchFormEndDate, _csrf: csrf_token  });
	}).fadeIn('1000');
	
	// set result range to default value
	$("#resultStartRange").val(0);
	
	// get number of search parameters
	var count = 0 ;
	$('input[type=text]').each(function(){
		if($(this).val() != "")
			 count = count + 1;
	})			
		
	// set number of search parameters
	$("#setParams").text(count);
	
	// close modal
	$('#searchContactFormMessageBtn').modal('toggle');	
	
});	


// * * * FUNCTIONS * * *
// * * * Check checkbox on row click * * *
$(function() {
    $("#message-list-table").on('click', 'tr', function(){    	
        if (event.target.type !== 'checkbox') 
            $(':checkbox', this).trigger('click');    
    });
});

// * * * Change isReaded status * * *
function setReadStatusTrue(messageId){

	// find row for given id
	var $row = $("#message-list-table").find("tr[data-id='" + messageId + "']")

	// change isReaded status and row style
	$row.fadeOut('600', function(){
		$row.find("td:nth-child(2),td:nth-child(3),td:nth-child(4),td:nth-child(5)").removeClass( "font-weight-bold" );
		$row.find("td:nth-child(7)").text("true");
		// send changes to the server
		$.post("admin-action/change-contact-form-message-read-status-to-true", {selectedCheckboxValue: messageId, _csrf: csrf_token });
	}).fadeIn('600')		
}

// * * * Show toastr alert, depending on the langauge  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}