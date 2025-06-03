
document.addEventListener("DOMContentLoaded", function() {
    const carrinho = JSON.parse(localStorage.getItem("carrinhoOfertas")) || [];
    const listaProdutosContainer = document.getElementById("lista-produtos-resumo");
    const subtotalElement = document.getElementById("resumo-subtotal");
    const totalElement = document.getElementById("resumo-total");

    let subtotal = 0;

    function formatPrice(value) {
        if (typeof value !== "number") {
            value = 0;
        }
        return value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
    }

    listaProdutosContainer.innerHTML = '';

    if (carrinho.length === 0) {
   
        listaProdutosContainer.innerHTML = '<p style="text-align: center; color: #666;">Seu carrinho de ofertas está vazio.</p>';
        subtotalElement.textContent = formatPrice(0);
        totalElement.textContent = formatPrice(0);
        return; 
    }

    
    carrinho.forEach((item) => {
        const linhaProduto = document.createElement('div');
        linhaProduto.classList.add('linha'); 

        const spanInfo = document.createElement('span');
        spanInfo.innerHTML = `
            <img src="${item.imagem}" alt="${item.nome}" class="resumo-produto-imagem">
            ${item.nome}
        `;

        const spanPreco = document.createElement('span');
        spanPreco.textContent = formatPrice(item.preco);

        linhaProduto.appendChild(spanInfo);
        linhaProduto.appendChild(spanPreco);

        listaProdutosContainer.appendChild(linhaProduto);

        subtotal += item.preco * item.quantidade; 
    });

    subtotalElement.textContent = formatPrice(subtotal);
    totalElement.textContent = formatPrice(subtotal); 

});

