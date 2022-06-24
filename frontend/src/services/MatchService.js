import axios from "axios";
const LIST_MATCHES = "http://localhost:8080/matches?page=0&pageSize=40";
const GET_MATCH = "http://localhost:8080" + window.location.pathname;

//const LIST_MATCHES = process.env.REACT_APP_SERVER_HOSTNAME +  "/matches?page=0&pageSize=10";
//const GET_MATCH = process.env.REACT_APP_SERVER_HOSTNAME + window.location.pathname;

class MatchService {
  listMaches() {
    return axios.get(LIST_MATCHES, {
      headers: {
        "Authorization": localStorage.getItem("Authorization")
      }
    });
  }

  getMatch() {
    return axios.get(GET_MATCH, {
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    });
  }

  
}
export default new MatchService();