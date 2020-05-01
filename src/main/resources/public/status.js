function getSelectedText(){
    var status = document.getElementById("status");
    var result = status.options[status.selectedIndex].text;
    return result;
}

function getSelectedValue(){
    var status = document.getElementById("status");
    var result = status.options[status.selectedIndex].value;
    return result;
}

function hideAll(){
    var divsToHide = document.getElementsByClassName("row");
    for(var i = 0; i < divsToHide.length; i++){
        divsToHide[i].style.display = "none";
    }
}

function showById(id){
    var divsToShow = document.getElementsByClassName(id);
    for(var i = 0; i < divsToShow.length; i++){
        divsToShow[i].style.display = "inline-block";
    }
}


function updateShows() {
    hideAll();
    switch (getSelectedValue()) {
        case "A":
            for (i = 0; i < 6; i ++){
                showById(i);
            }
            break;
        case "0":
            showById(0);
            break;
        case "1":
            showById(1);
            break;
        case "2":
            showById(2);
            break;
        case "3":
            showById(3);
            break;
        case "4":
            showById(4);
            break;
        case "5":
            showById(5);
            break;
    }
}