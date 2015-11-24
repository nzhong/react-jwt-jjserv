var React = require('react');
var ReactDOM = require('react-dom');

var router = require('./router');

var App = React.createClass({
    getInitialState() {
        return {
            token: '',
            route: window.location.hash.substr(1)
        }
    },
    hashLocationChanged: function() {
        this.setState({
            route: window.location.hash.substr(1)
        });
    },
    componentDidMount: function() {
        window.addEventListener('hashchange', this.hashLocationChanged);
    },

    changeRoute: function( newHash ) {
        if(history.pushState) {
          history.pushState(null, null, '#'+newHash);
        }
        else {
          location.hash = '#'+newHash;
        }
        this.setState(
          {route: newHash}
        );
    },

    changeToken: function( newToken ) {
      $.ajaxSetup({
        headers: { 'X-Authentication-Token': newToken }
      });
      this.setState(
        {token: newToken}
      );
      this.changeRoute( '/welcome' );
    },

    render : function() {
        var Child = router.route( this.state );
        return (
          <div>
            <h1>App</h1>
            <ul>
              <li><a href="#/about">About</a></li>
              <li><a href="#/welcome">Welcome</a></li>
            </ul>
            <Child changeRoute={this.changeRoute} changeToken={this.changeToken} state={this.state} />
          </div>
        )
    }
});
ReactDOM.render( <App />, document.querySelector('#main'));

