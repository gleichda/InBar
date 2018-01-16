/**
 * 
 */

function fileSizeCheck(){
	console.log("check")
	var bild = document.getElementById("bild");
	var file = bild.files[0];
	var filesize = file.size;
	if ((filesize/1024/1024/5) > 1) {
		bild.setCustomValidity("Ihr Bild ist größer als 5MB bitte laden Sie ein kleineres Bild hoch");
	}
	else {
		bild.setCustomValidity("");
	}
}

function init(){
	document.getElementById("bild").addEventListener("change", fileSizeCheck);
}

document.addEventListener("DOMContentLoaded", init);