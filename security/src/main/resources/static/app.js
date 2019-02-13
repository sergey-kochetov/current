ws = new WebSocket("ws://localhost:8080/pong");

ws.onopen = function() {
    action('open connection');
}
ws.onmessage = function(ev) {
    action(ev.data);
}
ws.onclose = function(ev) {
    action('close connection');
}
ws.onerror = function(ev) {
    action('error');
}

function action(message) {
    var output = document.getElementById("stack");
    var newP = document.createElement('p');
    newP.appendChild(document.createTextNode(message));
    output.appendChild(newP);
}

function ping() {
    var message = document.getElementById('message').value;
    action('send:' + message);
    ws.send(message)
}

function actionSec(message) {
    var output = document.getElementById('inscroll')
    var newP = document.createElement('p');
    newP.appendChild(document.createTextNode(message));
    output.appendChild(newP);
}

window.onmousemove = function(e) {
    var xx = ${document}.height()*.9 - e.y
    if(xx < 0) {
        ws.send("load");
    }
}