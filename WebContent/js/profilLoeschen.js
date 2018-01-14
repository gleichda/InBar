/**
 *  @author david
 *  @date 08.01.2018
 */

function loeschenButton(){
	var confirmValue = confirm("Sind sie sicher, dass Sie Ihr Profil löschen wollen?"); 
	if (confirmValue){
		var formular = document.getElementById("profilLoeschenForm");
		formular.submit();
	}
}

function init(){
	document.getElementById("profilLoeschenButton").addEventListener("click", loeschenButton);
}

document.addEventListener("DOMContentLoaded", init);

