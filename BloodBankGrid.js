function bloodBankSlickGrid() {
	var dataView;
	var grid;
	var bloodBankData = document.getElementById("bloodbankinfo").value;
	var data = JSON.parse(bloodBankData);

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
		id: "bloobankid",
		name: "Code",
		field: "bloobankid",
		width: 60
	}, {
		id: "bloodbankname",
		name: "Bloodbank Name",
		field: "bloodbankname",
		width: 240
	}, {
		id: "contactnumber",
		name: "Contact No",
		field: "contactnumber",
		width: 127
	}, {
		id: "bloodbankemail",
		name: "Email",
		field: "bloodbankemail",
		width: 300
	}, {
		id: "bloodbankaddress",
		name: "Address",
		field: "bloodbankaddress",
		width: 260
	}, {
		id: "apositive",
		name: "A+",
		field: "apositive",
		width: 45
	}, {
		id: "anegative",
		name: "A-",
		field: "anegative",
		width: 45
	}, {
		id: "bpositive",
		name: "B+",
		field: "bpositive",
		width: 45
	}, {
		id: "bnegative",
		name: "B-",
		field: "bnegative",
		width: 45
	}, {
		id: "opositive",
		name: "O+",
		field: "opositive",
		width: 45
	}, {
		id: "onegative",
		name: "O-",
		field: "onegative",
		width: 45
	}, {
		id: "abpositive",
		name: "AB+",
		field: "abpositive",
		width: 50
	}, {
		id: "abnegative",
		name: "AB-",
		field: "abnegative",
		width: 50
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
