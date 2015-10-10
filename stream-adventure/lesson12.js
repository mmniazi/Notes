var duplexer = require('duplexer2');
var through = require('through2').obj;

module.exports = function (counter) {
    var count = {};

    function write(data, encoding, next) {
        if (count.hasOwnProperty(data.country)) count[data.country]++;
        else count[data.country] = 1;
        next();
    }

    function end(done) {
        counter.setCounts(count);
        done();
    }

    var input = through(write, end);
    return duplexer(input, counter);
};
