import React from "react";
import "bootswatch/dist/flatly/bootstrap.css";
import "./custom.css";
import Routes from "./appRoutes";
import Navbar from "./components/navbar";

function App() {
  return (
    <>
      <Navbar />
      <div className="container">
        <Routes />
      </div>
    </>
  );
}

export default App;
