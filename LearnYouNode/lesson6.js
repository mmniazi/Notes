var myModule = require('./myModule');

var directory = process.argv[2];
var extension = process.argv[3];

myModule(directory, extension, function (err, fileNames) {
    if(err) console.error(err);
    for (var i = 0; i < fileNames.length; i++) {
        console.log(fileNames[i]);
    }
});

