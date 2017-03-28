<#-- @ftlvariable name="username" type="java.lang.String" -->
<#-- @ftlvariable name="testEntitys" type="java.util.List<com.yhjr.demo.domain.TestEntity>" -->
<html>
<head>
    <title>TestEntitys测试页面</title>
</head>
<body>
<table border=1px; >
    <tr>
		<th colspan=6>当前登录用户信息: ${username} !!!</th>
    </tr>
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>类型</th>
        <th>标记</th>
        <th>创建时间</th>
    </tr>

<#list testEntitys as entity>
    <tr>
        <td>${entity.id}</td>
        <td>${entity.userName}</td>
        <td>${entity.password}</td>
        <td>${entity.type}</td>
        <td>${entity.delFlag?c}</td>
        <td>${entity.createDatetime?string("yyyy-MM-dd")}</td>
    </tr>
</#list>
</table>

</body>
</html>