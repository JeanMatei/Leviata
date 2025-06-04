function inicializarSlider() {
  const indicadores = document.querySelectorAll(".indicador");

  if (!indicadores.length) return;

  indicadores.forEach((indicador, index) => {
    indicador.addEventListener("click", () => {
      indicadores.forEach((ind) => ind.classList.remove("indicador--ativo"));

      indicador.classList.add("indicador--ativo");
    });
  });
}

function inicializarBotoesProduto() {
  const botoesFavorito = document.querySelectorAll(".produto__favorito");
  const botoesCarrinho = document.querySelectorAll(".produto__carrinho");

  botoesFavorito.forEach((botao) => {
    botao.addEventListener("click", (e) => {
      e.preventDefault();

      botao.classList.toggle("ativo");

      console.log("Produto adicionado aos favoritos");
    });
  });

  botoesCarrinho.forEach((botao) => {
    botao.addEventListener("click", (e) => {
      e.preventDefault();

      console.log("Produto adicionado ao carrinho");
    });
  });
}

function inicializarNavegacao() {
  const botaoAnterior = document.querySelector(".navegacao__anterior");
  const botaoProximo = document.querySelector(".navegacao__proximo");

  if (botaoAnterior && botaoProximo) {
    botaoAnterior.addEventListener("click", () => {
      console.log("Navegar para produtos anteriores");
    });

    botaoProximo.addEventListener("click", () => {
      console.log("Navegar para próximos produtos");
    });
  }
}

function inicializarBotaoVerMais() {
  const botaoVerMais = document.getElementById("verMaisProdutos");

  if (!botaoVerMais) return;

  let paginaAtual = 1;
  const produtosPorPagina = 4;

  botaoVerMais.addEventListener("click", () => {
    const icone = botaoVerMais.querySelector("i");
    const carregando = document.getElementById("carregando");

    if (icone) icone.style.display = "none";
    if (carregando) carregando.style.display = "block";

    paginaAtual++;

    setTimeout(() => {
      carregarMaisProdutos(paginaAtual, produtosPorPagina)
        .then((resultado) => {
          if (!resultado.temMaisProdutos) {
            botaoVerMais.style.display = "none";
          }

          if (carregando) carregando.style.display = "none";
          if (icone) icone.style.display = "inline-block";

          inicializarBotoesProduto();
        })
        .catch((erro) => {
          console.error("Erro ao carregar mais produtos:", erro);

          if (carregando) carregando.style.display = "none";
          if (icone) icone.style.display = "inline-block";

          mostrarNotificacao(
            "Não foi possível carregar mais produtos. Tente novamente mais tarde.",
            "erro"
          );
        });
    }, 1000);
  });
}

function carregarMaisProdutos(pagina, quantidade) {
  return new Promise((resolve, reject) => {
    fetch("todos-produtos.html")
      .then((response) => response.text())
      .then((html) => {
        const tempDiv = document.createElement("div");
        tempDiv.innerHTML = html;

        const todosProdutos = tempDiv.querySelectorAll(".produto");

        const inicio = (pagina - 1) * quantidade;
        const fim = inicio + quantidade;

        const temMaisProdutos = fim < todosProdutos.length;

        const produtosPagina = Array.from(todosProdutos).slice(inicio, fim);

        if (produtosPagina.length === 0) {
          resolve({ temMaisProdutos: false });
          return;
        }

        const gridProdutos = document.querySelector(".ofertas__grid");

        if (gridProdutos) {
          produtosPagina.forEach((produto) => {
            produto.classList.add("produto--novo");
            gridProdutos.appendChild(produto);
          });

          mostrarNotificacao(
            `${produtosPagina.length} novos produtos carregados!`
          );

          resolve({ temMaisProdutos });
        } else {
          reject(new Error("Container de produtos não encontrado"));
        }
      })
      .catch((erro) => reject(erro));
  });
}

function mostrarNotificacao(mensagem, tipo = "sucesso") {
  const notificacao = document.createElement("div");
  notificacao.className = `notificacao notificacao--${tipo}`;

  let icone = "check-circle";
  if (tipo === "erro") icone = "exclamation-circle";
  else if (tipo === "aviso") icone = "exclamation-triangle";

  notificacao.innerHTML = `
    <div class="notificacao__conteudo">
      <i class="fas fa-${icone}"></i>
      <span>${mensagem}</span>
    </div>
  `;

  notificacao.style.cssText = `
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: ${
      tipo === "sucesso"
        ? "var(--cor-destaque)"
        : tipo === "erro"
        ? "#ef4444"
        : "#f59e0b"
    };
    color: white;
    padding: 12px 20px;
    border-radius: 4px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    z-index: 1000;
    transform: translateY(100px);
    opacity: 0;
    transition: transform 0.3s, opacity 0.3s;
  `;

  const conteudo = notificacao.querySelector(".notificacao__conteudo");
  conteudo.style.cssText = `
    display: flex;
    align-items: center;
    gap: 10px;
  `;

  document.body.appendChild(notificacao);

  setTimeout(() => {
    notificacao.style.transform = "translateY(0)";
    notificacao.style.opacity = "1";
  }, 10);

  setTimeout(() => {
    notificacao.style.transform = "translateY(100px)";
    notificacao.style.opacity = "0";

    setTimeout(() => {
      document.body.removeChild(notificacao);
    }, 300);
  }, 3000);
}
