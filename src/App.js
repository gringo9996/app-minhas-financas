import React from "react";
import Login from "./pages/login";
import "bootswatch/dist/flatly/bootstrap.css";
import "./custom.css";
import CadastroUsuario from "./pages/cadastroUsuario";

class App extends React.Component {
  render() {
    return (
      <div>
        <CadastroUsuario />
      </div>
    );
  }
}

export default App;
