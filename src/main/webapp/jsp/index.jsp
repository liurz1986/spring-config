<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.8.3.min.js">
<script type="text/javascript">
$(function(){
	alert("dddd");
	$.ajax({
		url:"UserController/saveUser",
		contentType:"application/json",
		data:JSON.toStringigy({"name":"liurz","password":"12345"}),
		dataType:"json",
		success:function(date){
			alert(date);
		},
		error:function(date){
			alert(data.data);
		}
	})
	
	
	
})


</script>
</head>
<body>
  
</body>
</html>