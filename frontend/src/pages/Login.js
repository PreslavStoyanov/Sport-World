import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";

const LOGIN_URL = "http://localhost:8080/login";
//const LOGIN_URL = process.env.REACT_APP_SERVER_HOSTNAME + "/login";


function Login(props) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function sendLoginRequest() {
    const requestBody = {
      username: username,
      password: password,
    };
    fetch(LOGIN_URL, {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(requestBody),
    })
      .then((response) => response.json())
      .then((data) => {
        localStorage.setItem("Authorization", data.token);
        
      })
      .then(() => {
        if (localStorage.getItem("Authorization") !== null || localStorage.getItem("Authorization") !== "undefined") {
          
          window.location.href = "./matches";
        } else {
          alert("Wrong username or password");
          window.location.href = "./login";
        }
      });
  }

  function goToRegisterPage() {
    window.location.href = "./register";
  }

  function goToForgotPassword() {
    window.location.href = "./forgotPassword"
  }

  return (
      <div id="box">
        <form className="form" onSubmit={(response) => response.preventDefault()}>
          <br />
          <h1>Welcome Back!</h1>
          <h3>Username</h3>
          <input
            className="input"
            type="text"
            id="username"
            name="username"
            value={username}
            placeholder="Enter username..."
            onChange={(response) => setUsername(response.target.value)}
          ></input>
          <h3>Password</h3>
          <input
            className="input"
            type="password"
            id="password"
            name="password"
            value={password}
            placeholder="Enter password..."
            onChange={(response) => setPassword(response.target.value)}
          ></input>
          <br />
          <br />
          <button className="btn" onClick={sendLoginRequest}>
            Login
          </button>
          <br />
          <br />
          <hr className="bar"></hr>
          <br />
          <Link to="" onClick={goToRegisterPage}>Don't have an account?</Link>
          <br />
          <br />
          <hr className="bar"></hr>
          <br />
          <Link to="" onClick={goToForgotPassword}>Forgot password?</Link>
        </form>
      </div>
  );
}

export default Login;
