import { useEffect, useState } from "react";
import CommentsService from "../../services/CommentsService";
import Comment from "./Comment";
import CommentForm from "./CommentForm";

const CREATE_COMMENT = "http://localhost:8080/comments";
//const CREATE_COMMENT = process.env.REACT_APP_SERVER_HOSTNAME + "/comments";

const Comments = () => {
  const [comments, setComments] = useState([]);
  const [content, setContent] = useState("");
  const matchID = window.location.pathname.charAt(window.location.pathname.length - 1);

  function createComment(content) {
    const requestBody = {
      content: content,
      matchID: matchID,
    };
  
    fetch(CREATE_COMMENT, {
      headers: {
        Authorization: localStorage.getItem("Authorization"),
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(requestBody),
    }).then((response) => response.json());
    setContent("");
    refreshPage()
  }

  function refreshPage() {
    window.location.reload(true);
  }

  useEffect(() => {
    CommentsService.listCommentsByMach().then((response) => {
      setComments(response.data);
    });
  }, []);

  return (
      <div className="comments">
        <div className="comments-title">Comments</div>
        <div className="comment-form-title">Write comment</div>
        <CommentForm submitLabel="Write" handleSubmit={createComment} />
        <div className="comments-container">
          {comments.map((comment) => (
            <Comment key={comment.id} comment={comment} />
          ))}
        </div>
      </div>
  );
};

export default Comments;
