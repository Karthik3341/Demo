function searchDetails() {
	let filter = document.getElementById("search-box").value.toUpperCase();

	let myTable = document.getElementById("table");

	let tr = myTable.getElementsByTagName('tr');
	for (var i = 0; i < tr.length; i++) {
		let td = tr[i].getElementsByTagName('td')[1];
		if (td) {
			let textvalue = td.textContent || td.innerHTML;

			if (textvalue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}

		}
	}
}
