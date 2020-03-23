$(function () {

    /**
     * 初始化 table sql
     */
    var tableSqlIDE;

    var javaCodeOptions = {
        lineNumbers: true,
        matchBrackets: true,
        mode: "text/x-java",
        lineWrapping: true,
        readOnly: true,
        foldGutter: true,
        gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
    };

    var textXmlCodeOptions = {
        lineNumbers: true,
        matchBrackets: true,
        mode: "text/html",
        lineWrapping: true,
        readOnly: true
    };

    function initTableSql() {
        tableSqlIDE = CodeMirror.fromTextArea(document.getElementById("tableSql"), {
            lineNumbers: true,
            matchBrackets: true,
            mode: "text/x-sql",
            lineWrapping: false,
            readOnly: false,
            foldGutter: true,
            gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
        });
        tableSqlIDE.setSize('auto', 'auto');
    }

    initTableSql();

    /**
     * 初始化 code area
     */

    var controller_ide;
    var service_ide;
    var service_impl_ide;
    var dao_ide;
    var mybatis_ide;
    var model_ide;

    function initCodeArea() {


        // controller_ide
        controller_ide = CodeMirror.fromTextArea(document.getElementById("controller_ide"), javaCodeOptions);
        controller_ide.setSize('auto', 'auto');

        // service_ide
        service_ide = CodeMirror.fromTextArea(document.getElementById("service_ide"), javaCodeOptions);
        service_ide.setSize('auto', 'auto');

        // service_impl_ide
        service_impl_ide = CodeMirror.fromTextArea(document.getElementById("service_impl_ide"), javaCodeOptions);
        service_impl_ide.setSize('auto', 'auto');

        // dao_ide
        dao_ide = CodeMirror.fromTextArea(document.getElementById("dao_ide"), javaCodeOptions);
        dao_ide.setSize('auto', 'auto');

        // mybatis_ide
        mybatis_ide = CodeMirror.fromTextArea(document.getElementById("mybatis_ide"), textXmlCodeOptions);
        mybatis_ide.setSize('auto', 'auto');

        // model_ide
        model_ide = CodeMirror.fromTextArea(document.getElementById("model_ide"), javaCodeOptions);
        model_ide.setSize('auto', 'auto');
    }

    initCodeArea();


    $("#getParseTableSql").click(function () {
        var tableSql = tableSqlIDE.getValue();
        var packageName = document.getElementById("packageName").value;


        $.ajax({
            type: 'POST',
            url: base_url + "/getParseTableSql",
            data: {
                "tableSql": tableSql,
                "packageName": packageName,
            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    console.log(data);
                    layer.open({
                        type: 1,
                        title: "填写字段验证方式",
                        area: '1000px',
                        content: data.data.optionSelect,
                        end: function (layero, index) {

                        }
                    });
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg || '代码生成失败')
                    });
                }
            }
        });
    });

    /**
     * 生成代码
     */
    $('#codeGenerate').click(function () {

        var tableSql = tableSqlIDE.getValue();
        var packageName = document.getElementById("packageName").value;


        $.ajax({
            type: 'POST',
            url: base_url + "/codeGenerate",
            data: {
                "tableSql": tableSql,
                "packageName": packageName,

            },
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    layer.open({
                        icon: '1',
                        content: "代码生成成功",
                        end: function (layero, index) {

                            controller_ide.setValue(data.data.controller_code);
                            controller_ide.setSize('auto', 'auto');

                            service_ide.setValue(data.data.service_code);
                            service_ide.setSize('auto', 'auto');

                            service_impl_ide.setValue(data.data.service_impl_code);
                            service_impl_ide.setSize('auto', 'auto');

                            dao_ide.setValue(data.data.dao_code);
                            dao_ide.setSize('auto', 'auto');

                            mybatis_ide.setValue(data.data.mybatis_code);
                            mybatis_ide.setSize('auto', 'auto');

                            model_ide.setValue(data.data.model_code);
                            model_ide.setSize('auto', 'auto');

                        }
                    });
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg || '代码生成失败')
                    });
                }
            }
        });

    });




});