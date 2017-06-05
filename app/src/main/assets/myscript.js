function toggleNightMode(){  

 var color = document.getElementById('content').style.color;
 var h_color = document.getElementById('header').style.color;
 var el = document.getElementById('bg');


if ((color == "black" && h_color == "black") || (color == "" && h_color == "")) {
    document.getElementById('content').style.color="white";
    document.getElementById('header').style.color="white";
    el.classList.add('nightMode');
	} else {
	document.getElementById('content').style.color="black";
	document.getElementById('header').style.color="black";
	el.classList.remove('nightMode');
	} 
} 
