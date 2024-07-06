$(function () {

    /**
     * init table sql ide
     */
    var tableSqlIDE;
    function initTableSql() {
        tableSqlIDE = CodeMirror.fromTextArea(document.getElementById("tableSql"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-sql",
            lineWrapping:false,
            readOnly:false,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        tableSqlIDE.setSize('auto','auto');
    }
    initTableSql();

    /**
     * init code area
     */

    var controller_ide;
    var service_ide;
    var service_impl_ide;
    var dao_ide;
    var mybatis_ide;
    var model_ide;
    function initCodeArea(){

        // controller_ide
        controller_ide = CodeMirror.fromTextArea(document.getElementById("controller_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            lineWrapping:true,
            readOnly:true,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        controller_ide.setSize('auto','auto');

        // service_ide
        service_ide = CodeMirror.fromTextArea(document.getElementById("service_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            lineWrapping:true,
            readOnly:true,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        service_ide.setSize('auto','auto');

        // service_impl_ide
        service_impl_ide = CodeMirror.fromTextArea(document.getElementById("service_impl_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            lineWrapping:true,
            readOnly:true,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        service_impl_ide.setSize('auto','auto');

        // dao_ide
        dao_ide = CodeMirror.fromTextArea(document.getElementById("dao_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            lineWrapping:true,
            readOnly:true,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        dao_ide.setSize('auto','auto');

        // mybatis_ide
        mybatis_ide = CodeMirror.fromTextArea(document.getElementById("mybatis_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/html",
            lineWrapping:true,
            readOnly:true
        });
        mybatis_ide.setSize('auto','auto');

        // model_ide
        model_ide = CodeMirror.fromTextArea(document.getElementById("model_ide"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-java",
            lineWrapping:true,
            readOnly:true,
            foldGutter: true,
            gutters:["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        model_ide.setSize('auto','auto');
    }

    initCodeArea();

    /**
     * gen Code
     */
    $('#codeGenerate').click(function () {

        var tableSql = tableSqlIDE.getValue();

        $.ajax({
            type : 'POST',
            url : base_url + "/tool/codegen/genCode",
            data : {
                "tableSql" : tableSql
            },
            dataType : "json",
            success : function(data){
                if (data.code == 200) {

                    // set value
                    controller_ide.setValue(data.data.controller_code);
                    controller_ide.setSize('auto','auto');

                    service_ide.setValue(data.data.service_code);
                    service_ide.setSize('auto','auto');

                    service_impl_ide.setValue(data.data.service_impl_code);
                    service_impl_ide.setSize('auto','auto');

                    dao_ide.setValue(data.data.dao_code);
                    dao_ide.setSize('auto','auto');

                    mybatis_ide.setValue(data.data.mybatis_code);
                    mybatis_ide.setSize('auto','auto');

                    model_ide.setValue(data.data.model_code);
                    model_ide.setSize('auto','auto');

                    // refresh
                    controller_ide.refresh();
                    service_ide.refresh();
                    service_impl_ide.refresh();
                    dao_ide.refresh();
                    mybatis_ide.refresh();
                    model_ide.refresh();

                    // msg
                    layer.open({
                        icon: '1',
                        content: I18n.codegen_name+I18n.system_success ,
                        end: function(layero, index){
                            //
                        }
                    });
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg|| I18n.codegen_name+I18n.system_fail )
                    });
                }
            },
            error: function(xhr, status, error) {
                // Handle error
                console.log("Error: " + error);
                layer.open({
                    icon: '2',
                    content: (I18n.codegen_name+I18n.system_fail)
                });
            }
        });

    });

});