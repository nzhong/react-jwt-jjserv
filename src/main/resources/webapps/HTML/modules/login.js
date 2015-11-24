var React = require('react');

module.exports = {
    module: React.createClass({
        handleSubmit : function(e) {
            var loginUrl = '/login';
            e.preventDefault();
            var username = this.refs.username.value.trim();
            var password = this.refs.password.value.trim();
            $.ajax({
                url: loginUrl,
                contentType: 'application/json',
                dataType: 'json',
                type: 'POST',
                data: JSON.stringify({"username": username, "password": password}),
                success: function(data) {
                    // console.dir( data );
                    this.props.changeToken( data.token );
                }.bind(this),
                error: function(xhr, status, err) {
                    console.dir ( status );
                    console.dir( err );
                    // console.error( loginUrl, status, err.toString() );
                }.bind(this)
            });
            this.refs.username.value = '';
		        this.refs.password.value = '';
            return;
        },

        render: function() {
            return (
              <form className="form" onSubmit={this.handleSubmit}>
        				<p className="bucketName">
        					<input name="username" type="text"     required className="form-input" placeholder="username" ref="username" />
        				</p>
        				<p className="fileKey">
        					<input name="password" type="password" required className="form-input" placeholder="password" ref="password" />
        				</p>
        				<div className="submit">
        					<input type="submit" value="Login" id="button-blue"/>
        				</div>
        			</form>
            )
        }
    })
}

