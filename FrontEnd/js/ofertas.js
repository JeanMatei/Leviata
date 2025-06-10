function parsePrice(priceString) {
    if (!priceString) return 0;
    
    return parseFloat(priceString.replace('R$', '').replace('.', '').replace(',', '.').trim());
}


function adicionarOfertaAoCarrinho(event, produtoId) {
    
    event.preventDefault();

    const productCard = event.target.closest('.produto');
    if (!productCard) {
        console.error('Card do produto não encontrado!');
        return;
    }

    const nomeProduto = productCard.querySelector('.produto__nome').textContent.trim();
    const precoAtualTexto = productCard.querySelector('.preco__atual').textContent.trim();
    const imagemSrc = productCard.querySelector('.produto__imagem img').getAttribute('src');

    const precoAtual = parsePrice(precoAtualTexto);

    const produto = {
        id: produtoId || nomeProduto.toLowerCase().replace(/\s+/g, '-'), 
        nome: nomeProduto,
        imagem: imagemSrc,
        preco: precoAtual,
        quantidade: 1 
    };

    let carrinho = JSON.parse(localStorage.getItem('carrinhoOfertas')) || [];

    carrinho.push(produto);

    localStorage.setItem('carrinhoOfertas', JSON.stringify(carrinho));

    console.log(`${produto.nome} adicionado ao carrinho de ofertas.`);

    window.location.href = 'carrinho.html';
}

document.addEventListener('DOMContentLoaded', () => {

});

