/**
 * @author david
 * @date 04.01.2018
 */

function sendBewertung(){
	var barid = document.getElementById("barid").value;
	var rating;
	if(document.getElementById("star-1").checked){
		rating=1;
	}
	else if(document.getElementById("star-2").checked){
		rating=2;
	}
	else if(document.getElementById("star-3").checked){
		rating=3;
	}
	else if(document.getElementById("star-4").checked){
		rating=4;
	}
	else if(document.getElementById("star-5").checked){
		rating=5;
	}
	else {
		alert("Bitte vergeben sie eine Bewertung und versuchen Sie es dann erneut");
		return;
	}
	var kommentar = document.getElementById("bewertungstext").value;
	if (kommentar==""){
		alert("Bitte geben Sie einen Kommentar und versuchen Sie es dann erneut");
		return;
	}
	var params = 'bewertung=' + rating + '&kommentar=' + kommentar + '&barid=' + barid;
	console.log(params)
	var xhr = new XMLHttpRequest();
	xhr.open('POST', './NeueBewertung');
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	//xhr.setRequestHeader("Content-length", params.length)
	xhr.send(params);

	
	if (xhr.readyState == 4 && xhr.status == 200){
		console.log("erfolgreich gesendet")
	}
	else{
		alert("Fehler beim Senden ihrer Bewertung");
	}
}

function init(){
	document.getElementById("bewertungAbgeben").addEventListener("click", sendBewertung);
}

document.addEventListener("DOMContentLoaded", init);



