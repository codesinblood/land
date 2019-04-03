var rsp = {};
rsp.view;

rsp.createChildren = function () {
    console.log("rsp 1");
}

rsp.createView = function () {
    console.log("rsp 2");
    rsp.view = service.doGet('GET', 'html/rsp.html', false);
};

rsp.prePopulate = function () {
    console.log("Prepopulate rsp");
}

rsp.listenEvents = function () {
    console.log("rsp 4");
    eventManager.subscribe('entitySelected', onSelectEntity);
}

rsp.setDeafults = function () {
    console.log("rsp 5");
}

var onSelectEntity = function(entity) {
    if(entity == 'person') {
    createPersonPanelView();
    document.getElementById('rsp-panel').innerHTML = personPanel.view;
    personPanel.init();
    }
        // else {
        // // addressPanel.init();
    // }
}

