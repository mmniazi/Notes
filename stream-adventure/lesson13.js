var combine = require('stream-combiner');
var split = require('split');
var through = require('through2').obj;
var zlib = require('zlib');

module.exports = function () {

    var json;

    function write(data, _, next) {
        if (data.length === 0) return next();
        var row = JSON.parse(data);
        if (row.type === "genre") {
            if (json) this.push(JSON.stringify(json) + '\n');
            json = {"name": row.name, "books": []}
        } else if (row.type === "book") {
            json.books.push(row.name);
        }
        next();
    }

    function end(done) {
        if (json) {
            this.push(JSON.stringify(json) + '\n');
        }
        done();
    }

    var transform = through(write, end);

    return combine(split(), transform, zlib.createGzip());
};
