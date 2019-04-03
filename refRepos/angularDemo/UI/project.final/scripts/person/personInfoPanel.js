var personInfoPanel = {};
personInfoPanel.view;
var isCreate = null;
var keyList = ['id', 'firstname', 'lastname', 'email', 'birthdate', 'isadmin'];

personInfoPanel.createChildren = function () {
    console.log('createChilder personInfoPanel');
}

personInfoPanel.createView = function () {
    personInfoPanel.view = service.doGet('GET', 'html/personInfoPanel.html', false);
}

personInfoPanel.prePopulate = function () {
    console.log('prepopulate personInfoPanel');
}

personInfoPanel.listenEvents = function () {
    console.log('Listen Events personInfoPanel');
    document.getElementById('submit').addEventListener('click', submitSelected);
    document.getElementById('reset').addEventListener('click', onResetSelect);
    eventManager.subscribe('listItemSelected', onListItemSelect);
    eventManager.subscribe('addSelected', onAddSelect);
}

personInfoPanel.setDeafults = function () {
    console.log('set defaults personInfoPanel');
}

var submitSelected = function () {
    var temp = {};
    var person = [];
    for(i = 0; i< keyList.length; i++) {
        temp[keyList[i]] = document.getElementById(keyList[i]).value;
    }
    person.push(temp);
    eventManager.broadcast('submitSelected', person);
};

var onResetSelect = function () {
};


var onAddSelect = function () {
    
    for(i = 0; i< keyList.length; i++) {
        document.getElementById(keyList[i]).value = '';
    }
    document.getElementById(keyList[1]).focus();
};

var onListItemSelect = function(tableRow) {
    
    for(i = 0; i< keyList.length; i++) {
        document.getElementById(keyList[i]).value = tableRow.cells[i].innerHTML;
    }
};