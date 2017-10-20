<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/find/sisFind/">主子表列表</a></li>
		<shiro:hasPermission name="find:sisFind:edit"><li><a href="${ctx}/find/sisFind/form">主子表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sisFind" action="${ctx}/find/sisFind/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>模块类型：</label>
				<form:select path="modType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('mod_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>资源类型：</label>
				<form:select path="resType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('res_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>是否免费：</label>
				<form:radiobuttons path="isFree" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>是否热门：</label>
				<form:radiobuttons path="isHot" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sisFind.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${sisFind.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>删除标记：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标题</th>
				<th>模块类型</th>
				<th>资源类型</th>
				<th>标价</th>
				<th>现价</th>
				<th>状态</th>
				<th>是否免费</th>
				<th>是否热门</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>删除标记</th>
				<th>排序</th>
				<shiro:hasPermission name="find:sisFind:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sisFind">
			<tr>
				<td><a href="${ctx}/find/sisFind/form?id=${sisFind.id}">
					${sisFind.title}
				</a></td>
				<td>
					${fns:getDictLabel(sisFind.modType, 'mod_type', '')}
				</td>
				<td>
					${fns:getDictLabel(sisFind.resType, 'res_type', '')}
				</td>
				<td>
					${sisFind.bidPrice}
				</td>
				<td>
					${sisFind.realPrice}
				</td>
				<td>
					${fns:getDictLabel(sisFind.status, 'show_hide', '')}
				</td>
				<td>
					${fns:getDictLabel(sisFind.isFree, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(sisFind.isHot, 'yes_no', '')}
				</td>
				<td>
					${sisFind.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${sisFind.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(sisFind.delFlag, 'del_flag', '')}
				</td>
				<td>
					${sisFind.sort}
				</td>
				<shiro:hasPermission name="find:sisFind:edit"><td>
    				<a href="${ctx}/find/sisFind/form?id=${sisFind.id}">修改</a>
					<a href="${ctx}/find/sisFind/delete?id=${sisFind.id}" onclick="return confirmx('确认要删除该主子表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>