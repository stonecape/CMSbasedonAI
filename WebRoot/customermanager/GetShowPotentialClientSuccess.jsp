<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<s:push value="#request.showpotentialclients">
	<table id="potentialclients" border="1">
		<tr align="center">
			<th>编号</th>
			<th>客户名称</th>
			<th>累计订单量</th>
			<th>累计金额</th>
			<th>客户满意度</th>
			<th>客户信用</th>
			<th>星级</th>
			<th>推荐星级</th>
			<th>趋势</th>
			<th>星级操作</th>
		</tr>
		<s:iterator id="potentialclient" status="c">
			<tr align="center">
				<td><s:property value="#c.count" /></td>
				<td><s:property value="companyName" /></td>
				<td><s:property value="accumulateOrderCount" /></td>
				<td><s:set var="accumulateSum" value="accumulateSum" /><fmt:formatNumber value="${accumulateSum}" pattern="#0.00" /></td>
				<td><s:set var="clientStatisfaction" value="clientStatisfaction" /><fmt:formatNumber value="${clientStatisfaction}" pattern="#0.00" /></td>
				<td><s:property value="clientCredit" /></td>
				<td id="clientStarLevel<s:property value="#c.count" />"><s:property value="clientStarLevel" /></td>
				<td id="recommendStarLevel<s:property value="#c.count" />"><s:property value="recommendStarLevel" /></td>
				<td id="starTrend<s:property value="#c.count" />">
					<font>
						<s:if test="starTrend == 'up'"><font color="#FF4500">up</font></s:if>
						<s:elseif test="starTrend == 'flat'"><font color="#FFFFFF">flat</font></s:elseif>
						<s:elseif test="starTrend == 'down'"><font color="#00FF7F">down</font></s:elseif>
					</font>
				</td>
				<td>
					<input type="button" onclick="modifystarlevel(<s:property value="cid" />, 1, <s:property value="#c.count" />)" value="1"/>
					<input type="button" onclick="modifystarlevel(<s:property value="cid" />, 3, <s:property value="#c.count" />)" value="3"/>
					<input type="button" onclick="modifystarlevel(<s:property value="cid" />, 5, <s:property value="#c.count" />)" value="5"/>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:push>
