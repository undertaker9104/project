<%@ page language="java" contentType="text/html; charset=utf-8"   pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>    
<html>
    <head>
        <title>Web test</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>
    </head>
    <body onload="connect();" onunload="disconnect();">
        <h1> Web test </h1>
	    <h3 id="statusOutput" class="statusOutput"></h3>
        <div class="panel input-area">
            <input id="action" class="text-field" type="text" placeholder="使用者名稱" value="getOnePic"/>
            <input id="find_one"  class="text-field" type="text" placeholder="訊息" value="P000001"onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		</div>
   </body>
<script>
    var MyPoint = "/MyEchoServer/peter/309";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
  	var statusOutput = document.getElementById("statusOutput");
	var webSocket;

	function connect() {
		webSocket = new WebSocket(endPointURL);
		webSocket.onopen = function(event) {
		updateStatus("WebSocket 成功連線");
		};

		webSocket.onmessage = function(event) {
	         var jsonObj = JSON.parse(event.data);
	         var message ="action="+jsonObj.action+"&"+"find_one="+jsonObj.find_one;
	         var URI ='<%=request.getContextPath()%>/product.do?'+message;
	         var URIIMG=window.open('<%=request.getContextPath()%>/product.do?'+message,'show',config='toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,fullscreen=no,height=300,width=350,top=100,left=20');
	         
	         window.alert('<%=request.getContextPath()%>/product.do?'+message,'show',config='toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,fullscreen=no,height=300,width=350,top=100,left=20');
	      
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}

	function sendMessage() {
		  var action = document.getElementById("action");
		  var action = action.value.trim();
	      var find_one = document.getElementById("find_one");
		  var find_one = find_one.value.trim();
	      var jsonObj = {"action" :action,"find_one" :find_one};
	      webSocket.send(JSON.stringify(jsonObj));
    }
	
	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
</script>


</html>