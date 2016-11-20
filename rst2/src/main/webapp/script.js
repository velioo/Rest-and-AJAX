var new_page = true;
var page = 0;
var perPage = 3;
var counter = 1;
var totalPages;
var count_reloads = 0;
var all = true;

function check_scroll() {
    if ($("body").height() < $(window).height()) { // stackoverflow
    	if(all == true && page < totalPages) {
	        page++;
			var filters = getFilters();
	        getAnimeList(null, null, filters);
    	}
    }
}

function sortNumber(a,b) {
    return a - b;
}

function getFilters() {
	var filters = [];
	filters.push($('#types').find(":selected").text());
	filters.push($('#studios').find(":selected").text());
	filters.push($('#episodes').find(":selected").text());
	filters.push($('#years').find(":selected").text());
	return filters.toString();
}

function getAnimeList(withName = null, awesomplete = null, filters = "All,All,All,All") {
	if(withName == "") {
		if(count_reloads > 0) {
			return;
		} 
		count_reloads++;
		perPage = 3;
	}
	$.ajax({
		url: "http://localhost:8080/rst2/api/animes",
		type: "GET",
		data:{page: page, perPage: perPage, withName: withName, filters: filters},
		dataType: "json",
		success: function(data) {
			var animes = data.data;
			console.log(data);
			if(new_page == true) {
				var types = data.types;
				var studios = data.studios.sort();
				var episodes = data.episodes.sort(sortNumber);
				var years = data.years.sort(sortNumber);
				$.each(types, function(index, value){
					$('#types').append($('<option>', {
					    value: value,
					    text: value
					}));
				});
				$.each(studios, function(index, value){
					$('#studios').append($('<option>', {
					    value: value,
					    text: value
					}));
				});
				$.each(episodes, function(index, value){
					$('#episodes').append($('<option>', {
					    value: value,
					    text: value
					}));
				});
				$.each(years, function(index, value){
					$('#years').append($('<option>', {
					    value: value,
					    text: value
					}));
				});
			}
			new_page = false;
			if(withName != null && withName != "" && awesomplete != null) {
				var animeList = [];	
				$.each(animes, function(index){
				    animeList.push(animes[index].name);
				});
				awesomplete.list = animeList;
			} else {
				if(page == 0 && awesomplete == null || withName == "") {
					if(withName == "") {
						all = true;
					}
					totalPages = data.totalPages;
					$("#usersTable").empty();
					counter = 1;
					var th = $('<tr id="headers">');
					th.append('<th> # </th>')
					th.append('<th> Name </th>');
					th.append('<th> Type </th>');
					th.append('<th> Studio </th>');
					th.append('<th> Episodes </th>');				
					th.append('<th> Air year </th>');				
					th.append('</tr>');
					$('#usersTable').append(th);
				}
				$.each(animes, function(index) {		
					var tr = $('<tr>');
					tr.append('<td>' + counter + '</td>');
					tr.append('<td>' + animes[index].name + '</td>');
					tr.append('<td>' + animes[index].type + '</td>');
					tr.append('<td>' + animes[index].studio + '</td>');
					tr.append('<td>' + animes[index].episodes + '</td>');
					tr.append('<td>' + animes[index].airYear + '</td>');
					tr.append('</tr>');				
					$('#usersTable').append(tr);
					counter++;
				})	
				$('#usersTable').trigger('addRows');
			}
		}
	});
}

$(window).scroll(function(){ // this function is from stackoverflow - to check if the scrollbar is at the bottom of the page
    if ($(window).scrollTop() >= $(document).height()-$(window).height()){
    	page++;
        getAnimeList();
    }
});

$(document).ready(function() {
	getAnimeList();

	var input = document.getElementById("name_autocomplete");
	var awesomplete = new Awesomplete(input, {
	  minChars: 1,
	  maxItems: 5,
	  autoFirst: true
	});
	
	$('#usersTable').bind('addRows', function(event){
		check_scroll();
	});
	
	$("#name_autocomplete").on("keyup", function(e){ 
		if (e.keyCode == 13) {
			page = 0;
			perPage = 100;
			counter = 1;
			count_reloads = 0;
			getAnimeList($(this).val(), null);
		} else {
			page = 0;
			perPage = 100;
			all = false;
			filters = getFilters();
			if($(this).val() == "") {
				getAnimeList(null, null, filters)
			} else {
				getAnimeList($(this).val(), awesomplete, filters)
			}
		}
	});
	
	$("#name_autocomplete").on("awesomplete-selectcomplete", function() {
		var filters = getFilters();
		page = 0;
		perPage = 100;
		counter = 1;
		count_reloads = 0;
		all = false;
		getAnimeList($(this).val(), null, filters);
	});
	
	$('select').on('change', function() {
		var filters = getFilters();
		page = 0;
		perPage = 100;
		counter = 1;
		count_reloads = 0;
		all = false;
		if($('#name_autocomplete').val() == "") {
			getAnimeList(null, null, filters)
		} else {
			getAnimeList($('#name_autocomplete').val(), null, filters)
		}
		 
	});
	
	$(window).resize(function(){
		check_scroll();
	});
	
});
