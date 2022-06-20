import { useState } from "react";
import { Link } from "react-router-dom";
const CHANGE_PASSWORD_API = "http://localhost:8080/users/forgotPassword";
//const CHANGE_PASSWORD_API = process.env.REACT_APP_SERVER_HOSTNAME + "/users/forgotPassword";

function ForgotPassword() {
  const [username, setUsername] = useState("");

  async function sentChangeRequest() {
    const requestBody = {
      username: username,
    };

    fetch(CHANGE_PASSWORD_API, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(requestBody),
    }).then((response) => response.json());
    //setUsername("");
    goToResetPassword();
    alert("Check your email");
  }

  function goToResetPassword() {
    window.location.href = "../resetPassword";
  }

  function goBack() {
    window.location.href = "../login";
  }

  return (
    <div id="box">
      <h1>Type your username</h1>
      <form className="form" onSubmit={(response) => response.preventDefault()}>
        <h3>Username</h3>
        <input
          className="input"
          type="text"
          name="username"
          placeholder="Enter username..."
          onChange={(response) => {
            setUsername(response.target.value);
          }}
          
        />
        <br />
        <br />
        <br />
        <button className="btn" onClick={sentChangeRequest}>
          Change
        </button>
        <br />
        <br />
        <hr className="bar"></hr>
        <br />
        <Link to="" onClick={goBack}>
          Go back
        </Link>
        <br />
      </form>
    </div>
  );
}
export default ForgotPassword;
