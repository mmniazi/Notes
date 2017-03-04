/* Basic Types */
// You can skip or mention types
let isDone = false;
let height: number = 6;
// Array types -- both are same
const list: number[] = [1, 2, 3, 4];
const newList: Array<number> = [1, 2, 3, 4];

// Using latest ecmascript 6 string feature
console.log(`${list} || ${newList}`);

// Enum are well same as in java way of represnting some predefined sets of values
// Like enum of fruits
enum Fruit {
  Banana, Apple, PineApple,
}
let banana: Fruit = Fruit.Banana;
// You can also use numerical value to get enum
const apple = Fruit[1];
console.log(apple);

// We can opt-out type checking by using "any" type
let whatever: any = 3;
console.log(whatever);
whatever = "noob string";
console.log(whatever);

// We also have void type
function someFunc(): void {
  // return nothing
}

/* Interfaces */
// And here comes bessy I mean **interfaces**
// But one extra perk typescript interfaces has is that you dont have
// to explicitly implement an interface to make object usable if it has
// required properties then it is good to go
interface noobChecker {
  noobness: string
}
function printNoobness(player: noobChecker) {
  console.log(player.noobness);
}
const aPlyr = {games: 10, noobness: "imba"};
printNoobness(aPlyr);

// Optional properties
interface SquareConfig {
  color?: string;
  width?: number;
}
function createSquare(config: SquareConfig): { color: string; area: number } {
  const newSquare = {color: "white", area: 100};
  if (config.color) {
    newSquare.color = config.color;
  }
  if (config.width) {
    newSquare.area = config.width * config.width;
  }
  return newSquare;
}
const mySquare = createSquare({color: "black"});
console.log(mySquare);

// Function interface
interface SearchFunc {
  (source: string, subString: string): boolean;
}
// As a side note name of parameters are irrelevant only type matters
let mySearch: SearchFunc = function (src: string, sub: string): boolean {
  return src.search(sub) != -1;
};

// Array types
// There are two types of supported index string and number
interface StringArray {
  [index: number]: string;
}
let myArray: StringArray = ["bob the builder", "Ben 10"];

// Implementing an interface -- To Make Sure that Class meets certain criteria
interface ClockInterface {
  currentTime: Date;
  setTime(d: Date);
}
class Clock implements ClockInterface {
  currentTime: Date; // if you skip this or setTime there will a kaa boom or an error
  setTime(d: Date) {
    this.currentTime = d;
  }
  constructor(h: number, m: number) { }
}

// Difference between static/instance side of class
interface ClockStatic {
  new (hour: number, minute: number);
}
class newClock {
  currentTime: Date;
  constructor(h: number, m: number) { }
}
const cs: ClockStatic = newClock;
let myClock = new cs(7, 30);

// Like classes, interfaces can extend each other and yes it can extend many
interface Shape {
  color: string;
}
interface PenStroke {
  penWidth: number;
}
interface Square extends Shape, PenStroke {
  sideLength: number;
}
const square = <Square>{};
square.color = "blue";
square.sideLength = 10;
square.penWidth = 5.0;

// Hybrid Types -- due to dynamic nature of js we can have an object that acts as
// combination of multiple types such as function numbers etc and guess what we can
// use interfaces to create such type objects
interface Counter {
  (start: number): string;
  interval: number;
  reset(): void;
}
function createCounter(): Counter {
  const counter = <Counter> function (start: number) {
  };
  counter.interval = 123;
  counter.reset = function() { };
  return counter;
}
const c: Counter = createCounter();
c(10);
c.reset();
console.log(c.interval);
c.interval = 5.0;
console.log(c.interval);

/* Classes */
// Say Aoa to classes :P
class Greeter {
  greeting: string;
  constructor(message: string) {
    this.greeting = message;
  }
  greet() {
    return "Hello, " + this.greeting;
  }
}
const greeter = new Greeter("world");
console.log(greeter.greet);

// Inheritance
class Animal {
  name: string;
  constructor(name: string) { this.name = name; }
  move(meters: number = 0) {
    console.log(this.name + " moved " + meters + "m.")
  }
}
class Snake extends Animal {
  move(meters = 5) {
    console.log("Slitering...");
    super.move(meters);
  }
}
class Horse extends Animal {
  constructor(name: string) { super(name); }
  move(meters = 45) {
    console.log("Galloping...");
    super.move(meters);
  }
}
const sam = new Snake("Sammy the python");
const tom: Animal = new Horse("tommy the piano");
sam.move();
tom.move(34);

