import {bootstrap, Component, CORE_DIRECTIVES, FORM_DIRECTIVES} from 'angular2/angular2';

class Project {
    name: string;
    tasks: Task[];
}

class Task {
    id: number;
    projectId: number;
    title: string;
    description: string;
    owner: string;
    assignedTo: string;
    observers: string[];
    comments: string[];
    dueDate: Date;
}

@Component({
    selector: 'projects',
    template: `
      <input [(ng-model)]="newProject.title" placeholder="Title">
      <input type="button" (click)="addProject()" value="Add Project">
      <ul>
        <li *ng-for="#project of projects">
          <p>
            {{project.title}}
          </p>
        </li>
      </ul>`,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
class Projects {
    public projects: Project[] = [];
    public newProject: Project = new Project;
    public selectedProject: Project;
    public addProject() {
        this.projects.push(this.newProject);
        this.newProject = new Project;
    }
    public selectProject(project) {
        this.selectProject = project;
    }
}

@Component({
    selector: 'tasks',
    template: `
      <input [(ng-model)]="newTask.title" placeholder="Title">
      <input type="button" (click)="addTask()" value="Add Task">
      <ul>
        <li *ng-for="#task of tasks">
          <p>
            {{task.title}}
          </p>
        </li>
      </ul>`,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
class Tasks {
    public tasks: Task[] = [];
    public newTask: Task = new Task;
    public addTask() {
        this.tasks.push(this.newTask);
        this.newTask = new Task;
    }
}

@Component({
  selector: 'selected-task',
  template: `
    <input [(ng-model)]="task.title">
    <input [(ng-model)]="task.description">
    <input [(ng-model)]="task.owner">
    <input [(ng-model)]="task.assignedTo">
    <input [(ng-model)]="task.dueDate">
    <ul>
      <li *ng-for="#comment of task.comments">{{comment}}</li>
    </ul>
    <ul>
      <li *ng-for="#observer of task.observers">{{observer}}</li>
    </ul>`,
  directives: [CORE_DIRECTIVES, FORM_DIRECTIVES]
})
class SelectedTask {
  public task: Task;
}

@Component({
    selector: 'app',
    template: `
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-3"><projects></projects></div>
          <div class="col-md-4"><tasks></tasks></div>
          <div class="col-md-5"><selected-task></selected-task></div>
        </div>
      </div>`,
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, SelectedTask, Tasks, Projects]
})
class App {

}

bootstrap(App);
