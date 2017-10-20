<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>选题管理</title>
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
		<li class="active"><a href="${ctx}/topic/sisTopic/">选题列表</a></li>
		<shiro:hasPermission name="topic:sisTopic:edit"><li><a href="${ctx}/topic/sisTopic/form">选题添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sisTopic" action="${ctx}/topic/sisTopic/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关键字：</label>
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>发布者：</label>
				<form:input path="publisher" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>参与者：</label>
				<form:input path="sisUserId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>关键字</th>
				<th>发布者</th>
				<th>起始时间</th>
				<th>截止时间</th>
				<th>标价</th>
				<th>现价</th>
				<th>参与者</th>
				<th>状态</th>
				<th>排序</th>
				<th>推荐热度</th>
				<th>流程状态</th>
				<th>备注信息</th>
				<shiro:hasPermission name="topic:sisTopic:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sisTopic">
			<tr>
				<td><a href="${ctx}/topic/sisTopic/form?id=${sisTopic.id}">
					${sisTopic.keyword}
				</a></td>
				<td>
					${sisTopic.publisher}
				</td>
				<td>
					<fmt:formatDate value="${sisTopic.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sisTopic.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sisTopic.bidPrice}
				</td>
				<td>
					${sisTopic.realPrice}
				</td>
				<td>
					${sisTopic.sisUserId}
				</td>
				<td>
					${fns:getDictLabel(sisTopic.status, 'show_hide', '')}
				</td>
				<td>
					${sisTopic.sort}
				</td>
				<td>
					${sisTopic.hotSort}
				</td>
				<td>
					${fns:getDictLabel(sisTopic.flowStatus, 'flow_status', '')}
				</td>
				<td>
					${sisTopic.remarks}
				</td>
				<shiro:hasPermission name="topic:sisTopic:edit"><td>
    				<a href="${ctx}/topic/sisTopic/form?id=${sisTopic.id}">修改</a>
					<a href="${ctx}/topic/sisTopic/delete?id=${sisTopic.id}" onclick="return confirmx('确认要删除该选题吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>