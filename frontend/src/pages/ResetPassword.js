import { useState } from "react";
const CHANGE_PASSWORD_API = "http://localhost:8080/users/resetPassword";
//const CHANGE_PASSWORD_API = process.env.REACT_APP_SERVER_HOSTNAME + "/users/resetPassword";


function ResetPassword() {
  const [token, setToken] = useState("");
  const [newPassword, setNewPassword] = useState("");

  async function sentChangeRequest() {
    const requestBody = {
      token: token,
      newPassword: newPassword,
    };

    fetch(CHANGE_PASSWORD_API, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(requestBody),
    }).then((response) => response.json());
    setToken("");
    setNewPassword("");
    goToLogin();
    alert("Success");
  }

  function goToLogin() {
    window.location.href = "../login";
  }

  return (
    <div id="box">
      <h1>Type your data</h1>
      <form className="form" onSubmit={(response) => response.preventDefault()}>
        <h3>Unique code</h3>
        <input
          className="input"
          type="text"
          name="code"
          placeholder="Enter code..."
          onChange={(response) => setToken(response.target.value)}
        />
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
        <br />
        <button className="btn" onClick={sentChangeRequest}>
          Change
        </button>
        <br />

        <br />
      </form>
    </div>
  );
}
export default ResetPassword;
