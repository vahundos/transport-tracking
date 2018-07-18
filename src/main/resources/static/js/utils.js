var trackingUrl = "/ajax/tracking";
var zoneUrl = "/ajax/zone";
var eventUrl = "ajax/event";

var map;
var dataTable;

var trackingArray = [];
var svgPath = 'm 0 0 z'; // transparent icon

function initMap() {
    var vinnitsaLatLng = {lat: 49.234133, lng: 28.458250};

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        gestureHandling: 'cooperative',
        center: vinnitsaLatLng
    });
    map.addListener('click', function (event) {
        var marker = new google.maps.Marker({
            position: event.latLng,
            map: map
        });
        var jsonData = {
                location: {
                    latitude: marker.getPosition().lat(),
                    longitude: marker.getPosition().lng()
                }
            };
        $.ajax({
            url: trackingUrl,
            type: "POST",
            data: JSON.stringify(jsonData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                marker.setLabel(String(data.id));
                marker.id = data.id;
                trackingArray.push(marker);
            },
            error: function () {
                marker.setMap(null);
            }
        });
    });

    initDrawingManager();
    initDataTable();

    loadAndDrawZones();
    loadAndDrawTransport();

    setInterval(updateTransportPosition, 5000);
}

function initDrawingManager() {
    var drawingManager = new google.maps.drawing.DrawingManager({
        drawingControl: true,
        drawingControlOptions: {
            position: google.maps.ControlPosition.TOP_CENTER,
            drawingModes: ['circle']
        },
        circleOptions: {
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            clickable: true,
            editable: false,
            zIndex: 1
        }
    });
    drawingManager.setMap(map);
    drawingManager.addListener('circlecomplete', function (circle) {
        console.log('circle complete');
        var jsonData = {
            radius: circle.getRadius(),
            location: {
                latitude: circle.getCenter().lat(),
                longitude: circle.getCenter().lng()
            }
        };
        $.ajax({
            url: zoneUrl,
            type: "POST",
            data: JSON.stringify(jsonData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                var marker = new google.maps.Marker({
                    position: {
                        lat: circle.getCenter().lat(),
                        lng: circle.getCenter().lng()
                    },
                    icon: {
                        path: svgPath,
                        scale: 1
                    },
                    map: map,
                    label: String(data.id)
                });
            },
            error: function () {
                circle.setMap(null);
            }
        });
    });
}

function initDataTable() {
    dataTable = $('#dataTable').DataTable( {
        language: {search: "Search by transport id"},
        columns: [
            {
                data: 'date',
                searchable: false
            },
            {
                data: 'type',
                searchable: false
            },
            {
                data: 'zone.id',
                searchable: false
            },
            {
                data: 'transport.id'
            }
        ],
        buttons: [
            {
                text: 'Refresh',
                className: 'btn btn-primary col-md-2 col-md-offset-5',
                action: function ( e, dt, node, config ) {
                    $.ajax({
                        url: eventUrl,
                        type: "GET",
                        contentType: "application/json; charset=utf-8",
                        success: function (data) {
                            dt.clear().rows.add(data).draw();
                        },
                        error: function () {
                            console.log('error on refresh');
                        }
                    });
                }
            }
        ]
    } );
    dataTable.buttons().container()
        .appendTo( '#dataTable_wrapper .row:eq(1)' );
}

function loadAndDrawZones() {
    $.ajax({
        url: zoneUrl,
        dataType: "json",
        type: 'GET',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var zone = response[i];
                var zoneCircle = new google.maps.Circle({
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: '#FF0000',
                    fillOpacity: 0.35,
                    map: map,
                    center: {
                        lat: zone.location.latitude,
                        lng: zone.location.longitude
                    },
                    radius: zone.radius,
                    id: zone.id
                });
                var marker = new google.maps.Marker({
                    position: {
                        lat: zone.location.latitude,
                        lng: zone.location.longitude
                    },
                    icon: {
                        path: svgPath,
                        scale: 1
                    },
                    map: map,
                    label: String(zone.id)
                });
            }
        }
    })
}

function loadAndDrawTransport() {
    $.ajax({
        url: trackingUrl,
        dataType: "json",
        type: 'GET',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var transport = response[i];
                var marker = new google.maps.Marker({
                    position: {
                        lat: transport.location.latitude,
                        lng: transport.location.longitude
                    },
                    map: map,
                    label: String(transport.id),
                    id: transport.id
                });
                trackingArray.push(marker);
            }
        }
    })
}

function updateTransportPosition() {
    $.ajax({
        url: trackingUrl,
        dataType: "json",
        type: 'GET',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                var transport = response[i];
                var existingMarker = trackingArray[getIdIndex(transport.id)];
                var latlng = new google.maps.LatLng(transport.location.latitude, transport.location.longitude);
                existingMarker.setPosition(latlng);
            }
        }
    })
}

function getIdIndex(id) {
    for (var i = 0; i < trackingArray.length; i++) {
        if (trackingArray[i].id === id) {
            return i;
        }
    }
    throw "Illegal state exception: cant find tracking with id " + id;
}

function getCircleLngLtd(circle) {
    return
}