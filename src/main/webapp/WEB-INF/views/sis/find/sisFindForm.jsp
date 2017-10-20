<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
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
		<li><a href="${ctx}/find/sisFind/">主子表列表</a></li>
		<li class="active"><a href="${ctx}/find/sisFind/form?id=${sisFind.id}">主子表<shiro:hasPermission name="find:sisFind:edit">${not empty sisFind.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="find:sisFind:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sisFind" action="${ctx}/find/sisFind/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模块类型：</label>
			<div class="controls">
				<form:select path="modType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('mod_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资源类型：</label>
			<div class="controls">
				<form:select path="resType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('res_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标价：</label>
			<div class="controls">
				<form:input path="bidPrice" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现价：</label>
			<div class="controls">
				<form:input path="realPrice" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">封面地址：</label>
			<div class="controls">
				<form:hidden id="picUrl" path="picUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="picUrl" type="files" uploadPath="/find/sisFind" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频：</label>
			<div class="controls">
				<form:hidden id="vidUrl" path="vidUrl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="vidUrl" type="files" uploadPath="/find/sisFind" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否免费：</label>
			<div class="controls">
				<form:radiobuttons path="isFree" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否热门：</label>
			<div class="controls">
				<form:radiobuttons path="isHot" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">sis_find_add：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>资源标题</th>
								<th>资源类型</th>
								<th>资源地址</th>
								<th>资源封面地址</th>
								<th>内容</th>
								<th>状态</th>
								<th>排序</th>
								<th>备注信息</th>
								<shiro:hasPermission name="find:sisFind:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sisFindAddList">
						</tbody>
						<shiro:hasPermission name="find:sisFind:edit"><tfoot>
							<tr><td colspan="10"><a href="javascript:" onclick="addRow('#sisFindAddList', sisFindAddRowIdx, sisFindAddTpl);sisFindAddRowIdx = sisFindAddRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sisFindAddTpl">//<!--
						<tr id="sisFindAddList{{idx}}">
							<td class="hide">
								<input id="sisFindAddList{{idx}}_id" name="sisFindAddList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sisFindAddList{{idx}}_delFlag" name="sisFindAddList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="sisFindAddList{{idx}}_resTitle" name="sisFindAddList[{{idx}}].resTitle" type="text" value="{{row.resTitle}}" maxlength="50" class="input-small "/>
							</td>
							<td>
								<input id="sisFindAddList{{idx}}_resAddType" name="sisFindAddList[{{idx}}].resAddType" type="text" value="{{row.resAddType}}" maxlength="1" class="input-small "/>
							</td>
							<td>
								<input id="sisFindAddList{{idx}}_resUrl" name="sisFindAddList[{{idx}}].resUrl" type="hidden" value="{{row.resUrl}}" maxlength="255"/>
								<sys:ckfinder input="sisFindAddList{{idx}}_resUrl" type="files" uploadPath="/find/sisFind" selectMultiple="true"/>
							</td>
							<td>
								<input id="sisFindAddList{{idx}}_picUrl" name="sisFindAddList[{{idx}}].picUrl" type="hidden" value="{{row.picUrl}}" maxlength="255"/>
								<sys:ckfinder input="sisFindAddList{{idx}}_picUrl" type="files" uploadPath="/find/sisFind" selectMultiple="true"/>
							</td>
							<td>
								<textarea id="sisFindAddList{{idx}}_resContent" name="sisFindAddList[{{idx}}].resContent" rows="4" maxlength="64" class="input-small ">{{row.resContent}}</textarea>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('show_hide')}" var="dict" varStatus="dictStatus">
									<span><input id="sisFindAddList{{idx}}_resStatus${dictStatus.index}" name="sisFindAddList[{{idx}}].resStatus" type="radio" value="${dict.value}" data-value="{{row.resStatus}}"><label for="sisFindAddList{{idx}}_resStatus${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<input id="sisFindAddList{{idx}}_resSort" name="sisFindAddList[{{idx}}].resSort" type="text" value="{{row.resSort}}" maxlength="10" class="input-small  digits"/>
							</td>
							<td>
								<textarea id="sisFindAddList{{idx}}_remarks" name="sisFindAddList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="find:sisFind:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sisFindAddList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sisFindAddRowIdx = 0, sisFindAddTpl = $("#sisFindAddTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sisFind.sisFindAddList)};
							for (var i=0; i<data.length; i++){
								addRow('#sisFindAddList', sisFindAddRowIdx, sisFindAddTpl, data[i]);
								sisFindAddRowIdx = sisFindAddRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="find:sisFind:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>