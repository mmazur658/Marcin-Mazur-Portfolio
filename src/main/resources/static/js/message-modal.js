// * * * CSRF TOKEN * * *
var csrf_token = $("meta[name='_csrf']").attr("content");

// * * * Hide comment Form on page loading * * *
$('#addCommentDiv').hide();

// * * * Show comment Form on button click* * * 
$('#addCommentButton').on('click', function(){
	$('#addCommentDiv').show(600);
})

// * * * Hide comment form on button click* * * 
$('#cancelCommentBtn').on('click', function(){
	$('#addCommentDiv').hide(600);
	$('#commentText').val("");
})

// * * * Check checkbox on row click * * * 
$(function() {
    $("#message-table").on('click', 'tr', function(){    	
        if (event.target.type !== 'checkbox') 
            $(':checkbox', this).trigger('click');    
    });
});

// * * * Add Comment Button * * * 
$('#addCommentForm').submit(function(event){
	
	// prevent form form default action
    event.preventDefault(); 
    
    // get form url
  	var $form = $( this ),
    url = $form.attr( 'action' );  
  	
  	// get input data
  	var commentText = $('#commentText').val();

  	// send data to the server
  	$.post(url, {messageId: currentId, commentText: commentText,  _csrf: csrf_token }, function(data, status){
  		
  		if (status == "success"){  	
  			// reload message modal and show message
  			getMessageModal(currentId);	  			
  			showCustomAlert('success','','Komentarz został dodany.','','The Comment has been added.'); 			
  		} else {
  			// hide comment div
  			$('#cancelCommentBtn').on('click', function(){
				$('#addCommentDiv').hide(600);
			});
  			
  			// clear comment form and show message
			$('#commentText').val("");   			
  			showCustomAlert('error','Błąd','Błąd dodawania komentarza, spóbuj ponownie póxniej.','Error','The Comment has not been added. Please try later.');		
  		} 	  		
  	});
});  

//* * *  show next message on button * * * 
$("#nextBtn").on('click', function(){	
	
	// get current message index
	var currentIdIndex = selectedIds.indexOf(currentId);
	
	// change index and reload modal
	if( (currentIdIndex + 1) > (selectedIds.length - 1) ){			
		getMessageModal(selectedIds[0]);
		currentId = selectedIds[0];
		setReadStatusTrue(currentId);	
	} else {				
		getMessageModal(selectedIds[currentIdIndex+1]);
		currentId = selectedIds[currentIdIndex+1];
		setReadStatusTrue(currentId);
	}
});

// * * *  show previous message on button * * * 
$("#previousBtn").on('click', function(){	
	
	// get current message index
	var currentIdIndex = selectedIds.indexOf(currentId);

	// change index and reload modal
	if( (currentIdIndex - 1) < 0 ){			
		getMessageModal(selectedIds[selectedIds.length-1]);
		currentId = selectedIds[selectedIds.length-1];
		setReadStatusTrue(currentId);							
	} else {			
		getMessageModal(selectedIds[currentIdIndex-1]);
		currentId = selectedIds[currentIdIndex-1]
		setReadStatusTrue(currentId);				
	}
});

// * * * Close modal on button * * * 
$("#closeMessageModalBtn").on('click', function(){
	closeModalAndClearParameters();
});

// * * *  Delete message on button click * * * 
$("#deleteMessageBtn").on('click', function(){

	// get current message id and list type
	var currentIdIndex = selectedIds.indexOf(currentId);
	var listType = $( 'input[name=options]:checked' ).val();
	
	// delete message contact 
	$.post( "admin-action/delete-contact-form-message", { contactFormMessageId: currentId,  _csrf: csrf_token  });
	$.post( "admin-action/change-contact-form-message-read-status-to-true", {selectedCheckboxValue: currentId,  _csrf: csrf_token });
	
	
	// delete selectedId from the array
	if(selectedIds.length == 1)	
		selectedIds=[]
	else {		
		for(var i = 0; i < selectedIds.length-1; i++){ 
			if ( selectedIds[i] === currentId) 
				selectedIds.splice(i, 1); 						
		}			
	}	

	// load next message or close modal if there are no more selected messages 
	if(selectedIds.length == 0 )	
		closeModalAndClearParameters();			
	else {		
		if(currentIdIndex === 0) {				
			getMessageModal(selectedIds[0]);
			currentId = selectedIds[0];
			setReadStatusTrue(currentId);		
		} else {			
			getMessageModal(selectedIds[currentIdIndex-1]);
			currentId = selectedIds[selectedIds.length-1];
			setReadStatusTrue(currentId);					
		}	
	}
 
	// reload modal
	$('#message-list-table').fadeOut('1000', function() {				
			$('#message-list-table').load('get-contact-form-messages', { listType: listType, resultStartRange: 0 , _csrf: csrf_token });
	}).fadeIn('1000');	
	
	// show message
	showCustomAlert('success','Zrobione','Wiadomość została usunięta.','Done','The message has been deleted.');

});

