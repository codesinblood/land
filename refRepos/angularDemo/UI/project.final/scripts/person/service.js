var service = {};
service.response;

service.doGet = function (method, url, asynchronous) {
    var httpRequest = new XMLHttpRequest();
     httpRequest.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
                service.response = this.responseText;
        }
    };
    httpRequest.open(method, url, asynchronous);
    httpRequest.send();
    return service.response;
}