var gulp = require('gulp');
var gls = require('gulp-live-server'); // for live reloading
// load plugins save use from writing require for each plugin we use you can also just require all and use jshin instead of $.jshint.
// Also you need to manually install them from npm before using
var $ = require('gulp-load-plugins')({
	lazy: true
});

// Gulp task runs by using 'gulp jshint'
gulp.task('jshint', function () {
	// Creating a gulp data source
	return gulp.src(['app.js'])
		// Piping data to jshint
		.pipe($.jshint())
		// Piping data to jshint-stylish reporter
		.pipe($.jshint.reporter('jshint-stylish', {
			verbose: true
		}));
});

// You can inject css/js links/scripts tags into index.html also but as express does that so I am skipping that
// Similarly we can create a task such that when ever we add a bower component it automatically get wired up in index.html. Check out wiredep and inject. And there are ton other things you can do using gulp

// Watch files for changes if these js files changes run jshint task. Good if you are doing front end only otherwise check nodemon
// I am commenting it out as I am using gulp live server
// gulp.task('watch', function () {
// 	gulp.watch(['app.js', 'routes/*', 'views/*'], ['jshint', 'live-reload']);
// });

// Gulp Live server provides live reloading and restarting node.
gulp.task('serve', function () {
	// Run your script as a server
	var server = gls.new('./bin/www');
	server.start();

	//use gulp.watch to trigger server actions(notify, start or stop)
	gulp.watch(['views/*.jade', 'routes/*.js'], function (file) {
		server.notify.apply(server, [file]);
	});

	// Restart my server
	gulp.watch('./bin/www', function () {
		server.start.bind(server)();
	});
});

// Default task runs when you do 'gulp'
// Here we have added jshint as dependency so it also runs on 'gulp'
gulp.task('default', ['jshint', 'serve'], function () {
	console.log("Running Gulp");
});

/*
Installation Scripts:
npm install --save-dev gulp-load-plugins
npm install gulp-jshint --save
npm install jshint-stylish --save
npm install gulp-live-server --save // love this tried dozen but none worked :P
npm install connect-livereload --save
*/
