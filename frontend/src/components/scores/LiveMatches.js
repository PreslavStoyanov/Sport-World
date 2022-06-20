import React, { Component } from "react";
let axios = require("axios").default;
const LIVE_SCORE_API_URL = "http://localhost:8080/matches/live";
//const LIVE_SCORE_API_URL = process.env.REACT_APP_SERVER_HOSTNAME + "/matches/live";

export default class LiveMatches extends Component {
  constructor(props) {
    super(props);
    this.state = {
      games: [],
    };
  }

  componentDidMount() {
    this.getLiveGames().then((response) => {
      this.setState({
        games: response.data.events,
      });
    });
  }

  getLiveGames() {
    return axios.get(LIVE_SCORE_API_URL, {
      headers: {
        "Authorization": localStorage.getItem("Authorization")
      },
    });
  }

  render() {
    return (
      <div>
        <br />
        <br />
        <h1 className="header">Live Matches</h1>
        <hr className="live-match-bar" />
        <br />
        <div className="match-box">
          {this.state.games &&
            this.state.games.map((game, ind) => (
              <div className="live-match" ckey={game.id}>
                {game.tournament.name} - {game.tournament.category.name}                <br />
                {game.homeTeam.shortName} {game.homeScore.current} -
                {game.awayScore.current} {game.awayTeam.shortName} <br />
                <br />
              </div>
            ))}
        </div>
      </div>
    );
  }
}
