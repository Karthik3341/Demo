function selectedDonorSlickGrid() {
	var dataView;
	var grid;
	var selectedBloodRequestData = document.getElementById("selecteddonorinfo").value;
	var data = JSON.parse(selectedBloodRequestData);

	console.log(data);
	var nextId = 1;
	data.forEach(function(item) {
		var itemId = "item_" + nextId;
		item.id = itemId;
		nextId++;
	});
	console.log(data);
	var options = {
		enableCellNavigation: true,
		showHeaderRow: true,
		headerRowHeight: 40,
		explicitInitialization: true
	};

	function dateFormatter(row, cell, value, columnDef, dataContext) {
		const date = new Date(value);
		var getDay = date.toLocaleString("default", { day: "2-digit" });
		var getMonth = date.toLocaleString("default", { month: "2-digit" });
		var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
		return formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
	}

	var columnFilters = {};
	var columns = [{
		id: "selecteddonorid",
		name: "Code",
		field: "selecteddonorid",
		width: 55
	}, {
		id: "selecteddonorname",
		name: "Donor Name",
		field: "selecteddonorname",
		width: 120
	}, {
		id: "selecteddob",
		name: "DOB",
		field: "selecteddob",
		formatter: dateFormatter,
		width: 120
	}, {
		id: "selectedweight",
		name: "Weight",
		field: "selectedweight",
		width: 70
	}, {
		id: "selecteddonorgender",
		name: "Gender",
		field: "selecteddonorgender",
		width: 70
	}, {
		id: "selecteddonorbloodgroup",
		name: "BloodGroup",
		field: "selecteddonorbloodgroup",
		width: 45
	}, {
		id: "selecteddonormobilenumber",
		name: "Mobile No",
		field: "selecteddonormobilenumber",
		width: 120
	},
	{
		id: "selecteddonoremail",
		name: "Email",
		field: "selecteddonoremail",
		width: 215
	}, {
		id: "selecteddonorusername",
		name: "Username",
		field: "selecteddonorusername",
		width: 103
	}, {
		id: "selecteddonorregisterdate",
		name: "Register Date",
		field: "selecteddonorregisterdate",
		formatter: dateFormatter,
		width: 129
	}, {
		id: "selecteddonoraddress",
		name: "Address",
		field: "selecteddonoraddress",
		width: 310
	}];

	$(function() {
		function filter(item) {
			for (var columnId in columnFilters) {
				if (columnId !== undefined && columnFilters[columnId] !== "") {
					var column = grid.getColumns()[grid.getColumnIndex(columnId)];

					if (item[column.field] !== undefined) {
						var filterResult = typeof item[column.field].indexOf === 'function'
							? (item[column.field].indexOf(columnFilters[columnId]) === -1)
							: (item[column.field] !== columnFilters[columnId]);

						if (filterResult) {
							return false;
						}
					}
				}
			}
			return true;
		}

		/* filter end */
		dataView = new Slick.Data.DataView();
		grid = new Slick.Grid("#myGrid", dataView, columns, options);
		/* filter start */
		dataView.onRowCountChanged.subscribe(function(e, args) {
			grid.updateRowCount();
			grid.render();
		});
		dataView.onRowsChanged.subscribe(function(e, args) {
			grid.invalidateRows(args.rows);
			grid.render();
		});
		$(grid.getHeaderRow()).delegate(":input", "change keyup",

			function(e) {
				var columnId = $(this).data("columnId");
				if (columnId != null) {
					columnFilters[columnId] = $.trim($(this).val());
					dataView.refresh();
				}
			});

		grid.onHeaderRowCellRendered.subscribe(function(e, args) {
			$(args.node).empty();
			$("<input type='text'>").data("columnId", args.column.id).val(
				columnFilters[args.column.id]).appendTo(args.node);
		});

		grid.init();
		dataView.beginUpdate();
		dataView.setItems(data);
		dataView.setFilter(filter);
		dataView.endUpdate();
	})
}
