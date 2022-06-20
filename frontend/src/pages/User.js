import React from "react";
import { Link } from "react-router-dom";
import UserService from "../services/UserService";

class User extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        user: ""
      };
    }

    componentDidMount () {
        UserService.getUser().then((response) => {
            this.setState({
              user: response.data
            });
          });
    }

    goToChangePasswordMenu() {
      window.location.href = "../changePassword";
    }
  

    render() {
        return (
          <div>
            <br />
        <br />
        <h1 className="header">Profile</h1>
        <hr className="live-match-bar" />
        <br />
            <div id="box">
              <div className="header">
                <div>{this.state.user.username}</div>
                <div>{this.state.user.email}</div>
                <br />
              <Link to="" onClick={this.goToChangePasswordMenu}>Change password</Link>
            
              </div>
            </div>
          </div>
        );
      }
} export default User;