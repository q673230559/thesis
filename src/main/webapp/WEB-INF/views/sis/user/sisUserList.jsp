<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户功能管理</title>
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
		<li class="active"><a href="${ctx}/user/sisUser/">用户功能列表</a></li>
		<shiro:hasPermission name="user:sisUser:edit"><li><a href="${ctx}/user/sisUser/form">用户功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sisUser" action="${ctx}/user/sisUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>手机号码：</label>
				<form:input path="phonenumber" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>最高学位：</label>
				<form:select path="degree" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('highest_degree')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>用户昵称</th>
				<th>手机号码</th>
				<th>邮箱</th>
				<th>最高学位</th>
				<th>余额</th>
				<th>可提现余额</th>
				<th>状态</th>
				<th>经验值</th>
				<th>创建时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="user:sisUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sisUser">
			<tr>
				<td><a href="${ctx}/user/sisUser/form?id=${sisUser.id}">
					${sisUser.nickname}
				</a></td>
				<td>
					${sisUser.phonenumber}
				</td>
				<td>
					${sisUser.email}
				</td>
				<td>
					${fns:getDictLabel(sisUser.degree, 'highest_degree', '')}
				</td>
				<td>
					${sisUser.balance}
				</td>
				<td>
					${sisUser.withdrawBalance}
				</td>
				<td>
					${fns:getDictLabel(sisUser.status, 'show_hide', '')}
				</td>
				<td>
					${sisUser.exp}
				</td>
				<td>
					<fmt:formatDate value="${sisUser.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sisUser.remarks}
				</td>
				<shiro:hasPermission name="user:sisUser:edit"><td>
    				<a href="${ctx}/user/sisUser/form?id=${sisUser.id}">修改</a>
					<a href="${ctx}/user/sisUser/delete?id=${sisUser.id}" onclick="return confirmx('确认要删除该用户功能吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>