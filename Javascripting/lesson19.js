var a = 1, b = 2, c = 3;

(function firstFunction(){
    var b = 5, c = 6;

    (function secondFunction(){
        var b = 8;
        console.log("a: "+a+", b: "+b+",c: "+c);
        (function thirdFunction(){
            var a = 7, c = 9;

            (function fourthFunction(){
                var a = 1, c = 8;

            })();
        })();
    })();
})();


// a b c are not accessible outside, it is self contained
(function () {
    var a = 1;
    var b = 2;
    var c = 3;
    console.log(a + b + c)
})();


// Polutes gloabal namespace
var a = 1;
var b = 2;
var c = 3;
console.log(a + b + c)


// function accepts function 
