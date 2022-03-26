import React from "react";

export default function FormGroup(props) {
  const { label, children, htmlFor } = props;

  return (
    <div className="form-group mb-3">
      <label htmlFor={htmlFor}>{label}</label>

      {children}
    </div>
  );
}
