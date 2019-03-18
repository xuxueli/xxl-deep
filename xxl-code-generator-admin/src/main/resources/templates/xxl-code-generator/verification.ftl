<div style="width: 1000px; padding: 20px">
    <form role="form" class="form-horizontal">
        <div class="form-group">

            <div class="col-md-6">
                <div class="row">
                    <label class="control-label col-md-3">
                        <input name="${field}_checkbox" type="checkbox"/><span><strong>不为空</strong></span>
                    </label>
                    <div class="col-md-9 ">
                        <div class="row">
                            <label class="control-label col-md-4">错误文本</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="${field}_empty_message"
                                       name="${field}_empty_message"
                                       value="${field}必须填写"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




        </div>
    </form>
</div>


<#--<form class="form" role="form">-->
<#--<div class="form-group">-->
<#--<div class="col-sm-12">${field}</div>-->


<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_empty" name="${field}_empty"-->
<#--type="checkbox">不为空-->
<#--</label>-->
<#--</div>-->
<#--<input type="number" name="${field}_empty_text" class="form-control"-->
<#--id="${field}_empty_text" value="${field}必须填写">-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_mobile" name="${field}_mobile"-->
<#--type="checkbox">电话-->
<#--</label>-->
<#--</div>-->
<#--<input type="number" name="${field}_mobile_text" class="form-control"-->
<#--id="${field}_mobile_text" value="电话号码格式不正确">-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_email" name="${field}_email"-->
<#--type="checkbox">邮箱-->
<#--</label>-->
<#--</div>-->
<#--<input type="number" name="${field}_email_text" class="form-control"-->
<#--id="${field}_email_text" value="邮箱地址格式不正确">-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_chinese" name="${field}_chinese"-->
<#--type="checkbox">汉字-->
<#--</label>-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_idcard" name="${field}_idcard"-->
<#--type="checkbox">身份证-->
<#--</label>-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_url" name="${field}_url" type="checkbox">URL-->
<#--</label>-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<div class="checkbox">-->
<#--<label>-->
<#--<input id="${field}_ip" name="${field}_ip" type="checkbox">IP-->
<#--</label>-->
<#--</div>-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<input type="number" name="${field}_min" class="form-control"-->
<#--id="${field}_min" placeholder="minLength">-->
<#--</div>-->
<#--<div class="col-sm-12">-->
<#--<input type="number" name="${field}_max" class="form-control"-->
<#--id="${field}_max" placeholder="maxLength">-->
<#--</div>-->

<#--<div class="col-sm-12">-->
<#--<input type="text" name="${field}_regex" class="form-control"-->
<#--id="${field}_regex" placeholder="自定义正则表达式">-->
<#--</div>-->
<#--</div>-->

<#--</form>-->
