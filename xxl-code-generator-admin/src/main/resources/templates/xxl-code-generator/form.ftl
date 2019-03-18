<form class="form-horizontal" role="form">
    <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
        <#list classInfo.fieldList as fieldItem >
            <div class="form-group">
                <div class="col-sm-1">${fieldItem.fieldName}</div>
                <div class="col-sm-11">
                    <button type="button" onclick="openVerificationPage('${fieldItem.fieldName}')" class="btn btn-default">添加</button>
                </div>
            </div>
        </#list>
    </#if>
    <button type="submit" class="btn btn-default">Submit</button>
</form>