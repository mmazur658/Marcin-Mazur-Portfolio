
// * * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

//* * * load notification modal on button click * * * 
$("#notificationModalIconTrigger").off().on('click', function(event){	
	loadNotificationListModal();		
});

// * * * delete notification on button click and reload list * * * 
$('.deleteNotificationButton').on('click', function(){
	
	// delete notification
	$.post("/administrator-panel/notifications/delete-notification",{ notificationId: $(this).val(), _csrf: csrf_token }, function(data, status){
		
		// show message depending on status
		if(status == "success")
			showCustomAlert("success", "","Komunikat został usunięty","","The notification has been deleted");
		else
			showCustomAlert("error", "","Komunikat został usunięty","","Error during deleting the notification");
	});
	
	// reload notification modal
	loadNotificationListModal();
});

//* * * FUNCTIONS * * * 
//* * * Load notification list modal * * *  
function loadNotificationListModal(){	
	$('#notification-modal').fadeOut('1000', function() {	
		$('#notification-modal').load('notifications/notification-list', {  _csrf: csrf_token })
	}).fadeIn('1000');
};

// * * * Show toastr alert depending on the langauge  * * *  
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;

	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}