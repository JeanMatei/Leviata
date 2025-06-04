document.addEventListener("DOMContentLoaded", function () {
  const resumoContainer = document.getElementById("resumo-pedido-container");
  const totalElement = document.getElementById("resumo-pedido-total");
  const carrinho = JSON.parse(localStorage.getItem("carrinhoOfertas")) || [];

  let subtotal = 0;

  function formatPrice(value) {
    if (typeof value !== "number") {
      value = 0;
    }
    return value.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
    });
  }

  while (resumoContainer.children.length > 1) {
    resumoContainer.removeChild(resumoContainer.firstChild);
  }

  if (carrinho.length === 0) {
    const emptyMessage = document.createElement("p");
    emptyMessage.textContent = "Nenhum item no carrinho para exibir.";
    emptyMessage.style.textAlign = "center";
    emptyMessage.style.color = "#666";

    resumoContainer.insertBefore(emptyMessage, totalElement);
    totalElement.textContent = `Total: ${formatPrice(0)}`;
  } else {
    carrinho.forEach((item) => {
      const itemElement = document.createElement("p");

      itemElement.textContent = `${item.nome} - ${formatPrice(item.preco)}`;

      resumoContainer.insertBefore(itemElement, totalElement);

      subtotal += item.preco * item.quantidade;
    });

    totalElement.textContent = `Total: ${formatPrice(subtotal)}`;
  }

  const radiosPagamento = document.querySelectorAll('input[name="pagamento"]');
  const formCartao = document.getElementById("form-cartao");
  const formPix = document.getElementById("form-pix");

  radiosPagamento.forEach((radio) => {
    radio.addEventListener("change", function () {
      if (this.value === "cartao") {
        formCartao.classList.add("ativo");
        formPix.classList.remove("ativo");
        formPix.style.display = "none";
        formCartao.style.display = "block";
      } else if (this.value === "pix") {
        formCartao.classList.remove("ativo");
        formPix.classList.add("ativo");
        formCartao.style.display = "none";
        formPix.style.display = "block";
        gerarPixInfo(subtotal);
      }
    });
  });

  if (
    document.querySelector('input[name="pagamento"][value="cartao"]').checked
  ) {
    formPix.style.display = "none";
    formCartao.style.display = "block";
  } else {
    formCartao.style.display = "none";
    formPix.style.display = "block";
    gerarPixInfo(subtotal);
  }

  function gerarPixInfo(valor) {
    const codigoPixElement = document.getElementById("codigo-pix");
    const qrCanvas = document.getElementById("qr-pix");
    const chavePixExemplo = "exemplo-chave-pix-12345";
    const nomeBeneficiario = "Nome Loja Exemplo";
    const cidadeBeneficiario = "SAO PAULO";

    const valorFormatadoPix = valor.toFixed(2);

    const payload = `00020126330014BR.GOV.BCB.PIX0111${chavePixExemplo}520400005303986540${valorFormatadoPix}5802BR5919${nomeBeneficiario}6009${cidadeBeneficiario}62070503***6304`; // Adicionar checksum CRC16 no final (ex: ABCD)
    const payloadCompleto = payload + "ABCD";

    codigoPixElement.value = payloadCompleto;

    if (typeof QRCode !== "undefined") {
      QRCode.toCanvas(qrCanvas, payloadCompleto, function (error) {
        if (error) console.error("Erro ao gerar QR Code:", error);
        else console.log("QR Code Pix gerado!");
      });
    } else {
      console.warn(
        "Biblioteca QRCode não encontrada. Não foi possível gerar o QR Code."
      );

      const ctx = qrCanvas.getContext("2d");
      ctx.clearRect(0, 0, qrCanvas.width, qrCanvas.height);
      ctx.font = "12px Arial";
      ctx.textAlign = "center";
      ctx.fillText(
        "QR Code indisponível",
        qrCanvas.width / 2,
        qrCanvas.height / 2
      );
    }
  }

  const btnFinalizarPix = document.getElementById("finalizar-pix");
  if (btnFinalizarPix) {
    btnFinalizarPix.addEventListener("click", () => {
      alert("Pagamento Pix confirmado (simulação)!");
    });
  }

  if (formCartao) {
    formCartao.addEventListener("submit", (event) => {
      event.preventDefault();
      alert("Pagamento com Cartão confirmado (simulação)!");
    });
  }
});
