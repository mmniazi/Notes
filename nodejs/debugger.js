// Node.js includes a full-featured out-of-process debugging utility accessible via a simple TCP-based protocol and built-in debugging client. To use it, start Node.js with the debug argument followed by the path to the script to debug; a prompt will be displayed indicating successful launch of the debugger:
$ node debug myscript.js
< debugger listening on port 5858
connecting... ok
break in /home/indutny/Code/git/indutny/myscript.js:1
  1 x = 5;
  2 setTimeout(() => {
  3   debugger;
debug>
