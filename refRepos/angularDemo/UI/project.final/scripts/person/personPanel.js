var personPanel = {};
personPanel.view;

personPanel.init = function () {
    personPanel.createChildren();
    personPanel.createView();
    personPanel.prePopulate();
    personPanel.listenEvents();
    personPanel.setDeafults();
}

personPanel.createChildren = function () {
    personListPanel.createChildren();
    personInfoPanel.createChildren();
}

personPanel.createView = function () {
    personListPanel.createView();
    personInfoPanel.createView();
    document.getElementById('person-panel').innerHTML = personListPanel.view;
    document.getElementById('person-panel').innerHTML += personInfoPanel.view;
}

personPanel.prePopulate = function () {
    personListPanel.prePopulate();
    personInfoPanel.prePopulate();
}

personPanel.listenEvents = function () {
    personListPanel.listenEvents();
    personInfoPanel.listenEvents();
}

personPanel.setDeafults = function () {
    personListPanel.setDeafults();
    personInfoPanel.setDeafults();
}

var createPersonPanelView = function () {

    console.log('create Person panel view');
    personPanel.view = service.doGet('GET', 'html/personPanel.html', false);
};