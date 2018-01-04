/**
 * @author david
 * @date 02.01.2018
 */

function validate(){
	var passwort = document.getElementById("passwort");
	var passwort2 = document.getElementById("passwort_wiederholen");
	//passwort.required = true;
	//passwort2.required = true;
	if (! passwort.value == passwort2.value){
		console.log("passwörter stimmen nicht")
		passwort2.setCustomValidity("Die Passwörter stimmen nicht überein");
	}
	else if (passwort.value == passwort2.value){
		console.log("passwörter stimmen")
		passwort2.setCustomValidity("");
	}
}

function init(){
	console.log("init")
	document.getElementById("passwort").addEventListener("keyup", validate);
	document.getElementById("passwort_wiederholen").addEventListener("keyup", validate);
}

document.addEventListener("DOMContentLoaded", init);