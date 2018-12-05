// * * * CSRF TOKEN * * * 
var csrf_token = $("meta[name='_csrf']").attr("content");

// * * * months arrays * * *
var monthNamesEN = ["January", "February", "March", "April", "May", "June",
	  "July", "August", "September", "October", "November", "December"	];
var monthNamesPL = ["Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec",
	  "Lipiec", "Sierpień", "Wrzesirń", "Październik", "Listopad", "Grudzień"];	 

// * * * User language * * * 
var userLang = navigator.language;

//* * * change style on tab click * * * 
$('#v-pills-projects-tab').click(function(){	
	$('#v-pills-projects-tab').removeClass('text-muted');
	$('#v-pills-messages-tab').addClass('text-muted');	
});

//* * * change style on tab click * * * 
$('#v-pills-messages-tab').click(function(){	
	$('#v-pills-projects-tab').addClass('text-muted');
	$('#v-pills-messages-tab').removeClass('text-muted');	
});

// * * * set current month on page start * * *
var date = new Date();
if(userLang == "pl-PL")
	$("#inputMonth, #messageInputMonth").val(monthNamesPL[date.getMonth()]+" "+date.getFullYear());
else 
	$("#inputMonth, #messageInputMonth").val(monthNamesEN[date.getMonth()]+" "+date.getFullYear());

// * * * load general statistics and monthly chart on page start * * *
var resultSet = prepareDataToCreateChart($("#inputMonth").val());	
getProjectChartWithMonthlyData(resultSet[0], resultSet[1], resultSet[2], 'bar', 'monthlyChart',resultSet[3])
loadGeneralStatisticsForSelectedProject($("option:selected").val());

//* * * load messages general statistics and monthly chart on page start * * *
var resultSet = prepareDataToCreateChart($("#messageInputMonth").val());
getMessagesChartWithMonthlyData(resultSet[0], resultSet[1], resultSet[2], 'bar', 'messageMonthlyChart',resultSet[3])
loadGeneralStatisticsForMessages();

// * * * Load project monthly chart on button click* * * *
$('#getMonthlyChart').on('click', function(){	
	var resultSet = prepareDataToCreateChart($("#inputMonth").val());	
	getProjectChartWithMonthlyData(resultSet[0], resultSet[1], resultSet[2], 'bar', 'monthlyChart',resultSet[3])
});

// * * * Load message monthly chart on button click * * *
$('#getMessageMonthlyChart').on('click', function(){	
	var resultSet = prepareDataToCreateChart($("#messageInputMonth").val());	
	getMessagesChartWithMonthlyData(resultSet[0], resultSet[1], resultSet[2], 'bar', 'messageMonthlyChart',resultSet[3])
});

// * * * Load monthly chart and general statistics on project change * * *
$('#projectStatisticSelect').on('change', function () {
	var resultSet = prepareDataToCreateChart($("#inputMonth").val());	
	getProjectChartWithMonthlyData(resultSet[0], resultSet[1], resultSet[2], 'bar', 'monthlyChart',resultSet[3])
	loadGeneralStatisticsForSelectedProject($("option:selected").val());
});

// * * * FUNCTIONS * * *
// * * * monthly chart for projects * * *
var myChart;

function getProjectChartWithMonthlyData(startDate, endDate, label, barType, chartLocationId, monthLength){
	
	// load monthly chart data
	$.post( "statistics/get-monthly-data-for-selected-project", {	
		startDate: startDate,
		endDate: endDate,
		projectName: $('#projectStatisticSelect').val(),
		monthLength: monthLength,
		_csrf: csrf_token
	}, function(data){	
		
		// destroy previous char if exists to prevent unexpected behaviour
		if(myChart)
			myChart.destroy();			
		
		// extract data from data and populate arrays
		var labelArray = new Array();
		var dataArray = new Array();
		var backgroundColorArray = new Array();
		var borderColor = new Array();
		var tempArray = new Array();
		
		for(var i=0;i< data.length; i++){			
			tempArray=data[i];			
			labelArray.push(i+1);
			dataArray.push(tempArray[1]);
			
			if (i%2==0){
				backgroundColorArray.push('rgba(224, 75, 38, 0.2)');
				borderColor.push('rgba(224, 75, 38, 1)')
			} else {
				backgroundColorArray.push('rgba(107, 178, 194, 0.2)');
				borderColor.push('rgba(107, 178, 194, 1)')				
			}	
		}
		
		// chart options
		  var ctxB = document.getElementById(chartLocationId).getContext('2d');
		  myChart = new Chart(ctxB, {
		    type: barType,
		    data: {
		      labels: labelArray,
		      datasets: [{
		        label: label,
		        data: dataArray,
		        backgroundColor: backgroundColorArray,
		        borderColor: borderColor,
		        borderWidth: 1
		      }]
		    },
		    options: {
		      scales: {
		        yAxes: [{
		          ticks: {
		            beginAtZero: true
		          }
		        }]
		      }
		    }
		  });	
	});	
}
//* * * * * * monthly chart for messages* * * * * * * 
var myMessagesChart;

