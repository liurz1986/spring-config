window.onbeforeunload = function() {
	if (event.clientX>document.body.clientWidth && event.clientY<0||event.altKey){
		$.ajax({
			url : "UserController/logout",
			type : "post"
		});
    }
}