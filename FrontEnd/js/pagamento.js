document.getElementById("metodo").addEventListener("change", function () {
  const metodo = this.value;
  const pixDiv = document.getElementById("pix");
  const cartaoDiv = document.getElementById("cartao");

  if (metodo === "pix") {
    pixDiv.style.display = "block";
    cartaoDiv.style.display = "none";
  } else if (metodo === "cartao") {
    pixDiv.style.display = "none";
    cartaoDiv.style.display = "block";
  }
});

document.getElementById("confirmar").addEventListener("click", function () {
  const metodo = document.getElementById("metodo").value;

  if (metodo === "pix") {
    const nome = document.getElementById("nomePix").value.trim();
    const cpf = document.getElementById("cpfPix").value.trim();

    if (!nome || !cpf) {
      alert("Por favor, preencha Nome e CPF para o pagamento via PIX.");
      return;
    }

    alert(`Pagamento via PIX confirmado!\nNome: ${nome}\nCPF: ${cpf}`);

  } else if (metodo === "cartao") {
    alert("Pagamento via Cartão confirmado!");
  } else {
    alert("Selecione um método de pagamento.");
  }
});
