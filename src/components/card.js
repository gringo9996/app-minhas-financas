import React from "react";

export default function Card(props) {
  const { title, children } = props;

  return (
    <div className="card mb-3">
      <h3 className="card-header">{title}</h3>
      <div className="card-body">{children}</div>
    </div>
  );
}
