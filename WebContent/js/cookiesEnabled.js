/**
 * @author david
 * @date 15.01.2018
 */

function cookiesEnabled(){
	if (!navigator.cookieEnabled){
		alert("Sie haben Cookies deaktivert um sich einllogen zu können müssen sie Cookies in Ihrem Browser aktivieren");
	}
}

document.addEventListener("DOMContentLoaded", cookiesEnabled);
