import { Link } from "react-router-dom";
import bar from "./bar.module.css";
import jwt_decode from "jwt-decode";

function Bar() {
  var token = localStorage.getItem("Authorization");
  var decodedToken = token === null ? "" : jwt_decode(token);

  function goToHome() {
    window.location.href = "../matches";
  }

  function goToLive() {
    window.location.href = "../live";
  }

  function goToUserProfile() {
    window.location.href = "../users";
  }

  function goToSignOutPage() {
    window.location.href = "../signout";
  }

  function goToCreateMatch() {
    window.location.href = "../createMatch";
  }

  return (
    <header className={bar.header}>
      <nav>
        <ul>
          <li>
            <Link to="" onClick={goToHome}>
              Home
            </Link>
          </li>
          <li>
            <Link to="" onClick={goToLive}>
              Live
            </Link>
          </li>
          {
          decodedToken.role_id === 2 && (
            <li>
              <Link to="" onClick={goToCreateMatch}>
                Create match
              </Link>
            </li>
          )}
          <li>
            <Link to="" onClick={goToUserProfile}>
              Profile
            </Link>
          </li>
          <li>
            <Link to="" onClick={goToSignOutPage}>
              Sign out
            </Link>
          </li>
        </ul>
      </nav>
    </header>
  );
}
export default Bar;
