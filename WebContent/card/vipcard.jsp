<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<title>编辑会员卡</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/css/store.css" type="text/css" />
<link rel="stylesheet" href="/css/layout.css" type="text/css" />
<link rel="stylesheet" href="/css/jquery.validation.css" type="text/css" />
<link rel="stylesheet" href="/css/ie_style.css" type="text/css" />
<link rel="stylesheet" href="/css/bootstrap.css" media="screen"
	type="text/css" />

<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="/js/easyform.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/classify.js"></script>

<script type="text/javascript">
	$(function() {
		$('#header').load('/load/header.html');
		$('#header2').load('/load/header2.html');
		$('#foot').load('/load/foot.html');
	});

	$(document).ready(function() {
		$('#addvip').easyform();
	});
</script>
</head>

<body>

	<div id="main">

		<div id="header"></div>
		<div id="content">
			<div id="header2"></div>
			<div class="wrapper">
				<div class="aside">
					<ul class="nav001">
						<li><a href="/vip/card/addvipcard.jsp">创建会员卡</a></li>
						<li>
							<a class="current">查询会员卡</a>
						</li>
					</ul>
					<div class="box">
						<div class="inner">
							<ul class="list1">
								<li><a href="#">微博</a></li>
								<li><a href="#">微信</a></li>
								<li><a href="#">备用链接</a></li>
								<li><a href="#">备用链接</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="mainContent">

					<div class="txt1"></div>
					<div class="line-hor"></div>

					<div class="wrapper">
						<form id="addvip" action="/vip/card/update.action?id=${card.id}" method="post"
							class="validation-form-container" enctype="multipart/form-data">

							<div class="field">
								<label>会员卡名称</label>

								<div class="ui left labeled input">
									<input id="art_name" name="name" type="text" value="${card.name}"
										easyform="length:1-255;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>级别</label>
								<div class="ui left labeled input">
									<input id="level" name="level" type="text" value="${card.level}" placeholder="请使用1-100之间的整数，数值越大，级别越高。"
										easyform="money;real-time;" message="请使用1-100之间的整数，数值越大，级别越高。"
										easytip="disappear:lost-focus;theme:blue;" />
									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>折扣</label>
								<div class="ui left labeled input">
									<input id="discount" name="discount" type="text" value="${card.discount}" placeholder="请使用小数，如95折则输入0.95"
										easyform="money;real-time;" message="请使用小数，如95折则输入0.95"
										easytip="disappear:lost-focus;theme:blue;" />
									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>联系地址</label>

								<div class="ui left labeled input">
									<input id="address" name="address" type="text" value="${card.contactAddress}"
										easyform="length:1-255;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>联系电话</label>

								<div class="ui left labeled input">
									<input id="phone" name="phone" type="text" value="${card.contactNumber}"
										easyform="length:1-255;real-time;" message="请使用标准中英文字符"
										easytip="disappear:lost-focus;theme:blue;" />
									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>会员卡简介</label>
								<div class="ui left labeled input">
									<textarea name="introduction" id="introduction"
										style="height: 170px;">${card.introduction}</textarea>

									<div class="ui corner label">
										<i class="asterisk icon">*</i>
									</div>
								</div>
							</div>
							<div class="field">
								<label>会员卡图片</label>
								<img src="${card.pic}"/>
							</div>
							<div class="field">
								<label>更换会员卡图片</label>
								<div class="ui left labeled input">
									<input type="file" name="file"></input>
								</div>
							</div>

							<input value="下一步" type="submit" class="btn btn-default"
								name="nextpage" style="float: right;"> <br
								style="clear: both" />
						</form>

					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<div id="foot"></div>
	</div>

	<script type="text/javascript"
		src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>
</body>

</html>
