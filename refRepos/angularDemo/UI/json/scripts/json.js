var getPersonDetail = function () {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        
        var personDetails = JSON.parse(this.responseText);
            var temp = '';
            var x;
            for(x in personDetails) {
                temp += personDetails[x].firstName + ' ' +personDetails[x].lastName + ' ' + '<br>'; 
            }
            document.getElementById('demo').innerHTML = temp;
    }
  };
  xhttp.open('GET', 'assets/person.json', true);
  xhttp.send();
};