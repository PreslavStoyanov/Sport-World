import React from "react";
import MatchService from "../services/MatchService";

class Match extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      match: "",
      league: "",
    };
  }

  componentDidMount() {
    MatchService.getMatch().then((response) => {
      this.setState({
        match: response.data,
        league: response.data.league,
      });
    });
  }

  render() {
    return (
      <div>
          <div className="header">
            <div><h2>{this.state.match.title}</h2></div>
            <hr className="bar"></hr>
            <div><h3>{this.state.league.name}</h3></div>
            <div>{this.state.match.content}</div>
          </div>
        </div>
    );
  }
}
export default Match;
