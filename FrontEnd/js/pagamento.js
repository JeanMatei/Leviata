// Script para a página de pagamento (pagamento.js)

document.addEventListener("DOMContentLoaded", function() {
    const resumoContainer = document.getElementById("resumo-pedido-container");
    const totalElement = document.getElementById("resumo-pedido-total");
    const carrinho = JSON.parse(localStorage.getItem("carrinhoOfertas")) || [];

    let subtotal = 0;

    // Função para formatar preço
    function formatPrice(value) {
        if (typeof value !== "number") {
            value = 0;
        }
        return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
    }

    // Limpa o container do resumo (exceto o total que será atualizado)
    // Remove todos os filhos exceto o último (o parágrafo do total)
    while (resumoContainer.children.length > 1) {
        resumoContainer.removeChild(resumoContainer.firstChild);
    }

    if (carrinho.length === 0) {
        const emptyMessage = document.createElement("p");
        emptyMessage.textContent = "Nenhum item no carrinho para exibir.";
        emptyMessage.style.textAlign = "center";
        emptyMessage.style.color = "#666";
        // Insere a mensagem antes do elemento total
        resumoContainer.insertBefore(emptyMessage, totalElement);
        totalElement.textContent = `Total: ${formatPrice(0)}`;
    } else {
        carrinho.forEach(item => {
            const itemElement = document.createElement("p");
            // Exibe nome e preço formatado
            itemElement.textContent = `${item.nome} - ${formatPrice(item.preco)}`;
            // Insere o item antes do elemento total
            resumoContainer.insertBefore(itemElement, totalElement);

            subtotal += item.preco * item.quantidade; // Assume quantidade 1 para ofertas
        });

        // Atualiza o total
        totalElement.textContent = `Total: ${formatPrice(subtotal)}`;
    }

    // Lógica para alternar formulários de pagamento (Cartão/Pix)
    const radiosPagamento = document.querySelectorAll("input[name=\"pagamento\"]");
    const formCartao = document.getElementById("form-cartao");
    const formPix = document.getElementById("form-pix");

    radiosPagamento.forEach(radio => {
        radio.addEventListener("change", function() {
            if (this.value === "cartao") {
                formCartao.classList.add("ativo");
                formPix.classList.remove("ativo");
                formPix.style.display = "none"; // Garante que PIX fique oculto
                formCartao.style.display = "block"; // Garante que Cartão fique visível
            } else if (this.value === "pix") {
                formCartao.classList.remove("ativo");
                formPix.classList.add("ativo");
                formCartao.style.display = "none"; // Garante que Cartão fique oculto
                formPix.style.display = "block"; // Garante que PIX fique visível
                // Aqui você adicionaria a lógica para gerar o QR Code e o código Pix
                gerarPixInfo(subtotal); // Chama função para gerar dados do Pix
            }
        });
    });

    // Força a exibição inicial correta baseada no radio selecionado (Cartão por padrão)
    if (document.querySelector("input[name=\"pagamento\"][value=\"cartao\"]").checked) {
        formPix.style.display = "none";
        formCartao.style.display = "block";
    } else {
        formCartao.style.display = "none";
        formPix.style.display = "block";
         gerarPixInfo(subtotal); // Gera PIX se PIX estiver selecionado inicialmente
    }

    // Função placeholder para gerar informações do Pix (requer biblioteca externa para QR)
    function gerarPixInfo(valor) {
        const codigoPixElement = document.getElementById("codigo-pix");
        const qrCanvas = document.getElementById("qr-pix");
        const chavePixExemplo = "exemplo-chave-pix-12345"; // Substituir pela chave real
        const nomeBeneficiario = "Nome Loja Exemplo";
        const cidadeBeneficiario = "SAO PAULO";
        
        // Formata valor para o padrão Pix (ex: 119.80)
        const valorFormatadoPix = valor.toFixed(2);

        // Monta um payload Pix simplificado (pode precisar de ajustes para validação)
        // Este é um exemplo MUITO básico, um payload real é mais complexo
        const payload = `00020126330014BR.GOV.BCB.PIX0111${chavePixExemplo}520400005303986540${valorFormatadoPix}5802BR5919${nomeBeneficiario}6009${cidadeBeneficiario}62070503***6304`; // Adicionar checksum CRC16 no final (ex: ABCD)
        const payloadCompleto = payload + "ABCD"; // Placeholder para checksum

        codigoPixElement.value = payloadCompleto; // Exibe o código gerado

        // Tenta gerar QR Code se a biblioteca estiver disponível
        if (typeof QRCode !== 'undefined') {
            QRCode.toCanvas(qrCanvas, payloadCompleto, function (error) {
                if (error) console.error("Erro ao gerar QR Code:", error);
                else console.log('QR Code Pix gerado!');
            });
        } else {
            console.warn("Biblioteca QRCode não encontrada. Não foi possível gerar o QR Code.");
            // Você pode exibir uma mensagem alternativa no lugar do canvas
            const ctx = qrCanvas.getContext('2d');
            ctx.clearRect(0, 0, qrCanvas.width, qrCanvas.height);
            ctx.font = '12px Arial';
            ctx.textAlign = 'center';
            ctx.fillText('QR Code indisponível', qrCanvas.width / 2, qrCanvas.height / 2);
        }
    }

    // Adicionar listener para o botão de finalizar Pix (apenas exemplo)
    const btnFinalizarPix = document.getElementById("finalizar-pix");
    if(btnFinalizarPix) {
        btnFinalizarPix.addEventListener("click", () => {
            alert("Pagamento Pix confirmado (simulação)!");
            // Limpar carrinho após pagamento?
            // localStorage.removeItem("carrinhoOfertas");
            // Redirecionar para página de sucesso?
            // window.location.href = 'pedido_confirmado.html';
        });
    }
    
    // Adicionar listener para o submit do formulário de cartão (apenas exemplo)
    if(formCartao) {
        formCartao.addEventListener("submit", (event) => {
            event.preventDefault(); // Impede envio real do formulário
            alert("Pagamento com Cartão confirmado (simulação)!");
             // Limpar carrinho após pagamento?
            // localStorage.removeItem("carrinhoOfertas");
            // Redirecionar para página de sucesso?
            // window.location.href = 'pedido_confirmado.html';
        });
    }

});

