/**
 * @author sabine
 * @date 10.01.2018
 * Quelle: https://www.mediaevent.de/javascript/date-datum-zeit.html; https://wiki.selfhtml.org/wiki/JavaScript/Tutorials/Ereignisverarbeitung
 */

//document.addEventListener("DOMContentLoaded", init);
//document.querySelector("enddatum").addEventListener("onchange", pruefen);
window.onload=init;


function pruefen(){
	var startdatum = document.getElementById("startdatum");
	var enddatum = document.getElementById("enddatum");
	var startzeit = document.getElementById("start");
	var endzeit = document.getElementById("ende");
		

if (startdatum.value === enddatum.value){ //wenn Start und Ende am gleichen Tag ist, muss die Startzeit vor der Endzeit liegen.
	enddatum.setCustomValidity("");
	if (startzeit.value < endzeit.value){
		console.log("Start- und Enddatum sind gleich. Startzeit kleiner Endzeit.")
		endzeit.setCustomValidity("Start- und Enddatum sind gleich. Startzeit kleiner Endzeit.");
		endzeit.setCustomValidity("");
	}
	else if (startzeit.value> endzeit.value){
		console.log("Start- und Enddatum sind gleich. Startzeit größer Endzeit. Falscher Wert.")
		endzeit.setCustomValidity("Start- und Enddatum sind gleich. Startzeit größer Endzeit. Falscher Wert.");
	}
}

if (startdatum.value > enddatum.value){
	console.log("Startdatum vor Enddatum.");
	enddatum.setCustomValidity("Startdatum vor Enddatum.");
}

if (startdatum.value < enddatum.value){
	console.log ("Start liegt vor dem Ende. Richtig");
	enddatum.setCustomValidity("");
	
}
	
}

function init(){
	console.log("init")
	document.getElementById("enddatum").addEventListener("change", pruefen); // Ausführen, wenn der Wert des Form-Elements geändert wird.
	console.log("enddatum")
	document.getElementById("ende").addEventListener("change", pruefen);
}







