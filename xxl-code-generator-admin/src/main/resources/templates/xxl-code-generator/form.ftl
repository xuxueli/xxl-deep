<div style="width: 1000px; padding: 20px">
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="100px">
                <col width="150px">
                <col width="510px">
                <col width="150px">
                <col width="150px">
            </colgroup>
            <thead>
            <tr>
                <th>字段生成from表单</th>
                <th>字段名称</th>
                <th>验证信息</th>
                <th>注释信息</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
                <#list classInfo.fieldList as fieldItem >
                    <tr>
                        <td>
                            <div class="checkbox">
                                <label>
                                    <input onclick="toggleHtmlConfig(this , '${fieldItem.fieldName}_components')"
                                           name="${fieldItem.fieldName}_checkbox" id="${fieldItem.fieldName}_checkbox"
                                           type="checkbox">&nbsp;&nbsp;&nbsp;&nbsp;<span><strong>是</strong></span>
                                </label>
                            </div>
                        </td>
                        <td>${fieldItem.fieldName}</td>
                        <td>
                            <div id="${fieldItem.fieldName}_check"></div>
                        </td>
                        <td>${fieldItem.fieldComment}</td>
                        <td>
                            <button type="button" onclick="openVerificationPage(
                                    '${fieldItem.columnName}'
                                    ,'${fieldItem.fieldName}'
                                    ,'${fieldItem.fieldClass}'
                                    ,'${fieldItem.fieldComment}')"
                                    class="btn btn-default">添加验证
                            </button>
                        </td>
                    </tr>
                    <tr id="${fieldItem.fieldName}_components" style="display: none">
                        <td colspan="5">
                            <div class="btn-group" id="color" data-toggle="buttons">
                                <label class="btn btn-default active">
                                    <input type="radio" class="toggle" value="1"> text 文本框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="2"> checkbox 复选框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="3"> radio 单选框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="4"> select 选择框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="5"> 全时间选择框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="6"> 日期选择框
                                </label>
                                <label class="btn btn-default">
                                    <input type="radio" class="toggle" value="7"> 隐藏域
                                </label>
                            </div>
                        </td>

                    </tr>

                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</div>
<style>
    .btn.active, .btn.active.focus, .btn:active {
        background-color: #00a4ff;
    }


</style>
<script>
    function toggleHtmlConfig(checkbox, target) {
        if (checkbox.checked == true) {
            $("#" + target).show();
        } else {
            $("#" + target).hide();
        }
    }
</script>