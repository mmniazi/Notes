var through2 = require('through2');
var split = require('split');
var counter = 1;
process.stdin
    .pipe(split())
    .pipe(through2(function (line, _, next) {
        if(counter != 1){
            line = "\n".concat(line.toString());
        }
        if (counter % 2 == 0) this.push(line.toString().toUpperCase());
        else this.push(line.toString().toLowerCase());
        counter++;
        next();
    }))
    .pipe(process.stdout);
