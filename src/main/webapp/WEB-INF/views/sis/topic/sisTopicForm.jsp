<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选题管理</title>
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
		<li><a href="${ctx}/topic/sisTopic/">选题列表</a></li>
		<li class="active"><a href="${ctx}/topic/sisTopic/form?id=${sisTopic.id}">选题<shiro:hasPermission name="topic:sisTopic:edit">${not empty sisTopic.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="topic:sisTopic:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sisTopic" action="${ctx}/topic/sisTopic/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布者：</label>
			<div class="controls">
				<form:input path="publisher" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sisTopic.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">截止时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sisTopic.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
			<label class="control-label">参与者：</label>
			<div class="controls">
				<form:input path="sisUserId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐热度：</label>
			<div class="controls">
				<form:input path="hotSort" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程状态：</label>
			<div class="controls">
				<form:select path="flowStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('flow_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">选题&mdash;专业领域附表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>专业领域</th>
								<th>备注信息</th>
								<shiro:hasPermission name="topic:sisTopic:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sisTopicProList">
						</tbody>
						<shiro:hasPermission name="topic:sisTopic:edit"><tfoot>
							<tr><td colspan="4"><a href="javascript:" onclick="addRow('#sisTopicProList', sisTopicProRowIdx, sisTopicProTpl);sisTopicProRowIdx = sisTopicProRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sisTopicProTpl">//<!--
						<tr id="sisTopicProList{{idx}}">
							<td class="hide">
								<input id="sisTopicProList{{idx}}_id" name="sisTopicProList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sisTopicProList{{idx}}_delFlag" name="sisTopicProList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<select id="sisTopicProList{{idx}}_proField" name="sisTopicProList[{{idx}}].proField" data-value="{{row.proField}}" class="input-small required">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('pro_field')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<textarea id="sisTopicProList{{idx}}_remarks" name="sisTopicProList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="topic:sisTopic:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sisTopicProList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sisTopicProRowIdx = 0, sisTopicProTpl = $("#sisTopicProTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sisTopic.sisTopicProList)};
							for (var i=0; i<data.length; i++){
								addRow('#sisTopicProList', sisTopicProRowIdx, sisTopicProTpl, data[i]);
								sisTopicProRowIdx = sisTopicProRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">选题&mdash;资源附表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>资源地址</th>
								<th>流程状态</th>
								<th>状态</th>
								<th>备注信息</th>
								<shiro:hasPermission name="topic:sisTopic:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="sisTopicResourceList">
						</tbody>
						<shiro:hasPermission name="topic:sisTopic:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#sisTopicResourceList', sisTopicResourceRowIdx, sisTopicResourceTpl);sisTopicResourceRowIdx = sisTopicResourceRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="sisTopicResourceTpl">//<!--
						<tr id="sisTopicResourceList{{idx}}">
							<td class="hide">
								<input id="sisTopicResourceList{{idx}}_id" name="sisTopicResourceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="sisTopicResourceList{{idx}}_delFlag" name="sisTopicResourceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="sisTopicResourceList{{idx}}_resourceUrl" name="sisTopicResourceList[{{idx}}].resourceUrl" type="hidden" value="{{row.resourceUrl}}" maxlength="255"/>
								<sys:ckfinder input="sisTopicResourceList{{idx}}_resourceUrl" type="files" uploadPath="/topic/sisTopic" selectMultiple="true"/>
							</td>
							<td>
								<select id="sisTopicResourceList{{idx}}_flowStatus" name="sisTopicResourceList[{{idx}}].flowStatus" data-value="{{row.flowStatus}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('flow_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('show_hide')}" var="dict" varStatus="dictStatus">
									<span><input id="sisTopicResourceList{{idx}}_status${dictStatus.index}" name="sisTopicResourceList[{{idx}}].status" type="radio" value="${dict.value}" data-value="{{row.status}}"><label for="sisTopicResourceList{{idx}}_status${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<textarea id="sisTopicResourceList{{idx}}_remarks" name="sisTopicResourceList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="topic:sisTopic:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#sisTopicResourceList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var sisTopicResourceRowIdx = 0, sisTopicResourceTpl = $("#sisTopicResourceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(sisTopic.sisTopicResourceList)};
							for (var i=0; i<data.length; i++){
								addRow('#sisTopicResourceList', sisTopicResourceRowIdx, sisTopicResourceTpl, data[i]);
								sisTopicResourceRowIdx = sisTopicResourceRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="topic:sisTopic:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>