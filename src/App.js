import React from "react";
import "bootswatch/dist/flatly/bootstrap.css";
import "./custom.css";
import Routes from "./appRoutes";

class App extends React.Component {
  render() {
    return <Routes />;
  }
}

export default App;
