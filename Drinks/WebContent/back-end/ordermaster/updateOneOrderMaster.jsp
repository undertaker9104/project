<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.ordermaster.model.OrderMasterService"%>
<%@ page import="com.ordermaster.model.OrderMasterVo"%>
<%
 	String ord_id_update = (String) request.getAttribute("ord_id_update");
    OrderMasterService orderMasterSvc = new OrderMasterService(); 
    OrderMasterVo  masterVo  =  orderMasterSvc.getOne(ord_id_update);
	request.setAttribute("masterVo", masterVo);
%>
<html>
<head>
<title>listOneOrderMaster</title>
<style type="text/css">
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>

</head>
<body>
    <div class="page-header text-center">
		<div class="h1">訂單主檔</div>
    </div>
    <form>
	<table class="myTable_update table table-hover table-bordered table-striped table-condensed">
				<thead>
					<tr bgcolor=#DDDDDD>
						<th>訂單編號</th>
						<th>揪團狀態</th>
						<th>外送地址</th>
						<th>運送選擇</th>
						<th>訂單狀態</th>
					</tr>
				</thead>
				<tbody>
				 	<tr>
					    <td> ${masterVo.ord_id} </td> 
						<td> ${(0 == masterVo.grou) ?'無':'揪團確認'} </td>
						<td> ${(null == masterVo.oute_add) ?'無':masterVo.oute_add} </td>
						<td> ${(0 == masterVo.ship_option) ?'外帶確認':'外送確認'} </td>
						<td>
							<select size="1" name="masterstatus" class="form-control">
								<option value="0" ${(0 == masterVo.ord_status) ?'selected':''}>
									準備中
							    </option>
							    <option value="1" ${(1 == masterVo.ord_status) ?'selected':''}>
									出貨中
							    </option>
							    <option value="2" ${(2 == masterVo.ord_status) ?'selected':''}>
									可取貨
							    </option>
							    <option value="3" ${(3 == masterVo.ord_status) ?'selected':''}>
									交易完成
							    </option>
							    <option value="4" ${(3 == masterVo.ord_status) ?'selected':''}>
									移除
							    </option>
							</select>
					   </td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="ord_id_confirm"  value="${masterVo.ord_id}"> 
			<input type="hidden" name="action"  value="confirmOrderMaster">
		    <input type="submit" class="btn btn-info btn" value="確認修改">
	</form>
</body>
</html>