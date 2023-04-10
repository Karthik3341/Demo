function viewBloodRequestList() {

	var dataView;
	var grid;
	var data = document.getElementById("viewReq").value

	/*<![CDATA[*/
	//var data = /*[[${managerEmplist}]]*/'Emp';//

	console.log(data);


	var data1 = JSON.parse(data);

	var requestIdList = [];
	for (var i = 0; i < data1.length; i++) {
		requestIdList.push(data1[i]['requestid']);
		console.log(data1[i]['requestid']);

	}
	console.log(requestIdList);

	var nextId = 1;
	data1.forEach(function(item) {
		// Generate a unique id using the nextId value
		var itemId = "item_" + nextId;

		// Set the id property of the item object
		item.id = itemId;

		// Increment the nextId value
		nextId++;
	});
	// Use the updated items array list with unique ids
	/* unique id end */
	var options = {
		enableCellNavigation: true,
		showHeaderRow: true,
		headerRowHeight: 30,
		explicitInitialization: true
	};
	var columnFilters = {};
	function checkboxFormatter(row, cell, value, columnDef, dataContext) {
		let a = dataContext.requestid;
		return '<input type="checkbox" value="' + a + '" name="checkName" id="checkBox"' + (value ? 'checked="checked"' : '') + '/>';
	}
	function dateFormatter(row, cell, value, columnDef, dataContext) {
		const date = new Date(value);
		var getDay = date.toLocaleString("default", { day: "2-digit" });
		var getMonth = date.toLocaleString("default", { month: "2-digit" });
		var getYear = date.toLocaleString("default", { year: "numeric" }); // Set the desired date format
		return formattedDate = getDay + "-" + getMonth + "-" + getYear;// Format the date as a string
	}

	//var check= document.getElementById("checkBox");
	var check = document.querySelectorAll('input[type="checkbox"]:checked');
	console.log(check);
	var columns = [{
			id : "Select",
			name :"Select",
			field :"Select",
			width : 50,
			formatter : checkboxFormatter
		} ,{
			id : "requestid",
			name : "Request Code",
			field : "requestid",
			width : 100
		}, {
			id : "requestingid",
			name : "Requestor Code",
			field : "requestingid",
			width : 100
		}, {
			id : "requestortype",
			name : "Requestor Type",
			field : "requestortype",
			width : 138
		}, {
			id : "bloodrequestdate",
			name : "Request Date",
			field : "bloodrequestdate",
			formatter : dateFormatter,
			width : 128
		}, {
			id : "requestbloodgroup",
			name : "BloodGroup",
			field : "requestbloodgroup",
			width : 112
		}, {
			id : "requestquantity",
			name : "Quantity",
			field : "requestquantity",
			width : 85
		}, {
			id : "requestlocation",
			name : "Location",
			field : "requestlocation",
			width : 290
		}, {
			id : "requestmobileno",
			name : "Mobile No",
			field : "requestmobileno",
			width : 120
		} , {
			id : "requestbloodbankid",
			name : "BloodBank Code",
			field : "requestbloodbankid",
			width : 100
		}, {
			id : "requeststatus",
			name : "Status",
			field : "requeststatus",
			width : 110
		} ];



	/* filter start */
	$(function() {
		function filter(item) {
			for (var columnId in columnFilters) {
				if (columnId !== undefined
					&& columnFilters[columnId] !== "") {
					var c = grid.getColumns()[grid.getColumnIndex(columnId)];
					if (item[c.field] !== columnFilters[columnId]) {
						return false;
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
		dataView.setItems(data1);
		dataView.setFilter(filter);
		dataView.endUpdate();
	})
}

