function displayCastForm(){
	alert("made it to displaycastform");
		document.getElementById("cast").innerHTML += 
						"#foreach($person in ${people})<select id = "${person.getFullName()}actor" onchange = "getRolesForActor($person)"><option value = "${person.getPersonId()}">${person.getFullName()}</option></select>#end"
	}
	
	function getRolesForActor(person){
		alert("made it to getroelsforactor");
		console.log(person.getRole());
	
	}