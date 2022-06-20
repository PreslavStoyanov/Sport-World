import axios from "axios";
const LIST_COMMENTS_BY_MATCH = "http://localhost:8080/comments" + window.location.pathname;

// const LIST_COMMENTS_BY_MATCH = process.env.REACT_APP_SERVER_HOSTNAME + "/comments" + window.location.pathname;




// if (envVariableExists(LIST_COMMENTS_BY_MATCH)) {
//   LIST_COMMENTS_BY_MATCH = getEnv("SERVER_ORIGIN") + "/comments"
// }
//const SERVER_ORIGIN = getEnv("SERVER_ORIGIN") == "" ? "http://localhost:8080" : getEnv("SERVER_ORIGIN")

class CommentsService {

    
  listCommentsByMach() {
    return axios.get(LIST_COMMENTS_BY_MATCH, {
      headers: {
        Authorization: localStorage.getItem("Authorization"),
      },
    });
  }
}
export default new CommentsService();
