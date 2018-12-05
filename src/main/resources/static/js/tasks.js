// * * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

// * * * load task table on page loading * * * 
$(document).ready(function() {
	loadTaskTable();
});

//* * * load task table on list type change * * * 
$('#taskListTypeSelect').change(function(){	
	loadTaskTable();	
});

// * * * show new task modal on button click * * * 
$('#addTaskButton').on('click', function(){ 
	
	// show modal
	$('#newObjectModal').modal('show');	
	
	// load modal content
	$('#new-object-modal-content').fadeOut('1000', function() {	
		$('#new-object-modal-content').load('task/new-task', { _csrf: csrf_token });
	}).fadeIn('1000');
	
});

// * * * show task details modal on row click * * * 
$("#tasks-list-table").on('click', 'tr', function(){   
	
	// get task id
	var taskId = $(this).attr('data-id');
	
	// show modal
	$('#taskModal').modal('show');		    	
	
	// load modal content
	$('#task-details-modal-content').fadeOut('1000', function() {	
		$('#task-details-modal-content').load('task/task', { taskId: taskId, _csrf: csrf_token });
	}).fadeIn('1000');	
	
});

// * * * FUNCTIONS * * * 
// * * * load task table * * * 
function loadTaskTable(){
	
	// get list type
	var taskListType = $('#taskListTypeSelect').val();
	
	// load table content
	$('#tasks-list-table').fadeOut('1000', function() {				
		$('#tasks-list-table').load('task/tasks', {taskListType: taskListType,  _csrf : csrf_token});
	}).fadeIn('1000');
}
