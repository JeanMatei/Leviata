// JavaScript para controlar o modal de produto

document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('modalProduto');
    const fecharModal = document.getElementById('fecharModal');
    
    // Dados de exemplo dos produtos (você pode substituir pela sua fonte de dados)
    const produtos = {
        'jogo1': {
            nome: 'Jogo X',
            categoria: 'RPG',
            imagem: '/FrontEnd/img/jogo1.jpg',
            precoAtual: 'R$ 192,00',
            precoOriginal: 'R$ 240,00',
            estrelas: 4.5,
            reviews: 32,
            descricao: 'Um jogo incrível de RPG com aventuras épicas e mundos fantásticos para explorar.'
        },
        'jogo2': {
            nome: 'Adventure Quest',
            categoria: 'Aventura',
            imagem: '/FrontEnd/img/jogo2.jpg',
            precoAtual: 'R$ 149,90',
            precoOriginal: 'R$ 199,90',
            estrelas: 4,
            reviews: 28,
            descricao: 'Uma aventura emocionante com puzzles desafiadores e uma história envolvente.'
        }
        // Adicione mais produtos conforme necessário
    };

    // Função para abrir o modal
    function abrirModal(produtoId) {
        const produto = produtos[produtoId];
        if (!produto) return;

        // Preencher dados do modal
        document.getElementById('modalTitulo').textContent = produto.nome;
        document.getElementById('modalCategoria').textContent = produto.categoria;
        document.getElementById('modalImagem').src = produto.imagem;
        document.getElementById('modalImagem').alt = produto.nome;
        document.getElementById('modalPrecoAtual').textContent = produto.precoAtual;
        document.getElementById('modalPrecoOriginal').textContent = produto.precoOriginal;
        document.getElementById('modalReviews').textContent = `(${produto.reviews})`;
        
        // Atualizar descrição
        const descricaoElement = document.querySelector('.produto-detalhe__descricao p');
        if (descricaoElement) {
            descricaoElement.textContent = produto.descricao;
        }

        // Atualizar estrelas
        atualizarEstrelas(produto.estrelas);

        // Mostrar modal
        modal.classList.add('ativo');
        document.body.style.overflow = 'hidden'; // Impede scroll da página
    }

    // Função para fechar o modal
    function fecharModalFunc() {
        modal.classList.remove('ativo');
        document.body.style.overflow = 'auto'; // Restaura scroll da página
    }

    // Função para atualizar as estrelas
    function atualizarEstrelas(rating) {
        const estrelasContainer = document.getElementById('modalEstrelas');
        estrelasContainer.innerHTML = '';
        
        const estrelasInteiras = Math.floor(rating);
        const temMeiaEstrela = rating % 1 !== 0;
        
        // Adicionar estrelas cheias
        for (let i = 0; i < estrelasInteiras; i++) {
            estrelasContainer.innerHTML += '<i class="fas fa-star"></i>';
        }
        
        // Adicionar meia estrela se necessário
        if (temMeiaEstrela) {
            estrelasContainer.innerHTML += '<i class="fas fa-star-half-alt"></i>';
        }
        
        // Adicionar estrelas vazias para completar 5
        const estrelasVazias = 5 - Math.ceil(rating);
        for (let i = 0; i < estrelasVazias; i++) {
            estrelasContainer.innerHTML += '<i class="far fa-star"></i>';
        }
    }

    // Event listeners
    fecharModal.addEventListener('click', fecharModalFunc);

    // Fechar modal clicando fora dele
    modal.addEventListener('click', function(e) {
        if (e.target === modal) {
            fecharModalFunc();
        }
    });

    // Fechar modal com tecla ESC
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && modal.classList.contains('ativo')) {
            fecharModalFunc();
        }
    });

    // Controles de quantidade
    const diminuirBtn = document.querySelector('.diminuir-quantidade');
    const aumentarBtn = document.querySelector('.aumentar-quantidade');
    const quantidadeSpan = document.querySelector('.produto-detalhe__quantidade-valor');

    diminuirBtn.addEventListener('click', function() {
        let quantidade = parseInt(quantidadeSpan.textContent);
        if (quantidade > 1) {
            quantidadeSpan.textContent = quantidade - 1;
        }
    });

    aumentarBtn.addEventListener('click', function() {
        let quantidade = parseInt(quantidadeSpan.textContent);
        quantidadeSpan.textContent = quantidade + 1;
    });

    // Botão adicionar ao carrinho
    const botaoComprar = document.querySelector('.produto-detalhe__comprar');
    botaoComprar.addEventListener('click', function() {
        const quantidade = parseInt(quantidadeSpan.textContent);
        const nomeProduto = document.getElementById('modalTitulo').textContent;
        
        alert(`${nomeProduto} adicionado ao carrinho!\nQuantidade: ${quantidade}`);
        // Aqui você pode implementar a lógica real do carrinho
        
        fecharModalFunc();
    });

    // Função para ser chamada pelos cards de produto
    window.abrirModalProduto = abrirModal;
});
