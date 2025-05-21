document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.produto').forEach(produto => {
    produto.addEventListener('click', () => {
      const nome = produto.getAttribute('data-nome');
      const categoria = produto.getAttribute('data-categoria');
      const precoAtual = produto.getAttribute('data-preco-atual');
      const precoOriginal = produto.getAttribute('data-preco-original');
      const imagem = produto.getAttribute('data-imagem');
      const reviews = produto.getAttribute('data-reviews');
      const estrelas = parseFloat(produto.getAttribute('data-estrelas'));

      // Preenche modal
      document.getElementById('modalTitulo').textContent = nome;
      document.getElementById('modalCategoria').textContent = categoria;
      document.getElementById('modalPrecoAtual').textContent = precoAtual;
      document.getElementById('modalPrecoOriginal').textContent = precoOriginal;
      document.getElementById('modalImagem').src = imagem;
      document.getElementById('modalReviews').textContent = `(${reviews})`;

      const estrelasEl = document.getElementById('modalEstrelas');
      estrelasEl.innerHTML = '';
      for (let i = 1; i <= 5; i++) {
        if (i <= estrelas) {
          estrelasEl.innerHTML += '<i class="fas fa-star"></i>';
        } else if (i - estrelas < 1) {
          estrelasEl.innerHTML += '<i class="fas fa-star-half-alt"></i>';
        } else {
          estrelasEl.innerHTML += '<i class="far fa-star"></i>';
        }
      }

      document.getElementById('modalProduto').classList.add('modal--ativo');
    });
  });

  // Fecha o modal
  const fecharBtn = document.getElementById('fecharModal');
  if (fecharBtn) {
    fecharBtn.addEventListener('click', () => {
      document.getElementById('modalProduto').classList.remove('modal--ativo');
    });
  }
});
