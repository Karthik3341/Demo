function hospitalBloodRequestHistorySlickGrid() {
	var dataView;
	var grid;
	var hospBloodHistoryData = document.getElementById("hospitalbloodreqinfo").value;
	var data = JSON.parse(hospBloodHistoryData);

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
		id: "selhospitalhisid",
		name: "Request Code",
		field: "selhospitalhisid",
		width: 157
	}, {
		id: "selhospitalhisname",
		name: "Requestor Code",
		field: "selhospitalhisname",
		width: 160
	}, {
		id: "selhospitalhisrequestdate",
		name: "Request Date",
		field: "selhospitalhisrequestdate",
		formatter: dateFormatter,
		width: 170
	}, {
		id: "selhospitalhisbloodgroup",
		name: "BloodGroup",
		field: "selhospitalhisbloodgroup",
		width: 160
	}, {
		id: "selhospitalhismobileno",
		name: "Mobile No",
		field: "selhospitalhismobileno",
		width: 170
	}, {
		id: "selhospitalhisquantity",
		name: "Quantity",
		field: "selhospitalhisquantity",
		width: 130
	}, {
		id: "selhospitalhisbloodbankid",
		name: "BloodBank Code",
		field: "selhospitalhisbloodbankid",
		width: 210
	}, {
		id: "selhospitalhisstatus",
		name: "Status",
		field: "selhospitalhisstatus",
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
