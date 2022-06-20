import { Route, Switch, Redirect } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import Logo from "./components/logo/Logo";
import Match from "./pages/Match";
import Bar from "./components/bar/Bar";
import Signout from "./pages/Signout";
import User from "./pages/User";
import ChangePassword from "./pages/ChangePassword";
import Comments from "./components/comments/Comments";
import LiveMatches from "./components/scores/LiveMatches";
import ForgotPassword from "./pages/ForgotPassword";
import ResetPassword from "./pages/ResetPassword";

function App() {
  return (
    <div>
      <Switch>
        <Route exact path="/">
          <Redirect to="/login" />
        </Route>

        <Route exact path="/matches">
          <Logo />
          <Bar />
          <Home />
        </Route>

        <Route path="/register">
          <Register />
        </Route>

        <Route path="/login">
          <Login />
        </Route>

        <Route path={"/matches/:id"}>
          <Logo />
          <Bar />
          <div id="box">
            <Match />
            <hr className="bar" />
            <Comments />
          </div>
        </Route>

        <Route path={"/live"}>
          <Logo />
          <Bar />
          <LiveMatches />
        </Route>

        <Route path="/users">
          <Logo />
          <Bar />
          <User />
        </Route>

        <Route path="/changePassword">
          <Logo />
          <Bar />
          <ChangePassword />
        </Route>

        <Route path="/forgotPassword">
          <ForgotPassword />
        </Route>

        <Route path="/resetPassword">
          <ResetPassword />
        </Route>

        <Route path="/signout">
          <Signout />
        </Route>

        <Route path="*">
          <div>
            <h2>404 Page Not Found</h2>
          </div>
        </Route>
      </Switch>
    </div>
  );
}

export default App;
