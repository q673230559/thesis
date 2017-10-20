<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户功能管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/user/sisUser/">用户功能列表</a></li>
		<li class="active"><a href="${ctx}/user/sisUser/form?id=${sisUser.id}">用户功能<shiro:hasPermission name="user:sisUser:edit">${not empty sisUser.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="user:sisUser:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sisUser" action="${ctx}/user/sisUser/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">用户昵称：</label>
			<div class="controls">
				<form:input path="nickname" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号码：</label>
			<div class="controls">
				<form:input path="phonenumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱：</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码：</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最高学位：</label>
			<div class="controls">
				<form:select path="degree" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('highest_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">余额：</label>
			<div class="controls">
				<form:input path="balance" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可提现余额：</label>
			<div class="controls">
				<form:input path="withdrawBalance" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">头像照片：</label>
			<div class="controls">
				<form:hidden id="headPic" path="headPic" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="headPic" type="files" uploadPath="/user/sisUser" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经验值：</label>
			<div class="controls">
				<form:input path="exp" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">用户&mdash;科研能力附表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>科研能力</th>
								<shiro:hasPermission name="user:sisUser:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sisUserPowList">
						</tbody>
						<shiro:hasPermission name="user:sisUser:edit"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#sisUserPowList', sisUserPowRowIdx, sisUserPowTpl);sisUserPowRowIdx = sisUserPowRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sisUserPowTpl">//<!--
						<tr id="sisUserPowList{{idx}}">
							<td class="hide">
								<input id="sisUserPowList{{idx}}_id" name="sisUserPowList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sisUserPowList{{idx}}_delFlag" name="sisUserPowList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="sisUserPowList{{idx}}_power" name="sisUserPowList[{{idx}}].power" data-value="{{row.power}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('power')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="user:sisUser:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sisUserPowList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sisUserPowRowIdx = 0, sisUserPowTpl = $("#sisUserPowTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sisUser.sisUserPowList)};
							for (var i=0; i<data.length; i++){
								addRow('#sisUserPowList', sisUserPowRowIdx, sisUserPowTpl, data[i]);
								sisUserPowRowIdx = sisUserPowRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">用户&mdash;专业领域附表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>专业领域</th>
								<shiro:hasPermission name="user:sisUser:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sisUserProList">
						</tbody>
						<shiro:hasPermission name="user:sisUser:edit"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#sisUserProList', sisUserProRowIdx, sisUserProTpl);sisUserProRowIdx = sisUserProRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sisUserProTpl">//<!--
						<tr id="sisUserProList{{idx}}">
							<td class="hide">
								<input id="sisUserProList{{idx}}_id" name="sisUserProList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sisUserProList{{idx}}_delFlag" name="sisUserProList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="sisUserProList{{idx}}_proField" name="sisUserProList[{{idx}}].proField" data-value="{{row.proField}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('pro_field')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="user:sisUser:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sisUserProList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sisUserProRowIdx = 0, sisUserProTpl = $("#sisUserProTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sisUser.sisUserProList)};
							for (var i=0; i<data.length; i++){
								addRow('#sisUserProList', sisUserProRowIdx, sisUserProTpl, data[i]);
								sisUserProRowIdx = sisUserProRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="user:sisUser:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>