// * * * Change Read Status Button * * *
$("#readStatusBtn").on('click', function(){
	changeReadStatus(currentId);
});

// * * *  Change Replied Status Button * * * 
$("#repliedStatusButton").on('click', function(){
	changeIsRepliedStatus(currentId);
});

// * * * Delete comment on button click* * * 
$("#deleteCommentButton").on('click', function(){
		
		// get ids
		var commentsIds=[];		
		$('input[name="table-message-chbox"]:checked').each(function() {
			commentsIds.push($(this).val());
		});
		
	

		if(commentsIds.length > 0) {
			
			// delete every selcted message
			for(var i=0; i<commentsIds.length; i++)
				$.post( "admin-action/delete-comment", { contactFormCommentId: commentsIds[i], _csrf: csrf_token  });		
			
			// reload modal
			getMessageModal(currentId);
		
			// show message
			if(commentsIds.length > 1)				
				showCustomAlert('success','Zrobione','Komentarze zostały usunięte.','Done','The comments have been deleted.');	
			else 				
				showCustomAlert('success','Zrobione','Komentarz został usunięty','Done','The comment has been deleted.');			

		} else {	
			// show message if no checkbox has been selected
			showCustomAlert('error','Pusty wybór','Proszę zaznaczyć więcej komentarzy.','Empty Select','Please select more comments.');
		}

});

// * * * FUNCTIONS * * *
// * * *  Change isReplied status * * * 
function changeIsRepliedStatus(messageId){

	// get row for gigen message id
	var $row = $("#message-list-table").find("tr[data-id='" + messageId + "']")
	
	// change checkbox image and row style
	if($row.find("td:nth-child(8)").html() == "false"){		
		$row.find("td:nth-child(6)").fadeOut('600', function(){
			// send changes to the server
			$.post("admin-action/change-contact-form-message-replied-status", {selectedCheckboxValue: messageId,  _csrf: csrf_token });
			$(this).html('<i class="far fa-check-square"></i>');
		}).fadeIn('600');
		
		if($row.find("td:nth-child(7)").html() == "false"){				
			$row.fadeOut('600', function(){
				$row.find("td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(5)").removeClass( "font-weight-bold" );
			}).fadeIn('600')	
		}
		
		// set new value for hidden tds
		$row.find("td:nth-child(7)").text("true");
		$row.find("td:nth-child(8)").text("true");
	
	} else {
		
		// change checkbox image
		$row.find("td:nth-child(6)").fadeOut('600', function(){
			$(this).html('<i class="far fa-square"></i>');
		}).fadeIn('600');
		
		// send changes to the server
		$.post("admin-action/change-contact-form-message-replied-status", {selectedCheckboxValue: messageId,  _csrf: csrf_token });
		$row.find("td:nth-child(8)").text("false");				
	}	
	
	// show message
	showCustomAlert("success", "Zrobione", "Status wiadomości został zmieniony.", "Done", "The message status has been changed.")
}

// * * *  Show toastr alert, depends on the langauge  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}

// * * *  change isReaded status  * * * 
function changeReadStatus(messageId){
	
	// get row for gigen message id
	var $row = $("#message-list-table").find("tr[data-id='" + messageId + "']")
	
	// change row style
	if($row.find("td:nth-child(7)").html() == "true"){
		$row.fadeOut('600', function(){
			$row.find("td:nth-child(2), td:nth-child(3), td:nth-child(5), td:nth-child(4) ").addClass( "font-weight-bold" );
			$row.find("td:nth-child(7)").text("false");			
		}).fadeIn('600')
	} else {
		$row.fadeOut('600', function(){
			$row.find("td:nth-child(2), td:nth-child(3), td:nth-child(4), td:nth-child(5)").removeClass( "font-weight-bold" );
			$row.find("td:nth-child(7)").text("true");
		}).fadeIn('600')
	}	
	// send changes to the server
	$.post("admin-action/change-contact-form-message-read-status", {selectedCheckboxValue: messageId,  _csrf: csrf_token });
	
	// show message
	showCustomAlert("success","Zrobione","Status wiadomości został zmieniony.","Done","The message status has been changed.");
}

// * * *  Close modal and clear global parameters * * * 
function closeModalAndClearParameters(){
	currentId=0;
	selectedIds=[];
	$('#contactFormMessageModal').modal('toggle');
}

// * * * Load message content into modal body * * * 
function getMessageModal(messageId){
	$('#contact-form-message-modal').fadeOut('1000', function() {	
		$('#contact-form-message-modal').load('get-message-modal', { contactFormMessageId: messageId, _csrf: csrf_token});
	}).fadeIn('1000');	
}