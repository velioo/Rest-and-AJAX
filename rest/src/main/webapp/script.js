function getAnimeList() {
	$.ajax({
		url: "http://localhost:8181/rest/api/animes",
		type: "GET",
		dataType: "json",
		success: function(data) {
			console.log(data);
			var th = $('<tr id="headers">');
			th.append('<th> Name </th>');
			th.append('<th> Type </th>');
			th.append('<th> Studio </th>');
			th.append('<th> Episodes </th>');				
			th.append('<th> Air year </th>');				
			th.append('</tr>');
			$('#usersTable').append(th);
			$.each(data, function(index) {				
				var tr = $('<tr>');
				tr.append('<td>' + data[index].name + '</td>');
				tr.append('<td>' + data[index].type + '</td>');
				tr.append('<td>' + data[index].studio + '</td>');
				tr.append('<td>' + data[index].episodes + '</td>');
				tr.append('<td>' + data[index].air_year + '</td>');
				tr.append('</tr>');				
				$('#usersTable').append(tr);
			})
		}
	});
}

$(document).ready(function() {
	getAnimeList();
});