// TS is public by default like java you can explicitly make members private
class Animal2 {
  private name: string;
  constructor(theName: string) { this.name = theName; }
  move(meters: number) {
    console.log(this.name + " moved " + meters + "m.");
  }
}

// As we saw before its the shape of object that determines its type but in case of private members its not the same even if you have same shape as the type having private member you cant interchange them because private member of that class will be different from private member of your class although with same shape. But you can extend a class to get the same private member so not they are interchangeable.
class Animal3 {
  private name: string;
  constructor(theName: string) { this.name = theName; }
}

class Rhino extends Animal3 {
  constructor() { super("Rhino"); }
}

class Employee {
  private name: string;
  constructor(theName: string) { this.name = theName; }
}

let animal = new Animal3("Goat");
const rhino = new Rhino();
let employee = new Employee("Bob");

animal = rhino;
// animal = employee; error: Animal and Employee are not compatible

// Parameter properties -- Instead of first defining class properties and then initalizing them in constructor you can define them in constructor using private and public keyword
class Animal4 {
  constructor(public name: string) { }
  print() { console.log(this.name); }
}
(new Animal4("Rabbit")).print();

// Getters and Setters
const pass = "noob";
class Employee1 {
  private _fullName: string;
  get fullName(): string { return this._fullName }
  set fullName(newName: string) {
    if (pass && pass == "noob") {
      this._fullName = newName;
    } else {
      alert("intruder intruder !!!")
    }
  }
}
const employee1 = new Employee1();
employee1.fullName = "Bob Smith";
if (employee1.fullName) {
  console.log(employee1.fullName);
}

// Static properties -- simply use static like you use in java/C# and you can access it by class Name
class Grid {
    static origin = {x: 0, y: 0};
    calculateDistanceFromOrigin(point: {x: number; y: number;}) {
        const xDist = (point.x - Grid.origin.x);
        const yDist = (point.y - Grid.origin.y);
        return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
    }
    constructor (public scale: number) { }
}
const grid1 = new Grid(1.0);  // 1x scale
const grid2 = new Grid(5.0);  // 5x scale
console.log(grid1.calculateDistanceFromOrigin({x: 10, y: 10}));
console.log(grid2.calculateDistanceFromOrigin({x: 10, y: 10}));

// Generics
function identity<T>(arg: T): T {
  return arg;
}

let output = identity<string>("myString");
let output1 = identity("myString"); // inferred

function loggingIdentity<T>(arg: T[]): T[] {
  console.log(arg.length);  // Array has a .length, so no more error
  return arg;
}

let myIdentity: <T>(arg: T) => T = identity;

// Generic interface
interface GenericIdentityFn {
  <T>(arg: T): T;
}

let myIdentity1: GenericIdentityFn = identity;

class GenericNumber<T> {
  zeroValue: T;
  add: (x: T, y: T) => T;
}

let myGenericNumber = new GenericNumber<number>();
myGenericNumber.zeroValue = 0;
myGenericNumber.add = function(x, y) { return x + y; };

interface Lengthwise {
  length: number;
}

function loggingIdentity2<T extends Lengthwise>(arg: T): T {
  console.log(arg.length);  // Now we know it has a .length property, so no more error
  return arg;
}

loggingIdentity2({length: 10, value: 3});

function copyFields<T extends U, U>(target: T, source: U): T {
  for (let id in source) {
    target[id] = source[id];
  }
  return target;
}

let x = { a: 1, b: 2, c: 3, d: 4 };

copyFields(x, { b: 10, d: 20 }); // okay
// copyFields(x, { Q: 90 });  // error: property 'Q' isn't declared in 'x'.

// Union types
/**
 * Takes a string and adds "padding" to the left.
 * If 'padding' is a string, then 'padding' is appended to the left side.
 * If 'padding' is a number, then that number of spaces is added to the left side.
 */
function padLeft(value: string, padding: string | number) {
  // ...
}

// Intersection type
interface customType { b: number }
function interDemo(value: {a} & customType) {}
let testVar = {a : 1, b: 2};
interDemo(testVar);

// Type alias
type Aaaa = string
