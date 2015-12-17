var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") return Reflect.decorate(decorators, target, key, desc);
    switch (arguments.length) {
        case 2: return decorators.reduceRight(function(o, d) { return (d && d(o)) || o; }, target);
        case 3: return decorators.reduceRight(function(o, d) { return (d && d(target, key)), void 0; }, void 0);
        case 4: return decorators.reduceRight(function(o, d) { return (d && d(target, key, o)) || o; }, desc);
    }
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var angular2_1 = require('angular2/angular2');
var Project = (function () {
    function Project() {
    }
    return Project;
})();
var Task = (function () {
    function Task() {
    }
    return Task;
})();
var Projects = (function () {
    function Projects() {
        this.projects = [];
        this.newProject = new Project;
    }
    Projects.prototype.addProject = function () {
        this.projects.push(this.newProject);
        this.newProject = new Project;
    };
    Projects.prototype.selectProject = function (project) {
        this.selectProject = project;
    };
    Projects = __decorate([
        angular2_1.Component({
            selector: 'projects',
            template: "\n      <input [(ng-model)]=\"newProject.title\" placeholder=\"Title\">\n      <input type=\"button\" (click)=\"addProject()\" value=\"Add Project\">\n      <ul>\n        <li *ng-for=\"#project of projects\">\n          <p>\n            {{project.title}}\n          </p>\n        </li>\n      </ul>",
            directives: [angular2_1.CORE_DIRECTIVES, angular2_1.FORM_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], Projects);
    return Projects;
})();
var Tasks = (function () {
    function Tasks() {
        this.tasks = [];
        this.newTask = new Task;
    }
    Tasks.prototype.addTask = function () {
        this.tasks.push(this.newTask);
        this.newTask = new Task;
    };
    Tasks = __decorate([
        angular2_1.Component({
            selector: 'tasks',
            template: "\n      <input [(ng-model)]=\"newTask.title\" placeholder=\"Title\">\n      <input type=\"button\" (click)=\"addTask()\" value=\"Add Task\">\n      <ul>\n        <li *ng-for=\"#task of tasks\">\n          <p>\n            {{task.title}}\n          </p>\n        </li>\n      </ul>",
            directives: [angular2_1.CORE_DIRECTIVES, angular2_1.FORM_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], Tasks);
    return Tasks;
})();
var SelectedTask = (function () {
    function SelectedTask() {
    }
    SelectedTask = __decorate([
        angular2_1.Component({
            selector: 'selected-task',
            template: "\n    <input [(ng-model)]=\"task.title\">\n    <input [(ng-model)]=\"task.description\">\n    <input [(ng-model)]=\"task.owner\">\n    <input [(ng-model)]=\"task.assignedTo\">\n    <input [(ng-model)]=\"task.dueDate\">\n    <ul>\n      <li *ng-for=\"#comment of task.comments\">{{comment}}</li>\n    </ul>\n    <ul>\n      <li *ng-for=\"#observer of task.observers\">{{observer}}</li>\n    </ul>",
            directives: [angular2_1.CORE_DIRECTIVES, angular2_1.FORM_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], SelectedTask);
    return SelectedTask;
})();
var App = (function () {
    function App() {
    }
    App = __decorate([
        angular2_1.Component({
            selector: 'app',
            template: "\n      <div class=\"container-fluid\">\n        <div class=\"row\">\n          <div class=\"col-md-3\"><projects></projects></div>\n          <div class=\"col-md-4\"><tasks></tasks></div>\n          <div class=\"col-md-5\"><selected-task></selected-task></div>\n        </div>\n      </div>",
            directives: [angular2_1.CORE_DIRECTIVES, angular2_1.FORM_DIRECTIVES, SelectedTask, Tasks, Projects]
        }), 
        __metadata('design:paramtypes', [])
    ], App);
    return App;
})();
angular2_1.bootstrap(App);
