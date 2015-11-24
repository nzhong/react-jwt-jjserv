README
======

This repo is based on the following two repos:

* <a href="https://github.com/nzhong/jj-serv">jj-serv</a>: A small embedded java server, which can serve static content, and REST APIs. It's based on Jetty + Jersey, thus the name JJ.
* <a href="https://github.com/nzhong/react-webpack-minimal">react-webpack-minimal</a>: A simple starter project for a webpack build of ReactJS.


What is this?
---------------------------------------------------------
This project adds the following items to the above two base repo:

* (non rackt/react-router) Routing in ReactJS by hash (#)
* login form
* JWT token authentication (JWT=Json Web Token).


How to run?
---------------------------------------------------------
**warning**: just 'mvn clean package' will not work here. I have not integrated webpack/grunt workflow, so there are some manual steps needed.

* ```cd src/main/resources/webapps/HTML/```
* ```npm init ```
* ```npm install webpack -g ```
* ```npm install --save-dev babel babel-core babel-preset-react babel-loader ```
* ```npm install --save-dev react react-dom ```
* ```webpack ```
<br />
This will generate bundle.js in the src/main/resources/webapps/HTML/ directory, which test.html will use.

* ```cd ../../../../../```
* ```mvn clean package```
* ```java -jar target/jj-server-1.0-SNAPSHOT.jar```

* Point browser to:
* http://localhost:6060/static/test.html  
