function displayCastForm(){
    alert("made it to displaycastform");
        document.getElementById("cast").innerHTML += 
        "<select class = 'form-control' id = ''>
        #foreach($person in ${people})
            #set($id = ${person.getFullName().replace(" ","")})
            #set($actor = "actor")
        <option id = '$id$actor' value = '${person.getPersonId()}'>${person.getFullName()}</option>
         #end</select>"
    }

function getRolesForActor(person){
    alert("made it to getroelsforactor");
    console.log(person.getRole());

}

var str = "str";

if(str.)
var select = document.createElement("select");
select.id
var option = document.createElement("optiom");
option.text = "Text";
option.value = "myvalue";
var select = document.getElementById("id-to-my-select-box");
select.appendChild(option);

document.getElementById("actor"+count).addEventListener("change", function(){
    getRolesForActor(${person.getRole()});});

    		/*function getRolesForActor(){
		    var personID = document.getElementById("actor"+count).value;	    
		    #set ($role = ${PersonDAO.getPersonById(personID).getRole()})
		    var role = "$role";	
        }*/

        function getRolesForActor(){
			var arrayValue =  document.getElementById("actor"+count).value;
			var roleOfActor = actorRoleArray[arrayValue];
			var allPossibleRoles;
			if(roleOfActor.includes(",")){
				allPossibleRoles = roleOfActor.split(",");
			}else{
			allPossibleRoles = roleOfActor;
			}
			
			var div = document.getElementById("cast");
			var select = document.createElement("select");
			select.id = "role"+count;
			select.name = "role"+count;
			select.class = "form-control";
			var option = document.createElement("option");
			option.text = "Please select a role";
			option.value = "0";
			select.appendChild(option);	
			
			for(var i = 0;  i < allPossibleRoles.length; i++){
				var option = document.createElement("option");
				option.text = allPossibleRoles[i];
				option.value = allPossibleRoles[i];
				select.appendChild(option);	
			}
		
		}