function getMessagesChartWithMonthlyData(startDate, endDate, label, barType, chartLocationId, monthLength){

	// load monthly chart data
	$.post( "statistics/get-monthly-data-for-messages", {	
		startDate: startDate,
		endDate: endDate,
		monthLength: monthLength,
		_csrf: csrf_token
	}, function(data){	
		
		// destroy previous char if exists to prevent unexpected behaviour
		if(myMessagesChart)
			myMessagesChart.destroy();			
		
		// extract data from data and populate arrays
		var labelArray = new Array();
		var dataArray = new Array();
		var backgroundColorArray = new Array();
		var borderColor = new Array();
		var tempArray = new Array();
		
		for(var i=0;i< data.length; i++){			
			tempArray=data[i];			
			labelArray.push(i+1);
			dataArray.push(tempArray[1]);
			
			if (i%2==0){
				backgroundColorArray.push('rgba(224, 75, 38, 0.2)');
				borderColor.push('rgba(224, 75, 38, 1)')
			} else {
				backgroundColorArray.push('rgba(107, 178, 194, 0.2)');
				borderColor.push('rgba(107, 178, 194, 1)')				
			}	
		}
		// chart options
		  var ctxB = document.getElementById(chartLocationId).getContext('2d');
		  myMessagesChart = new Chart(ctxB, {
		    type: barType,
		    data: {
		      labels: labelArray,
		      datasets: [{
		        label: label,
		        data: dataArray,
		        backgroundColor: backgroundColorArray,
		        borderColor: borderColor,
		        borderWidth: 1
		      }]
		    },
		    options: {
		      scales: {
		        yAxes: [{
		          ticks: {
		            beginAtZero: true
		          }
		        }]
		      }
		    }
		  });	
	});	
}
//* * * * * * * *  prepare data to create chart * * * * * * * * * * * * 
function prepareDataToCreateChart(inputMonthValue){	
	
	// extract year and monthName from inputMonthValue
	var startIndex = 0;
	for(var i=0; i<inputMonthValue.length ; i++ ){
		if($.isNumeric(inputMonthValue.charAt(i))){
			startIndex = i;
			break;
		}
	}
	
	var selectedMonthName = inputMonthValue.substring(0,startIndex-1);
	var selectedYear = inputMonthValue.substring(startIndex,inputMonthValue.length);
	
	// get month index based on month name
	var selectedMonthIndex=-1;
	
	for(var i=0 ; i < 12 ; i++) {
		if(monthNamesPL[i] ==  selectedMonthName || monthNamesEN[i] == selectedMonthName){
			selectedMonthIndex=i;
			break;
		}
	}
	
	// create startDate, endDate and monthLength based on extracted values
	var tempDate = new Date();
	tempDate.setFullYear(selectedYear);
	tempDate.setMonth(selectedMonthIndex);
	
	var tempStartDate = new Date(tempDate.getFullYear(), tempDate.getMonth(), 1);
	var tempEndDate = new Date(tempDate.getFullYear(), tempDate.getMonth() + 1, 0);
	
	var startDate = tempStartDate.getFullYear()+'-'+(tempStartDate.getMonth() + 1)+'-'+tempStartDate.getDate();
	var endDate = tempEndDate.getFullYear()+'-'+(tempEndDate.getMonth() + 1)+'-'+tempEndDate.getDate();
	
	var monthLength = tempEndDate.getDate();
	
	var resultSet = [startDate, endDate, selectedMonthName, monthLength]
	
	return resultSet;
}


// * * * load general statistics for selected project * * * 
function loadGeneralStatisticsForSelectedProject(projectName){			
	$.post("statistics/get-general-statistics-for-selected-project",{projectName: projectName, _csrf: csrf_token}, function(data){
		$('#projectToday').fadeOut('1000', function() {				
			$('#projectToday').text(data[0]);
		}).fadeIn('1000');	
		$('#projectYesterday').fadeOut('1000', function() {				
			$('#projectYesterday').text(data[1]);
		}).fadeIn('1000');	
		$('#project7Days').fadeOut('1000', function() {				
			$('#project7Days').text(data[2]);
		}).fadeIn('1000');	
		$('#project30Days').fadeOut('1000', function() {				
			$('#project30Days').text(data[3]);
		}).fadeIn('1000');	
		$('#projectEver').fadeOut('1000', function() {				
			$('#projectEver').text(data[4]);
		}).fadeIn('1000');				
	});
}

// * * * load general statistics for messages * * * 
function loadGeneralStatisticsForMessages(){	
	$.post("statistics/get-general-statistics-for-messages"	,{ _csrf: csrf_token}, function(data){
		$('#messagesToday').fadeOut('1000', function() {				
			$('#messagesToday').text(data[0]);
		}).fadeIn('1000');	
		$('#messagesYesterday').fadeOut('1000', function() {				
			$('#messagesYesterday').text(data[1]);
		}).fadeIn('1000');	
		$('#messages7Days').fadeOut('1000', function() {				
			$('#messages7Days').text(data[2]);
		}).fadeIn('1000');	
		$('#messages30Days').fadeOut('1000', function() {				
			$('#messages30Days').text(data[3]);
		}).fadeIn('1000');	
		$('#messagesEver').fadeOut('1000', function() {				
			$('#messagesEver').text(data[4]);
		}).fadeIn('1000');				
	});
}
		