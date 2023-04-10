function bloodDonateHistorySlickGrid() {
	var dataView;
	var grid;
	var bloodDonateData = document.getElementById("blooddonatehistoryinfo").value;
	var data = JSON.parse(bloodDonateData);

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
		var formattedDate = formattedDate;
		const date = new Date(value);
		var getDay = date.toLocaleString("default", { day: "2-digit" });
		var getMonth = date.toLocaleString("default", { month: "2-digit" });
		var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
		return formattedDate =`${getDay}-${getMonth}-${getYear}`;// Format the date as a string
	}

	var columnFilters = {};
	var columns = [{
		id: "donateid",
		name: "Donate Code",
		field: "donateid",
		width: 125
	}, {
		id: "donatedonorid",
		name: "Donor Code",
		field: "donatedonorid",
		width: 125
	}, {
		id: "donatorname",
		name: "Donator Name",
		field: "donatorname",
		width: 257
	}, {
		id: "donatequantity",
		name: "Quantity",
		field: "donatequantity",
		width: 150
	}, {
		id: "donatemobileno",
		name: "Mobile No",
		field: "donatemobileno",
		width: 150
	}, {
		id: "donatebloodgroup",
		name: "Blood Group",
		field: "donatebloodgroup",
		width: 167
	}, {
		id: "donatedate",
		name: "Donate Date",
		field: "donatedate",
		formatter: dateFormatter,
		width: 180
	}, {
		id: "donatebloodbankid",
		name: "BloodBank Code",
		field: "donatebloodbankid",
		width: 200
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
