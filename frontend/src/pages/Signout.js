import React from "react";

function Signout() {
  function goToLogin() {
    localStorage.removeItem("Authorization");
    window.location.href = "./login";
  }
  function goToHome() {
    window.location.href = "./matches";
  }

  return (
    <div id="box">
      <h2>Are you sure?</h2>

      <button className="signout-btn" onClick={goToLogin}>
        Yes
      </button>
      <br />
      <button className="signout-btn" onClick={goToHome}>
        No
      </button>
    </div>
  );
}
export default Signout;
