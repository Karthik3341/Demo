function selectedBloodRequestSlickgrid() {
	var dataView;
	var grid;
	var selectedBloodRequestData = document.getElementById("selectedbloodrequestinfo").value;
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
		id: "selectedrequestid",
		name: "Request Code",
		field: "selectedrequestid",
		width: 100
	}, {
		id: "selectedrequestingid",
		name: "Requestor Code",
		field: "selectedrequestingid",
		width: 110
	}, {
		id: "selectedrequestortype",
		name: "Requestor Type",
		field: "selectedrequestortype",
		width: 125
	}, {
		id: "selectedbloodrequestdate",
		name: "Request Date",
		field: "selectedbloodrequestdate",
		formatter : dateFormatter,
		width: 128
	}, {
		id: "selectedrequestbloodgroup",
		name: "BloodGroup",
		field: "selectedrequestbloodgroup",
		width: 80
	}, {
		id: "selectedrequestquantity",
		name: "Quantity",
		field: "selectedrequestquantity",
		width: 75
	}, {
		id: "selectedrequestlocation",
		name: "Location",
		field: "selectedrequestlocation",
		width: 350
	}, {
		id: "selectedrequestmobileno",
		name: "Mobile No",
		field: "selectedrequestmobileno",
		width: 120
	}, {
		id: "selectedrequestbloodbankid",
		name: "BloodBank Code",
		field: "selectedrequestbloodbankid",
		width: 125
	}, {
		id: "selectedrequeststatus",
		name: "Status",
		field: "selectedrequeststatus",
		width: 143
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
