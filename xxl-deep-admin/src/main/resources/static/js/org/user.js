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
	var userListTable = $("#data_list").dataTable({
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
                obj.role = $('#data_filter .role').val();
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
						"width":'5%',
						"render": function ( data, type, row ) {
							tableData['key'+row.id] = row;
							return '<input align="center" type="checkbox" class="checkItem" data-id="'+ row.id +'"  >';
						}
					},
	                {
						"title":"User ID",
						"data": 'id',
						"visible" : false,
						"width":'10%'
					},
	                {
						"title": I18n.user_username,
	                	"data": 'username',
						"visible" : true,
						"width":'20%'
					},
	                {
						"title": I18n.user_password,
						"data": 'password',
						"visible" : true,
                        "width":'20%',
                        "render": function ( data, type, row ) {
                            return '*********';
                        }
					},
					{
						"title": '真实姓名',
						"data": 'realName',
						"visible" : true,
						"width":'20%'
					},
					{
						"title": '有效状态',
						"data": 'status',
						"visible" : true,
						"width":'10%',
                        "render": function ( data, type, row ) {
                            if (data == 1) {
                                return I18n.user_role_admin
                            } else {
                                return I18n.user_role_normal
                            }
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
        userListTable.fnDraw();
	});

	// ---------- ---------- ---------- table operation ---------- ---------- ----------
	// delete
	$("#data_list").on('click', '.delete',function() {
		var id = $(this).parent('p').attr("id");

		layer.confirm( I18n.system_ok + I18n.system_opt_del + '?', {
			icon: 3,
			title: I18n.system_tips ,
            btn: [ I18n.system_ok, I18n.system_cancel ]
		}, function(index){
			layer.close(index);

			$.ajax({
				type : 'POST',
				url : base_url + "/org/user/remove",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(data){
					if (data.code == 200) {
                        layer.msg( I18n.system_success );
						userListTable.fnDraw(false);
					} else {
                        layer.msg( data.msg || I18n.system_opt_del + I18n.system_fail );
					}
				}
			});
		});
	});

	// add role
    $("#addModal .form input[name=role]").change(function () {
		var role = $(this).val();
		if (role == 1) {
            $("#addModal .form input[name=permission]").parents('.form-group').hide();
		} else {
            $("#addModal .form input[name=permission]").parents('.form-group').show();
		}
        $("#addModal .form input[name='permission']").prop("checked",false);
    });

    jQuery.validator.addMethod("myValid01", function(value, element) {
        var length = value.length;
        var valid = /^[a-z][a-z0-9]*$/;
        return this.optional(element) || valid.test(value);
    }, I18n.user_username_valid );

	// add
	$(".add").click(function(){
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
                myValid01: true
			},
            password : {
                required : true,
                rangelength:[4, 20]
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

            var permissionArr = [];
            $("#addModal .form input[name=permission]:checked").each(function(){
                permissionArr.push($(this).val());
            });

			var paramData = {
				"username": $("#addModal .form input[name=username]").val(),
                "password": $("#addModal .form input[name=password]").val(),
                "role": $("#addModal .form input[name=role]:checked").val(),
                "permission": permissionArr.join(',')
			};

        	$.post(base_url + "/org/user/add", paramData, function(data, status) {
    			if (data.code == "200") {
					$('#addModal').modal('hide');

                    layer.msg( I18n.system_opt_add + I18n.system_success );
                    userListTable.fnDraw();
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
		$("#addModal .form")[0].reset();
		addModalValidate.resetForm();
		$("#addModal .form .form-group").removeClass("has-error");
		$(".remote_panel").show();	// remote

        $("#addModal .form input[name=permission]").parents('.form-group').show();
	});

    // update role
    $("#updateModal .form input[name=role]").change(function () {
        var role = $(this).val();
        if (role == 1) {
            $("#updateModal .form input[name=permission]").parents('.form-group').hide();
        } else {
            $("#updateModal .form input[name=permission]").parents('.form-group').show();
        }
        $("#updateModal .form input[name='permission']").prop("checked",false);
    });

	// update
	$("#data_list").on('click', '.update',function() {

        var id = $(this).parent('p').attr("id");
        var row = tableData['key'+id];

		// base data
		$("#updateModal .form input[name='id']").val( row.id );
		$("#updateModal .form input[name='username']").val( row.username );
		$("#updateModal .form input[name='password']").val( '' );
		$("#updateModal .form input[name='role'][value='"+ row.role +"']").click();
        var permissionArr = [];
        if (row.permission) {
            permissionArr = row.permission.split(",");
		}
        $("#updateModal .form input[name='permission']").each(function () {
            if($.inArray($(this).val(), permissionArr) > -1) {
                $(this).prop("checked",true);
            } else {
                $(this).prop("checked",false);
            }
        });

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
        submitHandler : function(form) {

            var permissionArr =[];
            $("#updateModal .form input[name=permission]:checked").each(function(){
                permissionArr.push($(this).val());
            });

            var paramData = {
                "id": $("#updateModal .form input[name=id]").val(),
                "username": $("#updateModal .form input[name=username]").val(),
                "password": $("#updateModal .form input[name=password]").val(),
                "role": $("#updateModal .form input[name=role]:checked").val(),
                "permission": permissionArr.join(',')
            };

            $.post(base_url + "/org/user/update", paramData, function(data, status) {
                if (data.code == "200") {
                    $('#updateModal').modal('hide');

                    layer.msg( I18n.system_opt_edit + I18n.system_success );
                    userListTable.fnDraw();
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
        $("#updateModal .form")[0].reset();
        updateModalValidate.resetForm();
        $("#updateModal .form .form-group").removeClass("has-error");
        $(".remote_panel").show();	// remote

        $("#updateModal .form input[name=permission]").parents('.form-group').show();
	});

});
