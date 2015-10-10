var http = require('http');
var concatStream = require('concat-stream');
http.get(process.argv[2], function (response) {
    response.pipe(concatStream(function (err, data) {
        if (err) return console.error(err);
        console.log(data.length);
        console.log(data.toString());
    }));
});