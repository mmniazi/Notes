var gulp = require('gulp');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var cleanCSS = require('gulp-clean-css');

var input = 'scss/styles.scss';
var output = 'css/';

var sassOptions = {
	errLogToConsole: true,
	outputStyle: 'expanded'
};

gulp.task('sass', function () {
	return gulp
		.src(input)
		.pipe(sass(sassOptions).on('error', sass.logError))
		.pipe(autoprefixer({
			browsers: ['last 2 version', 'ff > 20', '> 1%', 'ie 9', 'ie 10']
		}))
		.pipe(cleanCSS({
			compatibility: 'ie8'
		}))
		.pipe(gulp.dest(output));
});

gulp.task('watch', function () {
	return gulp
		// Watch the input folder for change,
		// and run `sass` task when something happens
		.watch(input, ['sass'])
		// When there is a change,
		// log a message in the console
		.on('change', function (event) {
			console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
		});
});

gulp.task('default', ['sass', 'watch']);
