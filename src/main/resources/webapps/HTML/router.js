
var login   = require('./modules/login');
var about   = require('./modules/about');
var welcome = require('./modules/welcome');

var Login   = login.module;
var About   = about.module;
var Welcome = welcome.module;

module.exports = {
    route: function( state ) {
        if ( state.token==null || state.token=='' ) {
          return Login;
        }

        var result;
        switch (state.route) {
          case '/about':   result = About;   break;
          case '/welcome': result = Welcome; break;
          default:         result = Welcome;
        }
        return result;
    }
}

