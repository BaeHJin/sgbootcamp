function registerEvent(){
	
	var mydiv = document.querySelector("#mydiv");

	mydiv.addEventListener("click", function(evt){
		console.log("oh! mouse up fired");
	});	
}

//jquery 에서는 '$(document).ready()' 사용
	document.addEventListener("DOMcontentLoaded", function(){
		registerEvent();
	});