var eventManager = {}

  // to store subscribers in list
    var listen = [];

    // to assign events to actions
    eventManager.subscribe = function (eventName, action) {
        listen[eventName] = action;
        console.log(listen);
    };

    // to fire the action for events
    eventManager.broadcast = function (eventName, data) {
        var handler = listen[eventName];
        handler(data);
        // more than one handlers 
        // handlers = listen[eventName];
        // for (handler : handlers) {
            // handler(data);
        // }
    }