<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group_art.model.*"%>
<%@ page import="com.mem.model.*"%>
<jsp:include page="/front-end/header.jsp"/>


	<%
		Group_ArtVO group_ArtVO = (Group_ArtVO) request.getAttribute("group_ArtVO");
		MemberVO memVO2 = (MemberVO) session.getAttribute("memVO");
		pageContext.setAttribute("memVO2",memVO2);
	%>

	<div class="container" style="margin-top:10%;">
		<form class="well form-horizontal"
			action="<%=request.getContextPath() %>/grouplist/grouplist.do" method="post"
			id="contact_form" enctype="multipart/form-data">
			<fieldset>
				<!-- Form Name -->
				<legend class="text-center" style="font-size: 20px;">
					<b style="color:black">新增揪團</b>
				</legend>
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">文章名稱</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-comment"></i></span> <input name="art_name"
								placeholder="文章名稱" class="form-control" type="text"
								value="<%=(group_ArtVO == null) ? "" : group_ArtVO.getArt_name()%> ">
						</div>
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">揪團到期日期</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span> <input id="f_date1" name="exp_date"
								placeholder="到期日期" class="form-control" type="text">
						</div>
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">成團金額</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-usd"></i></span> <input name="grou_price"
								placeholder="成團金額" class="form-control" type="text"
								value="<%=(group_ArtVO == null) ? "" : group_ArtVO.getGrou_price()%>">
						</div>
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">文章圖片</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-picture"></i></span> <input id="inputFile" name="art_img"
								class="form-control" type="File">
								<img id="image_upload_preview" src=""/>
						</div>
					</div>
				</div>
					
				<div class="form-group">
					<label class="col-md-4 control-label">外帶</label>
					<div class="col-md-1 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon">
        						 <input id="address1" name="ship_locatbt" type="radio" aria-label="..." value="0">
      						</span>
						</div>
					</div>
					<label class="col-md-1 control-label">外送</label>
					<div class="col-md-1 inputGroupContainer" >
						<div class="input-group">
							<span class="input-group-addon">
        						 <input id="address2" name="ship_locatbt" type="radio" aria-label="..." value="1" checked>
      						</span>
						</div>
					</div>
				</div>

				<!-- Text input-->

				<div class="form-group" id="address3">
					<label class="col-md-4 control-label">外送地址</label>
					<div class="col-md-4 inputGroupContainer" >
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-home"></i></span> <input id="address" name="ship_locat"
								placeholder="外送地址" class="form-control" type="text"
								value="<%=(group_ArtVO == null) ? "" : group_ArtVO.getShip_locat()%>">
						</div>
					</div>
					<div class="col-md-4" id="distance" style="padding-left:0px;"></div>
				</div>

				<!-- Text input-->

				<div class="form-group">
					<label class="col-md-4 control-label">發送飲品地址</label>
					<div class="col-md-4 inputGroupContainer">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-home"></i></span> <input name="send_locat"
								placeholder="發送飲品地址" class="form-control" type="text"
								value="<%=(group_ArtVO == null) ? "" : group_ArtVO.getSend_locat()%>">
						</div>
					</div>
				</div>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label"></label>
					<div class="col-md-4">
						<input type="hidden" name="action" value="insert">
						<input type="hidden" name="mem_id" value="${memVO2.mem_id}">
						<button type="submit" class="btn btn-warning" id="insert">
							新增<span class="glyphicon glyphicon-send"></span>
						</button>
						<a href="<%=request.getContextPath()%>/front-end/grouplist.jsp"><button type="button" class="btn btn-warning">
							取消<span class="glyphicon glyphicon-send"></span>
						</button></a>
					</div>
				</div>

			</fieldset>
		</form>
	</div>
	<!-- /.container -->
	
	
