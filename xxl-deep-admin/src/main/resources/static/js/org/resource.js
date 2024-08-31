$(function() {

	// ---------- ---------- ---------- customer select for datatables ---------- ---------- ----------
	// Select：All
	$('#data_list').on('change', 'thead #checkAll', function() {
		var isChecked = $(this).prop('checked');
		$('#data_list tbody  input.checkItem').each(function(){
			$(this).prop('checked', isChecked);
		});
		selectStatusEffctOpt();
	});
	// Select：Item (all select will fresh '#checkAll')
	$('#data_list').on('change', 'tbody input.checkItem', function() {
		var newStatus = $('#data_list tbody input.checkItem').length>0
			&& $('#data_list tbody input.checkItem').length === $('#data_list tbody input.checkItem:checked').length;
		$('#checkAll').prop('checked', newStatus);
		selectStatusEffctOpt();
	});
	// Select: status init
	function selectStatusInit(){
		$('#checkAll').prop('checked', false);
		$('#data_list tbody input.checkItem').each(function(){
			$(this).prop('checked', false);
		});
		selectStatusEffctOpt();
	}
	// Select: find ids
	function selectIdsFind(){
		var checkIds = [];
		$('#data_list tbody input.checkItem').each(function(){
			if ($(this).prop('checked')) {
				checkIds.push( $(this).attr('data-id') );
			}
		});
		return checkIds;
	}
	// Select: refresh operation status
	function selectStatusEffctOpt(){
		var selectLen = selectIdsFind().length;
		if (selectLen > 0) {
			$("#data_operation .delete").removeClass('disabled');
		} else {
			$("#data_operation .delete").addClass('disabled');
		}
		if (selectLen === 1) {
			$("#data_operation .update").removeClass('disabled');
		} else {
			$("#data_operation .update").addClass('disabled');
		}

	}

	// ---------- ---------- ---------- main table  ---------- ---------- ----------
	// treeGrid：https://github.com/lhmyy521125/dataTables.treeGrid
	// table data
	var tableData = {};
	// init date tables
	var mainDataTable = $("#data_list").dataTable({
		"deferRender": false,
		"processing" : true,
		"serverSide": true,
		"ajax": {
			url: base_url + "/org/resource/treeList",
			type:"post",
			// request data
			data : function ( d ) {
				var obj = {};
				obj.name = $('#data_filter .name').val();
				obj.status = $('#data_filter .status').val();
				obj.start = d.start;
				obj.length = d.length;
				return obj;
			},
			// response data filter
			dataFilter: function (originData) {
				var originJson = $.parseJSON(originData);
				return JSON.stringify({
					data: originJson.data?originJson.data: {}
				});
			}
		},
		"searching": false,
		"ordering": false,
		//"scrollX": true,																		// scroll x，close self-adaption
		/**
		 l - Length changing 改变每页显示多少条数据的控件
		 f - Filtering input 即时搜索框控件
		 t - The Table 表格本身
		 i - Information 表格相关信息控件
		 p - Pagination 分页控件
		 r - pRocessing 加载等待显示信息
		 **/
		"dom": "tr",
		"drawCallback": function( settings ) {
			selectStatusInit();
		},
		"columns": [
			{
				"title": '<input align="center" type="checkbox" id="checkAll" >',
				"data": 'id',
				"visible" : true,
				"width":'5%',
				"render": function ( data, type, row ) {
					tableData['key'+row.id] = row;
					return '<input align="center" type="checkbox" class="checkItem" data-id="'+ row.id +'"  >';
				}
			},
			{
				"title": '层级',
				"className": 'treegrid-control',
				"data": 'parentId',
				"width":'5%',
				"render": function ( data, type, row ){
					if (row.children != null && row.children.length > 0) {
						return '<i class="fa fa-fw fa-chevron-right" ></i>';
					}
					return '';
				}
			},
			{
				"title": I18n.resource_tips + I18n.resource_name,
				"data": 'name',
				"width":'30%'
			},
			{
				"title": I18n.resource_tips + I18n.resource_type,
				"data": 'type',
				"width":'10%',
				"render": function ( data, type, row ) {
					var result = "";
					$('#addModal select[name="type"] option').each(function(){
						if ( data.toString() === $(this).val() ) {
							result = $(this).text();
						}
					});
					return result;
				}
			},
			{
				"title": I18n.resource_permission,
				"data": 'permission',
				"width":'15%'
			},
			{
				"title": I18n.resource_tips + I18n.resource_url,
				"data": 'url',
				"width":'20%'
			},
			{
				"title": I18n.resource_tips + I18n.resource_order,
				"data": 'order',
				"width":'10%'
			},
			{
				"title": I18n.resource_status,
				"data": 'status',
				"visible" : true,
				"width":'10%',
				"render": function ( data, type, row ) {
					var result = "";
					$('#addModal select[name="status"] option').each(function(){
						if ( data.toString() === $(this).val() ) {
							result = $(this).text();
						}
					});
					return result;
				}
			}
		],
		"columnDefs": [
			{
				"defaultContent": "",
				"targets": "_all"
			}
		],
		"language" : {
			"sProcessing" : I18n.dataTable_sProcessing ,
			"sZeroRecords" : I18n.dataTable_sZeroRecords ,
			"sEmptyTable" : I18n.dataTable_sEmptyTable ,
			"sLoadingRecords" : I18n.dataTable_sLoadingRecords ,
			"sInfoThousands" : ","
		}
	});
	// tree grid : expandAll / collapseAll
	tree = new $.fn.dataTable.TreeGrid(mainDataTable,{
		left: 20,
		expandAll: true,
		expandIcon: '<i class="fa fa-fw fa-chevron-right" ></i>',
		collapseIcon: '<i class="fa fa-fw fa-chevron-down" ></i>'
	});

	// search btn
	$('#data_filter .searchBtn').on('click', function(){
		mainDataTable.fnDraw(false);
		//mainDataTable.draw(false);
	});

	// ---------- ---------- ---------- tree operation ---------- ---------- ----------
	var expandOrCollapse_val = 0;
	$("#data_operation").on('click', '.expandAndCollapse',function() {
		if ((expandOrCollapse_val++)%2 === 0) {
			tree.collapseAll();
		} else {
			tree.expandAll();
		}
	});

	// ---------- ---------- ---------- delete operation ---------- ---------- ----------
	// delete
	$("#data_operation").on('click', '.delete',function() {

		// find select ids
		var selectIds = selectIdsFind();
		if (selectIds.length <= 0) {
			layer.msg(I18n.system_please_choose + I18n.system_data);
			return;
		}

		// do delete
		layer.confirm( I18n.system_ok + I18n.system_opt_del + '?', {
			icon: 3,
			title: I18n.system_tips ,
			btn: [ I18n.system_ok, I18n.system_cancel ]
		}, function(index){
			layer.close(index);

			$.ajax({
				type : 'POST',
				url : base_url + "/org/resource/delete",
				data : {
					"ids" : selectIds
				},
				dataType : "json",
				success : function(data){
					if (data.code == 200) {
						layer.msg( I18n.system_opt_del + I18n.system_success );
						mainDataTable.fnDraw(false);	// false，refresh current page；true，all refresh
					} else {
						layer.msg( data.msg || I18n.system_opt_del + I18n.system_fail );
					}
				},
				error: function(xhr, status, error) {
					layer.open({
						icon: '2',
						content: (I18n.system_opt_del + I18n.system_fail)
					});
				}
			});
		});
	});

	// ---------- ---------- ---------- ztree ---------- ---------- ----------
	var zTreeObj;
	function initTree(){
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: true,
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: "0"
				}
			}
		};

		var zNodes = [
			{id: 1, pId: 0, name: "资源A", open: true},
			{id: 5, pId: 1, name: "资源A1"},
			{id: 6, pId: 1, name: "资源A2"},

			{id: 2, pId: 0, name: "资源B", open: false},
			{id: 8, pId: 2, name: "资源B1"},
			{id: 11, pId: 2, name: "资源B2"}
		];

		zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes); //初始化树
		zTreeObj.expandAll(true);    //true 节点全部展开、false节点收缩
	}

	// open
	$(".selectParent").click(function(){
		$('#treeModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	// choose
	$('#treeModal .choose').click(function(){

		// valid choose
		if (zTreeObj.getSelectedNodes().length < 1) {
			layer.msg( I18n.system_please_choose + I18n.resource_parent );
			return;
		}

		// fill choose data, todo-
		$("#addModal .form input[name=parentId]").val( zTreeObj.getSelectedNodes()[0].id );
		$("#addModal .form input[name=parentName]").val( zTreeObj.getSelectedNodes()[0].name );

		$("#updateModal .form input[name=parentId]").val( zTreeObj.getSelectedNodes()[0].id );
		$("#updateModal .form input[name=parentName]").val( zTreeObj.getSelectedNodes()[0].name );

		$('#treeModal').modal('hide');
	});

	// ---------- ---------- ---------- add operation ---------- ---------- ----------
	// add modal
	$("#data_operation .add").click(function(){

		// todo，reset not work
		initTree();
		$("#addModal .form input[name=parentId]").val( 0 );

		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : true,
		rules : {
			name : {
				required : true,
				rangelength:[2, 50]
			},
			permission : {
				required : true,
				rangelength:[2, 50]
			},
			order : {
				required : true,
				range:[1, 99999999]
			}
		},
		messages : {
			name : {
				required : I18n.system_please_input + I18n.resource_name,
				rangelength: I18n.system_lengh_limit + "[2-50]"
			},
			permission : {
				required : I18n.system_please_input + I18n.resource_permission,
				rangelength: I18n.system_lengh_limit + "[2-20]"
			},
			order : {
				required : I18n.system_please_input,
				range: I18n.system_num_range + " 1~99999999"
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement : function(error, element) {
			element.parent('div').append(error);
		},
		submitHandler : function(form) {

			// request
			var paramData = {
				"parentId": $("#addModal .form input[name=parentId]").val(),
				"name": $("#addModal .form input[name=name]").val(),
				"type": $("#addModal .form select[name=type]").val(),
				"permission": $("#addModal .form input[name=permission]").val(),
				"url": $("#addModal .form input[name=url]").val(),
				"order": $("#addModal .form input[name=order]").val(),
				"status": $("#addModal .form select[name=status]").val()
			};

			// post
			$.post(base_url + "/org/resource/insert", paramData, function(data, status) {
				if (data.code == "200") {
					$('#addModal').modal('hide');

					layer.msg( I18n.system_opt_add + I18n.system_success );
					mainDataTable.fnDraw();
				} else {
					layer.open({
						title: I18n.system_tips ,
						btn: [ I18n.system_ok ],
						content: (data.msg || I18n.system_opt_add + I18n.system_fail ),
						icon: '2'
					});
				}
			});
		}
	});
	$("#addModal").on('hide.bs.modal', function () {
		addModalValidate.resetForm();

		$("#addModal .form")[0].reset();
		$("#addModal .form .form-group").removeClass("has-error");
	});

	// ---------- ---------- ---------- update operation ---------- ---------- ----------
	$("#data_operation .update").click(function(){

		// find select ids
		var selectIds = selectIdsFind();
		if (selectIds.length != 1) {
			layer.msg(I18n.system_please_choose + I18n.system_one + I18n.system_data);
			return;
		}
		var row = tableData[ 'key' + selectIds[0] ];

		// base data
		$("#updateModal .form input[name=id]").val( row.id );
		$("#updateModal .form input[name=parentId]").val( row.parentId );
		$("#updateModal .form input[name=name]").val( row.name );
		$("#updateModal .form select[name=type]").val( row.type );
		$("#updateModal .form input[name=permission]").val( row.permission );
		$("#updateModal .form input[name=url]").val( row.url );
		$("#updateModal .form input[name=order]").val( row.order );
		$("#updateModal .form select[name=status]").val( row.status );

		// 设置 tree 选中
		initTree();
		if (row.id > 0) {
			var chooseNode = zTreeObj.getNodeByParam("id", row.id, null);
			zTreeObj.selectNode(chooseNode);

			$("#updateModal .form input[name=parentName]").val( chooseNode.name );
		}

		// show
		$('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var updateModalValidate = $("#updateModal .form").validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : true,
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement : function(error, element) {
			element.parent('div').append(error);
		},
		rules : {
			name : {
				required : true,
				rangelength:[2, 50]
			},
			permission : {
				required : true,
				rangelength:[2, 50]
			},
			order : {
				required : true,
				range:[1, 99999999]
			}
		},
		messages : {
			name : {
				required : I18n.system_please_input + I18n.user_password,
				rangelength: I18n.system_lengh_limit + "[2-50]"
			},
			permission : {
				required : I18n.system_please_input + I18n.user_real_name,
				rangelength: I18n.system_lengh_limit + "[2-20]"
			},
			order : {
				required : I18n.system_please_input,
				range: I18n.system_num_range + " 1~99999999"
			}
		},
		submitHandler : function(form) {

			// request
			var paramData = {
				"id": $("#updateModal .form input[name=id]").val(),
				"parentId": $("#updateModal .form input[name=parentId]").val(),
				"name": $("#updateModal .form input[name=name]").val(),
				"type": $("#updateModal .form select[name=type]").val(),
				"permission": $("#updateModal .form input[name=permission]").val(),
				"url": $("#updateModal .form input[name=url]").val(),
				"order": $("#updateModal .form input[name=order]").val(),
				"status": $("#updateModal .form select[name=status]").val()
			};

			$.post(base_url + "/org/resource/update", paramData, function(data, status) {
				if (data.code == "200") {
					$('#updateModal').modal('hide');

					layer.msg( I18n.system_opt_edit + I18n.system_success );
					mainDataTable.fnDraw(false);
				} else {
					layer.open({
						title: I18n.system_tips ,
						btn: [ I18n.system_ok ],
						content: (data.msg || I18n.system_opt_edit + I18n.system_fail ),
						icon: '2'
					});
				}
			});
		}
	});
	$("#updateModal").on('hide.bs.modal', function () {
		updateModalValidate.resetForm();

		$("#updateModal .form")[0].reset();
		$("#updateModal .form .form-group").removeClass("has-error");
	});

});
