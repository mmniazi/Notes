/* The first parameter of angular.module defines the name of the module.
 The second parameter is an array that declares the module dependencies
 that our module uses.*/
var myApp = angular.module('myApp', []);

myApp.controller('myController1', ['$scope', function ($scope) {
    $scope.greeting = 'Bismillah Alrehman Alrahim!';
    $scope.double = function (value) {
        return value * 2;
    };
}]);

/* As accessing every variable using "." is better so we are converting the code to use this
 * and then in html we would use myController as myCont to access the scope variables and functions
 * by myCont.myFunction()
 */


myApp.controller('myController2', function () {
    this.greeting = 'Bismillah Alrehman Alrahim!';
    this.double = function (value) {
        return value * 2;
    };
});


/* Services: we create services using factories using .factory() */
myApp.factory('messages', function () {
    var messages = {};

    messages.list = [];

    messages.add = function (message) {
        messages.list.push({id: messages.list.length, text: message});
    };

    return messages;
});

myApp.controller('ListCtrl', function (messages) {
    this.messages = messages.list;
});

myApp.controller('PostCtrl', function (messages) {

    this.newMessage = 'Hello World!';

    this.addMessage = function (message) {
        messages.add(message);
        self.newMessage = '';
    };
});


myApp.controller('ExampleController', ['$scope', function ($scope) {
    $scope.master = {};

    $scope.update = function (user) {
        $scope.master = angular.copy(user);
    };

    $scope.reset = function (form) {
        if (form) {
            form.$setPristine();
            form.$setUntouched();
        }
        $scope.user = angular.copy($scope.master);
    };

    $scope.reset();
}]);

