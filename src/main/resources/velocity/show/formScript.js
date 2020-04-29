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