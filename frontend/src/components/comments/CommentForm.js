import { useState } from "react";

const CommentForm = ({ handleSubmit, submitLabel }) => {
  const [content, setContent] = useState("");
  const isTextareaDisabled = content.length === 0;
  const onSubmit = (event) => {
    event.preventDefault();
    handleSubmit(content);
    setContent("");
  };

  return (
    <form onSubmit={onSubmit}>
      <textarea
        className="comment-form-textarea"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <button className="comment-form-button" disabled={isTextareaDisabled}>
        {submitLabel}
      </button>
    </form>
  );
};

export default CommentForm;
