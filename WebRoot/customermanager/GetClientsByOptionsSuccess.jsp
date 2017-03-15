<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<s:push value="#request.clientsByOptions">
	<table id="clients" border="1">
		<tr align="center">
			<th>编号</th>
			<th>公司/客户名称</th>
			<th>通讯地址</th>
			<th>信用</th>
			<th>联系电话</th>
			<th>E-mail</th>
			<th>联系人</th>
			<th>星级</th>
			<th>操作</th>
		</tr>
		<s:iterator id="client" status="c">
			<tr align="center">
				<td><s:property value="#c.count" /></td>
				<td><s:property value="companyName" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="credit" /></td>
				<td><s:property value="telePhone" /></td>
				<td><s:property value="email" /></td>
				<td><s:property value="linkName" /></td>
				<td><s:property value="starLevel" /></td>
				<td><a href="customermanager/ModifyClientInfoAction?cid=<s:property value="cid" />"><input type="button" value="修改"/></a></td>
			</tr>
		</s:iterator>
	</table>
</s:push>