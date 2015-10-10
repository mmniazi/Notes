var fs = require('fs');

fs.readFile(process.argv[2], 'utf-8',function callback(err, data){
    if (!err) {
        var arr = data.split('\n');
        console.log(arr.length - 1);
    }
});
