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
	// init date tables
	var mainDataTable = $("#data_list").dataTable({
		"deferRender": true,
		"processing" : true, 
	    "serverSide": true,
		"ajax": {
			url: base_url + "/org/user/pageList",
			type:"post",
			// request data
	        data : function ( d ) {
	        	var obj = {};
                obj.username = $('#data_filter .username').val();
                obj.status = $('#data_filter .status').val();
	        	obj.start = d.start;
	        	obj.length = d.length;
                return obj;
            },
			// response data filter
			dataFilter: function (originData) {
				var originJson = $.parseJSON(originData);
				return JSON.stringify({
					recordsTotal: originJson.data.totalCount,
					recordsFiltered: originJson.data.totalCount,
					data: originJson.data.pageData
				});
			}
	    },
	    "searching": false,
	    "ordering": false,
	    //"scrollX": true,																		// scroll x，close self-adaption
		//"dom": '<"top" t><"bottom" <"col-sm-3" i><"col-sm-3 right" l><"col-sm-6" p> >',		// dataTable "DOM layout"：https://datatables.club/example/diy.html
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
						"title": I18n.user_username,
	                	"data": 'username',
						"width":'30%'
					},
	                {
						"title": I18n.user_password,
						"data": 'password',
                        "width":'20%',
                        "render": function ( data, type, row ) {
                            return '*********';
                        }
					},
					{
						"title": '真实姓名',
						"data": 'realName',
						"width":'25%'
					},
					{
						"title": '启用状态',
						"data": 'status',
						"visible" : true,
						"width":'20%',
                        "render": function ( data, type, row ) {
							var result = "";
							$('#data_filter .status option').each(function(){
								if ( data.toString() === $(this).val() ) {
									result = $(this).text();
								}
							});
							return result;
                        }
					}
	            ],
		"language" : {
			"sProcessing" : I18n.dataTable_sProcessing ,
			"sLengthMenu" : I18n.dataTable_sLengthMenu ,
			"sZeroRecords" : I18n.dataTable_sZeroRecords ,
			"sInfo" : I18n.dataTable_sInfo ,
			"sInfoEmpty" : I18n.dataTable_sInfoEmpty ,
			"sInfoFiltered" : I18n.dataTable_sInfoFiltered ,
			"sInfoPostFix" : "",
			"sSearch" : I18n.dataTable_sSearch ,
			"sUrl" : "",
			"sEmptyTable" : I18n.dataTable_sEmptyTable ,
			"sLoadingRecords" : I18n.dataTable_sLoadingRecords ,
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : I18n.dataTable_sFirst ,
				"sPrevious" : I18n.dataTable_sPrevious ,
				"sNext" : I18n.dataTable_sNext ,
				"sLast" : I18n.dataTable_sLast
			},
			"oAria" : {
				"sSortAscending" : I18n.dataTable_sSortAscending ,
				"sSortDescending" : I18n.dataTable_sSortDescending
			}
		}
	});

    // table data
    var tableData = {};

	// search btn
	$('#data_filter .searchBtn').on('click', function(){
        mainDataTable.fnDraw();
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
				url : base_url + "/org/user/delete",
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
					// Handle error
					console.log("Error: " + error);
					layer.open({
						icon: '2',
						content: (I18n.system_opt_del + I18n.system_fail)
					});
				}
			});
		});
	});

	// ---------- ---------- ---------- add operation ---------- ---------- ----------
	// add validator method
	jQuery.validator.addMethod("usernameValid", function(value, element) {
		var length = value.length;
		var valid = /^[a-z][a-z0-9]*$/;
		return this.optional(element) || valid.test(value);
	}, I18n.user_username_valid );
	// add
	$("#data_operation .add").click(function(){
		$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	});
	var addModalValidate = $("#addModal .form").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {
            username : {
				required : true,
                rangelength:[4, 20],
				usernameValid: true
			},
            password : {
                required : true,
                rangelength:[4, 20]
            },
			realName : {
				required : true,
				rangelength:[2, 20]
			}
        }, 
        messages : {
            username : {
            	required : I18n.system_please_input + I18n.user_username,
                rangelength: I18n.system_lengh_limit + "[4-20]"
            },
            password : {
                required : I18n.system_please_input + I18n.user_password,
                rangelength: I18n.system_lengh_limit + "[4-20]"
            },
			realName : {
				required : I18n.system_please_input + I18n.user_real_name,
				rangelength: I18n.system_lengh_limit + "[2-20]"
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
				"username": $("#addModal .form input[name=username]").val(),
                "password": $("#addModal .form input[name=password]").val(),
                "status": $("#addModal .form select[name=status]").val(),
				"realName": $("#addModal .form input[name=realName]").val()
			};

			// post
        	$.post(base_url + "/org/user/add", paramData, function(data, status) {
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
		$("#updateModal .form input[name='id']").val( row.id );
		$("#updateModal .form input[name='username']").val( row.username );
		$("#updateModal .form input[name='password']").val( '' );
		$("#updateModal .form select[name='status']").val( row.status );
		$("#updateModal .form input[name='realName']").val( row.realName );

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
			realName : {
				required : true,
				rangelength:[2, 20]
			}
		},
		messages : {
			realName : {
				required : I18n.system_please_input + I18n.user_real_name,
				rangelength: I18n.system_lengh_limit + "[2-20]"
			}
		},
        submitHandler : function(form) {

			// request
            var paramData = {
                "id": $("#updateModal .form input[name=id]").val(),
                "username": $("#updateModal .form input[name=username]").val(),
                "password": $("#updateModal .form input[name=password]").val(),
				"status": $("#updateModal .form select[name=status]").val(),
				"realName": $("#updateModal .form input[name=realName]").val()
            };

            $.post(base_url + "/org/user/update", paramData, function(data, status) {
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
