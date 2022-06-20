const Comment = ({ comment }) => {
  const formatDate = (dateString) => {
    const options = { year: "numeric", month: "long", day: "numeric" };
    return new Date(dateString).toLocaleDateString(undefined, options);
  };

  return (
    <div className="comment">
      <div className="comment-image-container">
        <img alt="user-icon" src="/user-icon.png" />
      </div>
      <div className="comment-right-part">
        <div className="comment-content">
          <div className="comment-author">{comment.author}</div>
          <div>{formatDate(comment.creationDate)}</div>
        </div>
        <div className="comment-text">{comment.content}</div>
      </div>
    </div>
  );
};

export default Comment;
