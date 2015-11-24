var React = require('react');

const Welcome = React.createClass({
    componentDidMount: function() {
      var apiUrl = '/test';
      $.ajax({
	      url: apiUrl,
	      dataType: 'json',
	      cache: false,
	      success: function(data) {
	        console.dir( data );
          if ( data!=null && data.name!=null ) {
            alert( 'welcome '+data.name );
          }
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(this.props.url, status, err.toString());
	      }.bind(this)
	    });
    },

    render: function() {
        return (
            <div>Welcome </div>
        )
    }
});
module.exports = {
    module: Welcome
}

