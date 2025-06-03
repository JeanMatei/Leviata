// Script para capturar dados do formulário do carrinho e redirecionar

document.addEventListener("DOMContentLoaded", function() {
    const finalizarCompraButton = document.getElementById("finalizar-compra");

    if (finalizarCompraButton) {
        finalizarCompraButton.addEventListener("click", function() {
          
            const nome = document.getElementById("nome")?.value || "";
            const sobrenome = document.getElementById("sobrenome")?.value || "";
            const cpf = document.getElementById("cpf")?.value || "";
            const telefone = document.getElementById("telefone")?.value || "";
            const detalhes = document.getElementById("detalhes")?.value || "";
            const salvarDados = document.getElementById("salvar")?.checked || false;

         
            const dadosCadastro = {
                nome: nome,
                sobrenome: sobrenome,
                cpf: cpf,
                telefone: telefone,
                detalhesPagamento: detalhes,
                salvarDados: salvarDados
            };

      
            localStorage.setItem("dadosCadastroCarrinho", JSON.stringify(dadosCadastro));

         
            window.location.href = "pagamento.html"; 
        });
    }
});

