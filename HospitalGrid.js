function hospitalSlickGrid() {
	var dataView;
	var grid;
	var hospitalData = document.getElementById("hospitalinfo").value;
	var data = JSON.parse(hospitalData);

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
	var columnFilters = {};
	var columns = [{
		id: "hospitalid",
		name: "Hospital Code",
		field: "hospitalid",
		width: 137
	}, {
		id: "hospitalname",
		name: "Hospital Name",
		field: "hospitalname",
		width: 280
	}, {
		id: "hospitalemail",
		name: "Email",
		field: "hospitalemail",
		width: 350
	}, {
		id: "hospitalmobileno",
		name: "Mobile No",
		field: "hospitalmobileno",
		width: 130
	}, {
		id: "hospitaladdress",
		name: "Address",
		field: "hospitaladdress",
		width: 457
	},];

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
