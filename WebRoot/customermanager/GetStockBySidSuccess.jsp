<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<s:push value="#request.stockBySid">
	<table id="stocks" border="1">
		<tr align="center">
			<th>产品编号</th>
			<th>产品规格</th>
			<th>可用余量</th>
			<th>位置</th>
			<th>负责人</th>
			<th>状态</th>
		</tr>
		<tr align="center">
			<td><s:property value="sid"/></td>
			<td><s:property value="profile"/></td>
			<td><s:property value="remainder"/></td>
			<td><s:property value="location"/></td>
			<td><s:property value="director"/></td>
			<td>
				<s:if test='status == 0' ><font color="red">停售</font></s:if>
				<s:else>可售</s:else>
			</td>
		</tr>
	</table>
</s:push>
