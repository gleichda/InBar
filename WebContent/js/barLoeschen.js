
/**
 *  @author david
 *  @date 08.01.2018
 */

function loeschenButton(){
	var confirmValue = confirm("Sind sie sicher, dass Sie Ihre Bar und alle zugeordneten Events löschen wollen?"); 
	if (confirmValue){
		var formular = document.getElementById("barLoeschenForm");
		formular.submit();
	}
}

function init(){
	document.getElementById("barLoeschenButton").addEventListener("click", loeschenButton);
}

document.addEventListener("DOMContentLoaded", init);