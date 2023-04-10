function donorSlickGrid() {
	var dataView;
	var grid;
	var donorData = document.getElementById("donorinfo").value;
	var data = JSON.parse(donorData);

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
		id: "donorid",
		name: "Donor Code",
		field: "donorid",
		width: 120
	}, {
		id: "donorname",
		name: "Donor Name",
		field: "donorname",
		width: 170
	}, {
		id: "dob",
		name: "DOB",
		field: "dob",
		width: 125,
		formatter : dateFormatter
	}, {
		id: "donorgender",
		name: "Gender",
		field: "donorgender",
		width: 100
	}, {
		id: "donorbloodgroup",
		name: "BloodGroup",
		field: "donorbloodgroup",
		width: 100
	}, {
		id: "donormobilenumber",
		name: "Mobile No",
		field: "donormobilenumber",
		width: 130
	},
	{
		id: "donoremail",
		name: "Email",
		field: "donoremail",
		width: 270
	}, {
		id: "donoraddress",
		name: "Address",
		field: "donoraddress",
		width: 340
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
