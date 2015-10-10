var http = require('http');
var concatStream = require('concat-stream');

var responses = [1, 2, 3];
var counter = 0;
http.get(process.argv[2], function (response) {
    response.setEncoding('utf8');
    response.pipe(concatStream(function (data) {
        responses[0] = data.toString();
        counter++;
        if (counter == 3) {
            console.log(responses[0]);
            console.log(responses[1]);
            console.log(responses[2]);
        }
    }));
});
http.get(process.argv[3], function (response) {
    response.setEncoding('utf8');
    response.pipe(concatStream(function (data) {
        responses[1] = data.toString();
        counter++;
        if (counter == 3) {
            console.log(responses[0]);
            console.log(responses[1]);
            console.log(responses[2]);
        }
    }));
});
http.get(process.argv[4], function (response) {
    response.setEncoding('utf8');
    response.pipe(concatStream(function (data) {
        responses[2] = data.toString();
        counter++;
        if (counter == 3) {
            console.log(responses[0]);
            console.log(responses[1]);
            console.log(responses[2]);
        }
    }));
});