<% 
  java.sql.Timestamp exp_date = null;
  try {
	  	exp_date = group_ArtVO.getExp_date();
   } catch (Exception e) {
	   	exp_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<!-- GOOGLE MAP 金鑰 -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCOcxUQe-85L7pEFGVR-BJXxHoHI0Doc8s">
</script>
<script>
	//控制外送外帶
	$('#address1').click(function(){
		$('#address3').hide();
	});
	$('#address2').click(function(){
		$('#address3').show();
	});
	
	//可以選時間的日期
    $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 20,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=exp_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
    //圖片預覽
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image_upload_preview').attr('src', e.target.result);
                $('#image_upload_preview').css('width','310px').css('height','200px');
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#inputFile").change(function () {
        readURL(this);
    });
    
  //修改時直接判斷位址 
    if($('#address').val() != ""){
  	//地址轉成經緯度   
        var geocoder = new google.maps.Geocoder();
        addr = $("#address").val();
        if(addr != ""){
        geocoder.geocode({
                'address': addr
            }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var lat = results[0].geometry.location.lat();
                    var lng = results[0].geometry.location.lng();
                  //GOOGLE MAP DISTANCE
                	var origin1 = new google.maps.LatLng(24.968009, 121.191711);
                	var destinationA = new google.maps.LatLng(lat, lng);
                	var service = new google.maps.DistanceMatrixService();
                	service.getDistanceMatrix(
                	  {
                	    origins: [origin1],
                	    destinations: [destinationA],
                	    travelMode: 'DRIVING',
                	  }, callback);

                	function callback(response, status) {
                		if (status == 'OK') {
                		    var origins = response.originAddresses;
                		    for (var i = 0; i < origins.length; i++) {
                		      var results = response.rows[i].elements;
                		        var element = results[i];
                		        var distance = element.distance;
                		        if(distance.value > 10000){
                		        	$('#distance').html("距離為"+distance.text+",超出外送範圍請重新輸入地址");
                		        	$('#distance').css("color","red");
                		        	$('#insert').attr('disabled',true);
                		        }else{
                		        	$('#distance').html("距離為"+distance.text+",可接受外送");
                		        	$('#distance').css("color","green");
                		        	$('#insert').attr('disabled',false);
                		        }
                		        
                		    }
                		 }
                	}
            }
        }); 
        }else{
        	$('#distance').html(" ");
        	$('#insert').attr('disabled',false);
        }
    } 
//GOOGLE MAP DISTANCE 輸入後判斷
$("#address").blur(function(){
	//地址轉成經緯度   
    var geocoder = new google.maps.Geocoder();
    addr = $("#address").val();
    if(addr != ""){
    geocoder.geocode({
            'address': addr
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
              //GOOGLE MAP DISTANCE origin1 為資策會的經緯度
            	var origin1 = new google.maps.LatLng(24.968009, 121.191711);
            	var destinationA = new google.maps.LatLng(lat, lng);
            	var service = new google.maps.DistanceMatrixService();
            	service.getDistanceMatrix(
            	  {
            	    origins: [origin1],
            	    destinations: [destinationA],
            	    travelMode: 'DRIVING',
            	  }, callback);

            	function callback(response, status) {
            		if (status == 'OK') {
            		    var origins = response.originAddresses;
            		    for (var i = 0; i < origins.length; i++) {
            		      var results = response.rows[i].elements;
            		        var element = results[i];
            		        var distance = element.distance;
            		        if(distance.value > 10000){
            		        	$('#distance').html("距離為"+distance.text+",超出外送範圍請重新輸入地址");
            		        	$('#distance').css("color","red");
            		        	$('#insert').attr('disabled',true);
            		        }else{
            		        	$('#distance').html("距離為"+distance.text+",可接受外送");
            		        	$('#distance').css("color","green");
            		        	$('#insert').attr('disabled',false);
            		        }
            		        
            		    }
            		 }
            	}
        }
    }); 
    }else{
    	$('#distance').html(" ");
    	$('#insert').attr('disabled',false);
    }

	
  	
})


</script>
	
<jsp:include page="/front-end/footer.jsp"/>