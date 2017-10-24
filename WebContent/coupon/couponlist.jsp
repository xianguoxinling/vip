<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
	<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon"/>
<link href="/css/store.css" rel="stylesheet" type="text/css" />
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.validation.css" rel="stylesheet" type="text/css" />
<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/bootstrap.css" media="screen" type="text/css" />
<link rel="stylesheet" href="/css/normalize.css" type="text/css" />

<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>

	<script type="text/javascript">
	$(function() {
	$('#header').load('/load/header.html');
	$('#header2').load('/load/header2.html');
	$('#foot').load('/load/foot.html');
	});
	</script>

</head>

<body>
	<div id="main">
		<!-- header -->
		<div id="header"></div>
		<div id="header2"></div>

		<table class="cart_product">
			<tr class="bg">
				<th class="name">优惠券名称</th>
				<th class="name">优惠券编号</th>
				<th class="price" style="text-align: center">优惠券面值</th>
				<th class="price" style="text-align: center">满多少钱可用</th>
				<th class="name">状态</th>
				<th class="edit2" style="text-align: center">编辑</th>
			</tr>

			<c:forEach items="${couponList}" var="coupon">
				<tr>
					<td class="name"><c:out value="${coupon.name}" /></td>
					<td class="name"><c:out value="${coupon.id}" /></td>
					<td class="price"><c:out value="${coupon.faceValue}" /></td>
                    <td class="price"><c:out value="${coupon.minimumLimit}" /></td>
					<td class="name"><c:out value="${coupon.state}" /></td>
					<td class="edit2">
					
					<button class="btn btn-primary btn-sm" onclick="location.href='/vip/coupon/update.action?couponID=${coupon.id}';">更新优惠券</button>
					<button class="btn btn-primary btn-sm" style="margin-top:10px" onclick="location.href='/vip/coupon/delete.action?couponID=${coupon.id}';">删除优惠券</button>
					</td>
				</tr>
			</c:forEach>

		</table>

		<div id="foot"></div>
	</div>
	<script type="text/javascript" src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>
</html>
