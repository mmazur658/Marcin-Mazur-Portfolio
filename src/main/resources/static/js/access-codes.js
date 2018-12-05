// * * * CSRF TOKEN * * *
var csrf_token = $("meta[name='_csrf']").attr("content");

//* * * load access code table on page loading * * * 
$(document).ready(function() {
	loadAccessCodeTable();
});

//* * * show new access code modal on button click * * * 
$('#addAccessCodeButton').on('click', function(){ 
	$('#newObjectModal').modal('show');	
		$('#new-object-modal-content').fadeOut('1000', function() {	
		$('#new-object-modal-content').load('access-code/new-access-code', { _csrf: csrf_token });
	}).fadeIn('1000');	
});

//* * * show access code details on row click  * * * 
$("#access-code-list-table").on('click', 'tr', function(){   
	
	// get access code id
	var accessCodeId = $(this).attr('data-id');
	
	// show modal
	$('#accessCodeModal').modal('show');		    	
	
	// load content
	$('#access-code-details-modal-content').fadeOut('1000', function() {	
		$('#access-code-details-modal-content').load('access-code/access-code', { accessCodeId: accessCodeId, _csrf: csrf_token });
	}).fadeIn('1000');	
	
});

//* * * FUNCTION * * * 
//* * * load access code table * * * 
function loadAccessCodeTable(){
	$('#access-code-list-table').fadeOut('1000', function() {				
		$('#access-code-list-table').load('access-code/access-codes', { _csrf : csrf_token});
	}).fadeIn('1000');
}