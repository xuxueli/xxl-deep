<div style="width: 1000px;padding-left: 20px; padding-right: 20px ">
    <div class="row">
        <button type="button" id="${fieldName}_clean_all" class="btn btn-primary">取消全选</button>
    </div>
    <div class="row" style="margin-top: 20px;margin-bottom: 20px">
        <#if rule?exists && rule?size gt 0>
            <#list rule as ruleItem >
                <div class="checkbox-inline">
                    <label>
                        <input onclick="ruleCheckBoxClick(this , '${fieldName}_${ruleItem.rule}_tr_id')"
                               name="${fieldName}_checkbox" id="${fieldName}_checkbox"
                               type="checkbox">&nbsp;&nbsp;&nbsp;&nbsp;<span><strong>${ruleItem.name}验证</strong></span>
                    </label>
                </div>
            </#list>
        </#if>
    </div>
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="200px">
                <col width="200px">
                <col width="540px">
            </colgroup>
            <thead>
            <tr>
                <th style="padding: 10px">验证方式</th>
                <th style="padding: 10px">值</th>
                <th style="padding: 10px">错误提示</th>
            </tr>
            </thead>
            <tbody>
            <#if rule?exists && rule?size gt 0>
                <#list rule as ruleItem >
                    <tr id="${fieldName}_${ruleItem.rule}_tr_id" style="display: none">
                        <td style="padding: 10px">
                            ${fieldName}
                        </td>
                        <#if ruleItem.rule == "min">
                            <td style="padding: 10px"><input
                                        class="form-control"
                                        id="${fieldName}_${ruleItem.rule}_text_id"
                                        type="number"
                                        name="${fieldName}_${ruleItem.rule}_text_name"
                                        placeholder="请输入最小值"
                                /></td>
                        <#elseif ruleItem.rule == "max">
                            <td style="padding: 10px"><input
                                        class="form-control"
                                        id="${fieldName}_${ruleItem.rule}_id"
                                        name="${fieldName}_${ruleItem.rule}_name"
                                        type="number"
                                        placeholder="请输入最大值"

                                /></td>
                        <#else>
                            <td style="padding: 10px"></td>
                        </#if>
                        <td style="padding: 10px"><input type="text" class="form-control"
                                                         id="${fieldName}_${ruleItem.rule}_message"
                                                         name="${fieldName}_${ruleItem.rule}_message"
                                                         value="<#if ruleItem.type=='normal'>${fieldComment}</#if>${ruleItem.errorInfo}"
                            /></td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</div>
<script>
    $("#${fieldName}_clean_all").click(function () {
        $("[name='${fieldName}_checkbox']").removeAttr("checked");
    });

    function ruleCheckBoxClick(checkbox, target) {
        if (checkbox.checked == true) {
            $("#" + target ).show();
        } else {
            $("#" + target ).hide();
        }
    }
</script>
