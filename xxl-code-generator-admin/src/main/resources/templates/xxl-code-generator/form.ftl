<div style="width: 1000px; padding: 20px">

    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
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
                <td>${fieldItem.fieldName}</td>
                <td>暂时未选择</td>
                <td>${fieldItem.fieldComment}</td>
                <td><button type="button" onclick="openVerificationPage(
                            '${fieldItem.columnName}'
                            ,'${fieldItem.fieldName}'
                            ,'${fieldItem.fieldClass}'
                            ,'${fieldItem.fieldComment}')"
                            class="btn btn-default">添加验证信息
                    </button></td>
            </tr>

            </#list>
            </#if>
            </tbody>
        </table>
    </div>

</div>