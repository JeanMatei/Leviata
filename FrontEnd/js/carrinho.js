document.addEventListener('DOMContentLoaded', function() {
  const modal = document.getElementById('modalProduto');
  const fecharModal = document.getElementById('fecharModal');

  const produtos = {
    'jogo1': {
      id: 'jogo1',
      nome: 'Jogo X',
      categoria: 'RPG',
      imagem: '/FrontEnd/img/jogo1.jpg',
      precoAtual: 192.00,
      precoOriginal: 240.00,
      estrelas: 4.5,
      reviews: 32,
      descricao: 'Um jogo incrível de RPG com aventuras épicas e mundos fantásticos para explorar.'
    },
    'jogo2': {
      id: 'jogo2',
      nome: 'Adventure Quest',
      categoria: 'Aventura',
      imagem: '/FrontEnd/img/jogo2.jpg',
      precoAtual: 149.90,
      precoOriginal: 199.90,
      estrelas: 4,
      reviews: 28,
      descricao: 'Uma aventura emocionante com puzzles desafiadores e uma história envolvente.'
    }
  };

  // Função para abrir o modal e carregar os dados do produto
  function abrirModal(produtoId) {
    const produto = produtos[produtoId];
    if (!produto) return;

    document.getElementById('modalTitulo').textContent = produto.nome;
    document.getElementById('modalCategoria').textContent = produto.categoria;
    const img = document.getElementById('modalImagem');
    img.src = produto.imagem;
    img.alt = produto.nome;
    document.getElementById('modalPrecoAtual').textContent = formatarPreco(produto.precoAtual);
    document.getElementById('modalPrecoOriginal').textContent = formatarPreco(produto.precoOriginal);
    document.getElementById('modalReviews').textContent = `(${produto.reviews})`;

    const descricaoElement = document.querySelector('.produto-detalhe__descricao p');
    if (descricaoElement) descricaoElement.textContent = produto.descricao;

    atualizarEstrelas(produto.estrelas);

    // Ao abrir modal, verificar se o produto já está no carrinho para ajustar a quantidade
    const carrinho = obterCarrinho();
    const itemNoCarrinho = carrinho.find(item => item.id === produtoId);
    const quantidade = itemNoCarrinho ? itemNoCarrinho.quantidade : 1;
    document.querySelector('.produto-detalhe__quantidade-valor').textContent = quantidade;

    modal.classList.add('ativo');
    document.body.style.overflow = 'hidden';

    // Salvar o id do produto aberto no modal para usar ao adicionar ao carrinho
    modal.dataset.produtoId = produtoId;
  }

  function fecharModalFunc() {
    modal.classList.remove('ativo');
    document.body.style.overflow = 'auto';
    delete modal.dataset.produtoId;
  }

  // Atualizar estrelas no modal
  function atualizarEstrelas(rating) {
    const estrelasContainer = document.getElementById('modalEstrelas');
    estrelasContainer.innerHTML = '';

    const estrelasInteiras = Math.floor(rating);
    const temMeiaEstrela = rating % 1 !== 0;

    for (let i = 0; i < estrelasInteiras; i++) {
      estrelasContainer.innerHTML += '<i class="fas fa-star"></i>';
    }

    if (temMeiaEstrela) {
      estrelasContainer.innerHTML += '<i class="fas fa-star-half-alt"></i>';
    }

    const estrelasVazias = 5 - Math.ceil(rating);
    for (let i = 0; i < estrelasVazias; i++) {
      estrelasContainer.innerHTML += '<i class="far fa-star"></i>';
    }
  }

  // Formatação para preço em R$
  function formatarPreco(valor) {
    return `R$ ${valor.toFixed(2).replace('.', ',')}`;
  }

  // Pega o carrinho do localStorage (ou cria vazio)
  function obterCarrinho() {
    const carrinhoJSON = localStorage.getItem('carrinho');
    return carrinhoJSON ? JSON.parse(carrinhoJSON) : [];
  }

  // Salva o carrinho no localStorage
  function salvarCarrinho(carrinho) {
    localStorage.setItem('carrinho', JSON.stringify(carrinho));
  }

  // Adiciona ou atualiza produto no carrinho
  function adicionarAoCarrinho(produtoId, quantidade) {
    const produto = produtos[produtoId];
    if (!produto) return false;

    const carrinho = obterCarrinho();

    const itemIndex = carrinho.findIndex(item => item.id === produtoId);

    if (itemIndex > -1) {
      // Atualiza a quantidade (substitui pelo valor novo, não soma)
      carrinho[itemIndex].quantidade = quantidade;
    } else {
      // Novo item no carrinho
      carrinho.push({
        id: produto.id,
        nome: produto.nome,
        preco: produto.precoAtual,
        quantidade: quantidade,
        imagem: produto.imagem
      });
    }

    salvarCarrinho(carrinho);
    atualizarResumoPedido();
    return true;
  }

  // Atualiza o resumo do pedido na página com os dados do localStorage
  function atualizarResumoPedido() {
    const carrinho = obterCarrinho();
    const containerItens = document.getElementById('itens-carrinho');
    const subtotalSpan = document.getElementById('subtotal');
    const totalSpan = document.getElementById('total');

    containerItens.innerHTML = '';

    if (carrinho.length === 0) {
      containerItens.innerHTML = '<p>Seu carrinho está vazio.</p>';
      subtotalSpan.textContent = formatarPreco(0);
      totalSpan.textContent = formatarPreco(0);
      return;
    }

    let subtotal = 0;

    carrinho.forEach(item => {
      const linha = document.createElement('div');
      linha.classList.add('linha');

      const img = document.createElement('img');
      img.src = item.imagem;
      img.alt = item.nome;
      img.classList.add('imagem-jogo-carrinho');

      const nome = document.createElement('span');
      nome.textContent = item.nome;

      const quantidade = document.createElement('span');
      quantidade.textContent = `x${item.quantidade}`;

      const preco = document.createElement('span');
      const precoTotalItem = item.preco * item.quantidade;
      preco.textContent = formatarPreco(precoTotalItem);

      linha.appendChild(img);
      linha.appendChild(nome);
      linha.appendChild(quantidade);
      linha.appendChild(preco);

      containerItens.appendChild(linha);

      subtotal += precoTotalItem;
    });

    subtotalSpan.textContent = formatarPreco(subtotal);
    totalSpan.textContent = formatarPreco(subtotal); // pode incluir frete, desconto, etc
  }

  // Event listeners modal e controles de quantidade
  fecharModal.addEventListener('click', fecharModalFunc);

  modal.addEventListener('click', function(e) {
    if (e.target === modal) fecharModalFunc();
  });

  document.addEventListener('keydown', function(e) {
    if (e.key === 'Escape' && modal.classList.contains('ativo')) fecharModalFunc();
  });

  const diminuirBtn = document.querySelector('.diminuir-quantidade');
  const aumentarBtn = document.querySelector('.aumentar-quantidade');
  const quantidadeSpan = document.querySelector('.produto-detalhe__quantidade-valor');

  diminuirBtn.addEventListener('click', function() {
    let qtd = parseInt(quantidadeSpan.textContent);
    if (qtd > 1) quantidadeSpan.textContent = qtd - 1;
  });

  aumentarBtn.addEventListener('click', function() {
    let qtd = parseInt(quantidadeSpan.textContent);
    quantidadeSpan.textContent = qtd + 1;
  });

  // Botão comprar adiciona ao carrinho e fecha modal
  const botaoComprar = document.querySelector('.produto-detalhe__comprar');
  botaoComprar.addEventListener('click', function() {
    const qtd = parseInt(quantidadeSpan.textContent);
    const produtoId = modal.dataset.produtoId;
    if (!produtoId) return;

    const sucesso = adicionarAoCarrinho(produtoId, qtd);
    if (sucesso) {
      alert(`${produtos[produtoId].nome} adicionado ao carrinho!\nQuantidade: ${qtd}`);
      fecharModalFunc();
    } else {
      alert('Erro ao adicionar ao carrinho.');
    }
  });

  // Inicializa o resumo do pedido na página
  atualizarResumoPedido();

  // Expõe função global para abrir modal
  window.abrirModalProduto = abrirModal;
});
