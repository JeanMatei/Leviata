document.addEventListener("DOMContentLoaded", function () {
  
  function validarCPF(cpf) {
    return /^\d{11}$/.test(cpf);
  }


  document.getElementById("confirmar").addEventListener("click", function () {
    const nome = document.getElementById("nomePix").value.trim();
    const cpf = document.getElementById("cpfPix").value.trim();

    if (!nome || !cpf) {
      alert("Por favor, preencha Nome e CPF para o pagamento via PIX.");
      return;
    }

    if (!validarCPF(cpf)) {
      alert("CPF inválido! Digite 11 números.");
      return;
    }

    alert(`Pagamento via PIX confirmado!\nNome: ${nome}\nCPF: ${cpf}`);
  });
});
