var personListPanel = {};
personListPanel.view;
var person;
var personTemplate;

personListPanel.createChildren = function () {
    console.log('createChilder personListtPanel');
}

personListPanel.createView = function () {

    console.log('createView personListPanel');
    personListPanel.view = service.doGet('GET', 'html/personListPanel.html', false);
}
personListPanel.prePopulate = function () {

    person = service.doGet('GET', 'http://localhost:8080/ws/do/person',  false);
    person = JSON.parse(person);
    constructList(person);
}

personListPanel.listenEvents = function () {
    var table = document.getElementById('person-table');
    document.getElementById('add').addEventListener('click', addSelected);
    // document.getElementById('delete').addEventListener('click', onDeleteSelected);
    eventManager.subscribe('submitSelected', onSubmitSelect);
    for(i = 0; i < table.rows.length - 1; i++) {
        document.getElementById('r' + i).addEventListener('click', listItemSelected);
        document.getElementById('d' + i).addEventListener('click', onDeleteSelected);
    }
}

personListPanel.setDeafults = function () {

    console.log('set defaults personListPanel');
    var table = document.getElementById('person-table');
    eventManager.broadcast('listItemSelected', table.rows[1]);
}

var constructList = function (person) {

    console.log(person);
    getPersonTemplate();
    var temp = '';
    for(i = 0; i< person.length; i++) {
    var personValues = Object.values(person[i]);
    temp += personTemplate.replace(/%id%/, personValues[0])
                         .replace(/%firstname%/, personValues[1])
                         .replace(/%lastname%/, personValues[2])
                         .replace(/%email%/, personValues[3])
                         .replace(/%birthdate%/, personValues[4])
                         .replace(/%admin%/, personValues[5])
                         .replace(/rId/, 'r' + i)
                         .replace(/dId/, 'd' + i);
    }
    document.getElementById('person-table').innerHTML += temp;
    console.log(temp);
};

var getPersonTemplate = function () {
    personTemplate = service.doGet('GET', 'html/personTable.html', false);
}

var listItemSelected = function () {
    isCreate = false;
    eventManager.broadcast('listItemSelected', this);
};

var addSelected = function () {
    isCreate = true;
    eventManager.broadcast('addSelected', '');
};

var onSubmitSelect = function (person) {
    if(isCreate === true) {
        constructList(person);
    } else {
        updatePerson(person);
    }
};

var onDeleteSelected = function () {

    var table = document.getElementById('person-table')
    var index = this.parentNode.parentNode.rowIndex;
    if (index === 1) {
        table.rows[index + 1].click();
    }
    table.deleteRow(index);
};

var updatePerson = function (person) {

    var id = person[0].id;
    var table = document.getElementById('person-table' );
    var tr = table.rows;
    for (i = 1; i < tr.length; i++) {
        var td = tr[i].getElementsByTagName('td')[0].innerHTML;

        if (td == id) {
            for(j = 0; j < keyList.length; j++ ) {
                var inputData = person[0][keyList[j]];
                tr[i].cells[j].innerHTML = inputData;
            }
        }
    }
};
