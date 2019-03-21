<div style="width: 1000px;">
    <div style="margin-top: 20px;margin-left: 20px">
        <button type="button" id="${fieldName}_clean_all" class="btn btn-primary">取消全选</button>
    </div>
    <div id="verificationCheckBox" style="margin: 20px">
        <#if rule?exists && rule?size gt 0>
            <#list rule as ruleItem >
                <div class="checkbox-inline " style="width: 192px;margin-left: 0">
                    <label>
                        <input onclick="ruleCheckBoxClick(this , '${fieldName}_${ruleItem.rule}_tr_id' , '${ruleItem.rule}')"
                               name="${fieldName}_checkbox" id="${fieldName}_checkbox" rule="${ruleItem.rule}"
                               type="checkbox">&nbsp;&nbsp;&nbsp;&nbsp;<span><strong>${ruleItem.name}验证</strong></span>
                    </label>
                </div>
            </#list>
        </#if>
    </div>
    <div class="layui-form" style="margin-left: 20px; margin-right: 20px">
        <table class="layui-table">
            <colgroup>
                <col width="200px">
                <col width="200px">
                <col width="560px">
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
                            <span><strong>${ruleItem.name}验证</strong></span>
                        </td>
                        <#if ruleItem.rule == "min">
                            <td style="padding: 10px"><input
                                        class="form-control"
                                        id="${fieldName}_${ruleItem.rule}_input_id"
                                        type="number"
                                        name="${fieldName}_${ruleItem.rule}_input_name"
                                        placeholder="请输入最小值"
                                /></td>
                        <#elseif ruleItem.rule == "max">
                            <td style="padding: 10px"><input
                                        class="form-control"
                                        id="${fieldName}_${ruleItem.rule}_input_id"
                                        name="${fieldName}_${ruleItem.rule}_input_name"
                                        type="number"
                                        placeholder="请输入最大值"

                                /></td>
                        <#else>
                            <td style="padding: 10px"></td>
                        </#if>
                        <td style="padding: 10px"><input type="text" class="form-control"
                                                         id="${fieldName}_${ruleItem.rule}_errorInfo"
                                                         name="${fieldName}_${ruleItem.rule}_errorInfo"
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
    <#if rule?exists && rule?size gt 0>
    <#list rule as ruleItem >
    <#if ruleItem.rule == "min">
    var minErrorInfo = "${ruleItem.errorInfo}";
    <#elseif ruleItem.rule == "max">
    var maxErrorInfo = "${ruleItem.errorInfo}";
    <#else>
    </#if>
    </#list>
    </#if>

    $("#${fieldName}_min_input_id").change(function () {
        $("#${fieldName}_min_errorInfo").val(minErrorInfo + $(this).val() + "位")
    });
    $("#${fieldName}_max_input_id").change(function () {
        $("#${fieldName}_max_errorInfo").val(maxErrorInfo + $(this).val() + "位")
    });

    $("#${fieldName}_clean_all").click(function () {
        $("input:checkbox[name='${fieldName}_checkbox']:checked").each(function () {
            $(this).removeAttr("checked");
            var rule = $(this).attr("rule");
            $("#${fieldName}_" + rule + "_tr_id").hide();
        });
    });

    function ruleCheckBoxClick(checkbox, target, rule) {
        if (checkbox.checked == true) {
            $("#" + target).show();
        } else {
            $("#" + target).hide();
        }
    }

</script>
