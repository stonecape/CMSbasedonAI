<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<s:push value="#request.showOrderList">
	<table id="orders" border="1">
		<tr align="center">
			<th>订单号</th>
			<th>客户名称</th>
			<th>产品规格</th>
			<th>单价</th>
			<th>订货数量</th>
			<th>折扣</th>
			<th>金额</th>
			<th>下单时间</th>
		</tr>
		<s:iterator id="showorder">
			<tr align="center">
				<td><s:property value="singleShowOrder.oid"/></td>
				<td><s:property value="singleShowOrder.companyName"/></td>
				<td><s:property value="singleShowOrder.profile"/></td>
				<td><s:property value="singleShowOrder.unitPrice"/></td>
				<td><s:property value="singleShowOrder.amount"/></td>
				<td><s:property value="singleShowOrder.discount"/></td>
				<td>
					<s:set var="sum" value="singleShowOrder.amount * singleShowOrder.unitPrice * singleShowOrder.discount"></s:set>
					<fmt:formatNumber value="${sum}" pattern="#0.00" />
				</td>
				<td><s:date name="singleShowOrder.commitDate" format="yyyy-MM-dd"/></td>
			</tr>
		</s:iterator>
	</table>
</s:push>
