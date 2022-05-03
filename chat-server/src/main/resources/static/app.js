var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-location-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
var long = 69.240562+Math.random();
var lat = 41.345570 + Math.random();
function sendLocation() {
    // if (navigator.geolocation) {
    //     navigator.geolocation.getCurrentPosition(function(position){
    //
    //     });
    //
    // }
    stompClient
        .send(
            "/app/location",
            {},
            JSON.stringify(
                {
                    'latitude': lat+=.0005,
                    'longitude': long-=.0003,
                    'speed': 0,
                    'sender': $("#name").val(),
                }
            )
        );
}

function getPosition(position) {
    return  position.coords;
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        setInterval(function() {
            sendLocation();
        }, 1000);
    });
});