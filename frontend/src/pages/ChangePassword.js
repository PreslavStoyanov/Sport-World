//import axios from "axios";
import { useState } from "react";
//import { Link } from "react-router-dom";

const CHANGE_PASSWORD_API = "http://localhost:8080/users/changePassword";
//const CHANGE_PASSWORD_API = process.env.REACT_APP_SERVER_HOSTNAME + "/users/changePassword";

function ChangePassword(response) {
  const [username, setUsername] = useState("");
  const [oldPassword, setOldPassword] = useState("");
  const [newPassowrd, setNewPassword] = useState("");

  async function sentChangeRequest() {
    const requestBody = {
      username: username,
      oldPassword: oldPassword,
      newPassowrd: newPassowrd,
    };

    fetch(CHANGE_PASSWORD_API, {
      headers: {
        Authorization: localStorage.getItem("Authorization"),
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(requestBody),
    }).then((response) => response.json());
    setUsername("");
    setOldPassword("");
    setNewPassword("");

    window.location.reload(true);
    window.location.href = "./matches";
  }

  // async function handleSubmit(event) {
  //   event.preventDefault();
  //   try {
  //     await axios.post(CHANGE_PASSWORD_API, {
  //       username: username,
  //       oldPassword: oldPassword,
  //       newPassowrd: newPassowrd,
  //     });
  //     alert("Password changed successfully");
  //     setUsername("");
  //     setOldPassword("");
  //     setNewPassword("");

  //     window.location.reload(true);
  //     window.location.href = "./matches";
  //   } catch (err) {
  //     alert("Wrong credentials");
  //   }
  // }

  return (
    <div id="box">
      <h1>Type your credentials and new password</h1>
      <form className="form" onSubmit={(response) => response.preventDefault()}>
        <h3>Username</h3>
        <input
          className="input"
          type="text"
          name="username"
          placeholder="Enter username..."
          onChange={(response) => setUsername(response.target.value)}
        />
        <br />
        <h3>Old password</h3>
        <input
          className="input"
          type="password"
          name="oldPassword"
          placeholder="Enter old password..."
          onChange={(response) => setOldPassword(response.target.value)}
        />
        <br />
        <h3>New password</h3>
        <input
          className="input"
          type="password"
          name="newPassword"
          placeholder="Enter new password..."
          onChange={(response) => setNewPassword(response.target.value)}
        />
        <br />
        <br />
        <button className="btn" onClick={sentChangeRequest}>
          Change
        </button>
        <br />
      </form>
    </div>
  );
}
export default ChangePassword;
