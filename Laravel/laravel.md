#Laravel
Laravel understanding based on https://laravel.com/docs/master/quickstart-intermediate

##Migrations
Migrations are used to create database tables. Database of specific name must be created beforehand and its name should be set in .env file.
Following command can be used to generate migration:
`php artisan make:migration create_tasks_table --create=tasks`

`up` function creates and table and `down` drops it.

Finally use `php artisan migrate` for generating tables.

##Models
Laravel uses Eloquent ORM. Models can be created using `php artisan make:model Task`. Model maps to single database tables which is plural of model name. `$fillable` can  be used for mass assigning.

##Eloquent Relationships
We can link models using Eloquent relations:
```
public function tasks()
{
    return $this->hasMany(Task::class);
}
```

##Routing
We define routes in `routes.php`. Routes are mapped to Controller functions. We can used closures, but they are not recommended.
`Route::get('/tasks', 'TaskController@index');`

##Authentication
Authentication is built into laravel. only small configuration like `php artisan make:auth` and redirection paths etc are needed.

##Blade
`@extend for extending a layout`
`@section for defining a section, @endsection`
`@yield for using a section`
`@include for inserting a template(blade file)`

##Controller
Controllers define route handling logic. Request is injected into controller function and response is returned. Validation may be carried out in controller. If validation is failed user is redirected back with `$error` variable in session.

##Dependency Injection
###Repository
Repositories are defined to hold all data access logic. So all data is accessed through common channel. We may use following inside `TaskRepository`:
```
return $user->tasks()
    ->orderBy('created_at', 'asc')
    ->get();
```
Repositories are injected automatically in controller like angularjs.

##Route Model Binding
`Route::delete('/task/{task}', 'TaskController@destroy');
`

`public function destroy(Request $request, Task $task)`

Since the `{task}` variable in our route matches the `$task` variable defined in our controller method, Laravel's implicit model binding will automatically inject the corresponding Task model instance.

##Policies
Laravel uses "policies" to organize authorization logic into simple, small classes. Typically, each policy corresponds to a model.
`php artisan make:policy TaskPolicy`

```
public function destroy(User $user, Task $task)
{
 return $user->id === $task->user_id;
}
```
And in controller
` $this->authorize('destroy', $task);`
