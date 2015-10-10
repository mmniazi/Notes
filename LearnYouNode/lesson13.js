var http = require('http');
var urlLib = require('url');

var server = http.createServer(function (request, response) {
    var url = urlLib.parse(request.url, true);
    var date, json;
    if (url.pathname == '/api/parsetime') {
        date = new Date(url.query.iso);
        json = JSON.stringify(
            {
                hour: date.getHours(),
                minute: date.getMinutes(),
                second: date.getSeconds()
            });
    } else if (url.pathname == '/api/unixtime') {
        date = new Date(url.query.iso);
        json = JSON.stringify({unixtime: date.getTime()});
    }
    response.writeHead(200, {'Content-Type': 'application/json'});
    response.end(json);
});

server.listen(process.argv[2]);