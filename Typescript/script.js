var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var isDone = false;
var height = 6;
var list = [1, 2, 3, 4];
var newList = [1, 2, 3, 4];
console.log(list + " || " + newList);
var Fruit;
(function (Fruit) {
    Fruit[Fruit["Banana"] = 0] = "Banana";
    Fruit[Fruit["Apple"] = 1] = "Apple";
    Fruit[Fruit["PineApple"] = 2] = "PineApple";
})(Fruit || (Fruit = {}));
var banana = Fruit.Banana;
var apple = Fruit[1];
console.log(apple);
var whatever = 3;
console.log(whatever);
whatever = "noob string";
console.log(whatever);
function someFunc() {
}
function printNoobness(player) {
    console.log(player.noobness);
}
var aPlyr = { games: 10, noobness: "imba" };
printNoobness(aPlyr);
function createSquare(config) {
    var newSquare = { color: "white", area: 100 };
    if (config.color) {
        newSquare.color = config.color;
    }
    if (config.width) {
        newSquare.area = config.width * config.width;
    }
    return newSquare;
}
var mySquare = createSquare({ color: "black" });
console.log(mySquare);
var mySearch = function (src, sub) {
    var result = src.search(sub);
    if (result == -1) {
        return false;
    }
    else {
        return true;
    }
};
var myArray = ["bob the builder", "Ben 10"];
var Clock = (function () {
    function Clock(h, m) {
    }
    Clock.prototype.setTime = function (d) {
        this.currentTime = d;
    };
    return Clock;
})();
var newClock = (function () {
    function newClock(h, m) {
    }
    return newClock;
})();
var cs = newClock;
var myClock = new cs(7, 30);
var square = {};
square.color = "blue";
square.sideLength = 10;
square.penWidth = 5.0;
function createCounter() {
    var counter = function (start) { };
    counter.interval = 123;
    counter.reset = function () { };
    return counter;
}
var c = createCounter();
c(10);
c.reset();
console.log(c.interval);
c.interval = 5.0;
console.log(c.interval);
var Greeter = (function () {
    function Greeter(message) {
        this.greeting = message;
    }
    Greeter.prototype.greet = function () {
        return "Hello, " + this.greeting;
    };
    return Greeter;
})();
var greeter = new Greeter("world");
console.log(greeter.greet);
var Animal = (function () {
    function Animal(name) {
        this.name = name;
    }
    Animal.prototype.move = function (meters) {
        if (meters === void 0) { meters = 0; }
        console.log(this.name + " moved " + meters + "m.");
    };
    return Animal;
})();
var Snake = (function (_super) {
    __extends(Snake, _super);
    function Snake(name) {
        _super.call(this, name);
    }
    Snake.prototype.move = function (meters) {
        if (meters === void 0) { meters = 5; }
        console.log("Slitering...");
        _super.prototype.move.call(this, meters);
    };
    return Snake;
})(Animal);
var Horse = (function (_super) {
    __extends(Horse, _super);
    function Horse(name) {
        _super.call(this, name);
    }
    Horse.prototype.move = function (meters) {
        if (meters === void 0) { meters = 45; }
        console.log("Galloping...");
        _super.prototype.move.call(this, meters);
    };
    return Horse;
})(Animal);
var sam = new Snake("Sammy the python");
var tom = new Horse("tommy the piano");
sam.move();
tom.move(34);
var Animal2 = (function () {
    function Animal2(theName) {
        this.name = theName;
    }
    Animal2.prototype.move = function (meters) {
        console.log(this.name + " moved " + meters + "m.");
    };
    return Animal2;
})();
var Animal3 = (function () {
    function Animal3(theName) {
        this.name = theName;
    }
    return Animal3;
})();
var Rhino = (function (_super) {
    __extends(Rhino, _super);
    function Rhino() {
        _super.call(this, "Rhino");
    }
    return Rhino;
})(Animal3);
var Employee = (function () {
    function Employee(theName) {
        this.name = theName;
    }
    return Employee;
})();
var animal = new Animal3("Goat");
var rhino = new Rhino();
var employee = new Employee("Bob");
animal = rhino;
var Animal4 = (function () {
    function Animal4(name) {
        this.name = name;
    }
    Animal4.prototype.print = function () { console.log(this.name); };
    return Animal4;
})();
(new Animal4("Rabbit")).print();
var pass = "noob";
var Employee1 = (function () {
    function Employee1() {
    }
    Object.defineProperty(Employee1.prototype, "fullName", {
        get: function () { return this._fullName; },
        set: function (newName) {
            if (pass && pass == "noob") {
                this._fullName = newName;
            }
            else {
                alert("intruder intruder !!!");
            }
        },
        enumerable: true,
        configurable: true
    });
    return Employee1;
})();
var employee1 = new Employee1();
employee1.fullName = "Bob Smith";
if (employee1.fullName) {
    console.log(employee1.fullName);
}
var Grid = (function () {
    function Grid(scale) {
        this.scale = scale;
    }
    Grid.prototype.calculateDistanceFromOrigin = function (point) {
        var xDist = (point.x - Grid.origin.x);
        var yDist = (point.y - Grid.origin.y);
        return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
    };
    Grid.origin = { x: 0, y: 0 };
    return Grid;
})();
var grid1 = new Grid(1.0);
var grid2 = new Grid(5.0);
console.log(grid1.calculateDistanceFromOrigin({ x: 10, y: 10 }));
console.log(grid2.calculateDistanceFromOrigin({ x: 10, y: 10 }));
