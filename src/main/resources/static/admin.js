const senhaCorreta = "admin123"; // senha que libera o conteúdo

  function verificarSenha() {
    const senha = document.getElementById("senha").value;
    const erro = document.getElementById("error");

    if (senha === senhaCorreta) {
      document.getElementById("modal").style.display = "none";
      document.getElementById("conteudo").style.display = "block";
    } else {
      erro.style.display = "block";
    }
  }