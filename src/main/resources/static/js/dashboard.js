//* * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

//* * * load task table on page loading * * * 
loadTaskTable();

// * * * Counter * * *
$('.counter-count').each(function () {
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        }, {
            duration: 3000,
            easing: 'swing',
            step: function (now) {
                $(this).text(Math.ceil(now));
            }
        });
   });

// * * *load statistic table on page loading * * * 
var date = new Date();
var startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
var endDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
loadStatisticTableForGivenDateRange(startDate, endDate);

// * * * open notification modal on button click * * * 
$('#notificationModalTrigger').on('click', function(){
	$("#notificationModalIconTrigger").trigger( "click" );
});

//* * * Load char when date range is changed * * * 
$('#dashboardStatistickSelect').change(function(){	
	var selectedValue = $( this).val();
	
	// date variables
	var startDate;
	var endDate;
	var date;
		
	// loda data for given date range
	if (selectedValue == 'today') {	
		
		date = new Date();
		startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
		endDate = startDate;
		loadStatisticTableForGivenDateRange(startDate, endDate);
		
	} else if(selectedValue == 'yesterday'){	
		
		vdate = new Date();	
		date.setDate(date.getDate() - 1);	
		startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();	
		endDate = startDate;
		
		loadStatisticTableForGivenDateRange(startDate, endDate);
		
	} else if (selectedValue == 'week'){	
		
		date = new Date();		
		endDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();		
		date.setDate(date.getDate() - 6);		
		startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();	
		
		loadStatisticTableForGivenDateRange(startDate, endDate);
		
	} else if (selectedValue == 'month'){
		
		date = new Date();		
		endDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
		date.setDate(date.getDate() - 29);		
		startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();	

		loadStatisticTableForGivenDateRange(startDate, endDate);
		
	} else if (selectedValue == 'ever'){
		
		date = new Date();	
		endDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
		date.setDate(date.getDate() - 3500);		
		startDate = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();	
		
		loadStatisticTableForGivenDateRange(startDate, endDate);
	}	
});

// * * * FUNCTIONS * * *
// * * * Load task list table * * * 
function loadTaskTable(){	
	$('#dashboard-tasks-list-table').fadeOut('1000', function() {				
		$('#dashboard-tasks-list-table').load('task/tasks', {taskListType: "active" ,  _csrf : csrf_token});
	}).fadeIn('1000');
}
// * * * Load notification list modal * * * 
function loadNotificationListModal(){	
	$('#modal-body-contect').fadeOut('1000', function() {	
		$('#modal-body-contect').load('notifications/notification-list', {  _csrf: csrf_token });
	}).fadeIn('1000');
}

// * * * Show toastr alert depending on the langauge  * * *
function showCustomAlert(type, titlePL, textPL, titleEN, textEN){
	
	var userLang = document.documentElement.lang;
	
	if(userLang == "pl_PL")
    	showToastrAlert(type, textPL ,titlePL);
    else 
    	showToastrAlert(type, textEN , titleEN);
}

// * * * load statistic table for given date range * * * 
function loadStatisticTableForGivenDateRange(startDate, endDate){
	$('#statistic-table').fadeOut('1000', function() {	
		$('#statistic-table').load('/administrator-panel/statistic-table', {startDate: startDate, endDate: endDate, _csrf: csrf_token });
	}).fadeIn('1000');
}
