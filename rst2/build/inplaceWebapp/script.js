var page = 0;
var perPage = 25;

function getAnimeList() {
	$.ajax({
		url: "http://localhost:8080/rst2/api/animes",
		type: "GET",
		data:{page: page, perPage: perPage},
		dataType: "json",
		success: function(data) {
			var animes = data.data;
			if(page == 0) {
				console.log(data);
				var th = $('<tr id="headers">');
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
				tr.append('<td>' + animes[index].name + '</td>');
				tr.append('<td>' + animes[index].type + '</td>');
				tr.append('<td>' + animes[index].studio + '</td>');
				tr.append('<td>' + animes[index].episodes + '</td>');
				tr.append('<td>' + animes[index].air_year + '</td>');
				tr.append('</tr>');				
				$('#usersTable').append(tr);
			})
		}
	});
}

function check_scroll_enabled() {
    if ($("body").height() < $(window).height()) {
        page++;
        getAnimeList();
    }
}

$(window).scroll(function(){ // this function is from stackoverflow - to check if the scrollbar is at the bottom of the page
    if ($(window).scrollTop() >= $(document).height()-$(window).height()){
    	page++;
        getAnimeList();
    }
});

$(document).ready(function() {
	getAnimeList();
	perPage = 3;
});
