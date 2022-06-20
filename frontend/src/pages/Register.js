import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

const REGISTER_USER = "http://localhost:8080/register";
//const REGISTER_USER = process.env.REACT_APP_SERVER_HOSTNAME + "/register";

function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  async function handleSubmit(response) {
    response.preventDefault();
    try {
      await axios.post(REGISTER_USER, {
        username: username,
        password: password,
        email: email
      });
      alert("User Registration Successfully");
      setUsername("");
      setPassword("");
      setEmail("");

      window.location.reload(true);
      window.location.href = "./login";
    } catch (err) {
      alert(err);
    }
  }

  function goToLoginPage() {
    window.location.href = "./login";
  }

  return (
    <div id="box">
      <form className="form" onSubmit={handleSubmit}>
        <br></br>
        <h1>Welcome!</h1>
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
        <h3>Password</h3>
        <input
          className="input"
          type="password"
          name="password"
          placeholder="Enter password..."
          onChange={(response) => {
            setPassword(response.target.value);
          }}
        />
        <br />
        <h3>Email</h3>
        <input
          className="input"
          type="text"
          name="email"
          placeholder="Enter email..."
          onChange={(response) => {
            setEmail(response.target.value);
          }}
        />
        <br />
        <br />
        <button className="btn" type="submit">
          Register
        </button>
        <br />
        <br />
        <hr className="bar"></hr>
        <br />
        <Link to="" onClick={goToLoginPage}>Have an account?</Link>
      </form>
    </div>
  );
} export default Register;
