var lsp = {};
lsp.view;

lsp.createChildren = function () {
    console.log("Create Children lsp");
}

lsp.createView = function () {
    lsp.view = service.doGet('GET', 'html/lsp.html', false);
};

lsp.prePopulate = function () {
        console.log("Prepoulate lsp");
}

lsp.listenEvents = function () {
    
        console.log("Lsp listen Events");
        document.getElementById("person-item").addEventListener('click', onPersonSelect);
        document.getElementById("address-item").addEventListener('click', onAddressSelect);
        
}

lsp.setDeafults = function () {
        console.log("lsp Set Defaults");
        eventManager.broadcast('entitySelected', 'person');
}

var onPersonSelect = function () {
    eventManager.broadcast('entitySelected', 'person');
};

var onAddressSelect = function () {
    eventManager.broadcast('entitySelected', 'address');
};
