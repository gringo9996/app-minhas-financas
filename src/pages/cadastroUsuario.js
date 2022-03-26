import { useState } from "react";
import Card from "../components/card";
import FormGroup from "../components/form-group";

export default function CadastroUsuario() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [senhaRepeticao, setSenhaRepeticao] = useState("");

  const cadastrar = () => {
    console.log({ nome, email, senha, senhaRepeticao });
  };

  return (
    <div className="container">
      <Card title="Cadastro de Usuário">
        <div className="row">
          <div className="col-lg-12">
            <div className="bs-component">
              <FormGroup label="Nome: *" htmlFor="inputNome">
                <input
                  type="text"
                  id="inputNome"
                  name="nome"
                  className="form-control"
                  onChange={(e) => setNome(e.target.value)}
                />
              </FormGroup>
              <FormGroup label="Email *" htmlFor="inputEmail">
                <input
                  type="email"
                  id="inputEmail"
                  name="email"
                  className="form-control"
                  onChange={(e) => setEmail(e.target.value)}
                />
              </FormGroup>
              <FormGroup label="Senha *" htmlFor="inputSenha">
                <input
                  type="password"
                  id="inputSenha"
                  name="senha"
                  className="form-control"
                  onChange={(e) => setSenha(e.target.value)}
                />
              </FormGroup>
              <FormGroup label="Repita a senha *" htmlFor="inputRepitaSenha">
                <input
                  type="password"
                  id="inputRepitaSenha"
                  name="repitaSenha"
                  className="form-control"
                  onChange={(e) => setSenhaRepeticao(e.target.value)}
                />
              </FormGroup>
              <button
                type="button"
                className="btn btn-success"
                onClick={cadastrar}
                style={{ marginRight: 5 }}
              >
                Salvar
              </button>
              <button type="button" className="btn btn-danger">
                Cancelar
              </button>
            </div>
          </div>
        </div>
      </Card>
    </div>
  );
}
