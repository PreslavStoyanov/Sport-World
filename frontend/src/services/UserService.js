import axios from "axios";

const GET_USER = "http://localhost:8080/users/current";
//const GET_USER = process.env.REACT_APP_SERVER_HOSTNAME + "/users/current";


class UserService {
    
    getUser() {
        return axios.get(GET_USER, {
          headers: {
            Authorization: localStorage.getItem("Authorization"),
          },
        });
      }

} export default new UserService;