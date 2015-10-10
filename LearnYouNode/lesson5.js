var fs = require('fs');

var file = process.argv[2];
var ext = process.argv[3];
fs.readdir(file, function (err, files) {
    if (!err) {
        for (var i = 0; i < files.length; i++) {
            if (files[i].indexOf("." + ext) != -1) {
                console.log(files[i]);
            }
        }
    }
});
