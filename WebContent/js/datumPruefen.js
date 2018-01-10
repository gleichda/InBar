/**
 * @author sabine
 * @date 10.01.2018
 * Quelle: https://www.mediaevent.de/javascript/date-datum-zeit.html; https://wiki.selfhtml.org/wiki/JavaScript/Tutorials/Ereignisverarbeitung
 */

document.addEventListener("DOMContentLoaded", init());
document.querySelector("enddatum").addEventListener("onchange", pruefen);



function pruefen(){
	var heute = new Date;
	var startdatum = new Date(document.getElementById("startdatum"));
	var enddatum = new Date(document.getElementById("enddatum"));
	var startzeit = new Time(document.getElementById("startzeit"));
	var endzeit = new Time(document.getElementById("endzeit"));
	


if (startdatum.getDate() === enddatum.getDate()){ //wenn Start und Ende am gleichen Tag ist, muss die Startzeit vor der Endzeit liegen.
	if (startzeit.getTime() < endzeit.getTime()){
		console.log("Start- und Enddatum sind gleich. Startzeit kleiner Endzeit.")
		endzeit.setCustomValidity("Start- und Enddatum sind gleich. Startzeit kleiner Endzeit.");
	}
	else if (startzeit.getTime()> endzeit.getTime()){
		console.log("Start- und Enddatum sind gleich. Startzeit größer Endzeit. Falscher Wert.")
		endzeit.setCustomValidity("Start- und Enddatum sind gleich. Startzeit größer Endzeit. Falscher Wert.");
	}
}

if (startdatum.getDate() > enddatum.getDate()){
	console.log("Startdatum vor Enddatum.");
	enddatum.setCustomValidity("Startdatum vor Enddatum.");
}
	
	
}

function init(){
	console.log("init")
	document.getElementById("enddatum").addEventListener("onchange", pruefen); // Ausführen, wenn der Wert des Form-Elements geändert wird.
	document.getElementById("endzeit").addEventListener("onchange", pruefen);
}

