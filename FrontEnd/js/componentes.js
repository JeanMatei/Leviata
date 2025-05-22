// Funções existentes...

function inicializarSlider() {
  const indicadores = document.querySelectorAll(".indicador")

  if (!indicadores.length) return

  indicadores.forEach((indicador, index) => {
    indicador.addEventListener("click", () => {
    
      indicadores.forEach((ind) => ind.classList.remove("indicador--ativo"))

    
      indicador.classList.add("indicador--ativo")

    })
  })
}

function inicializarBotoesProduto() {
  const botoesFavorito = document.querySelectorAll(".produto__favorito")
  const botoesCarrinho = document.querySelectorAll(".produto__carrinho")

  botoesFavorito.forEach((botao) => {
    botao.addEventListener("click", (e) => {
      e.preventDefault()
    
      botao.classList.toggle("ativo")

    
      console.log("Produto adicionado aos favoritos")
    })
  })

  botoesCarrinho.forEach((botao) => {
    botao.addEventListener("click", (e) => {
      e.preventDefault()

  
      console.log("Produto adicionado ao carrinho")
    })
  })
}

// Inicializa a navegação para a seção Todos os Produtos
function inicializarNavegacao() {
  const botaoAnterior = document.querySelector(".navegacao__anterior")
  const botaoProximo = document.querySelector(".navegacao__proximo")

  if (botaoAnterior && botaoProximo) {
    botaoAnterior.addEventListener("click", () => {
      // Navega para os produtos anteriores
      console.log("Navegar para produtos anteriores")
    })

    botaoProximo.addEventListener("click", () => {
      // Navega para os próximos produtos
      console.log("Navegar para próximos produtos")
    })
    
  }
}

// NOVA FUNÇÃO: Inicializa o botão "Ver mais produtos"
function inicializarBotaoVerMais() {
  const botaoVerMais = document.getElementById("verMaisProdutos")
  
  if (!botaoVerMais) return
  
  // Variáveis para controle de paginação
  let paginaAtual = 1
  const produtosPorPagina = 4
  
  botaoVerMais.addEventListener("click", () => {
    // Mostrar indicador de carregamento
    const icone = botaoVerMais.querySelector("i")
    const carregando = document.getElementById("carregando")
    
    if (icone) icone.style.display = "none"
    if (carregando) carregando.style.display = "block"
    
    // Incrementar página atual
    paginaAtual++
    
    // Simular carregamento
    setTimeout(() => {
      // Buscar mais produtos
      carregarMaisProdutos(paginaAtual, produtosPorPagina)
        .then(resultado => {
          // Se não houver mais produtos para carregar
          if (!resultado.temMaisProdutos) {
            botaoVerMais.style.display = "none"
          }
          
          // Esconder indicador de carregamento
          if (carregando) carregando.style.display = "none"
          if (icone) icone.style.display = "inline-block"
          
          // Inicializar eventos nos novos produtos
          inicializarBotoesProduto()
        })
        .catch(erro => {
          console.error("Erro ao carregar mais produtos:", erro)
          
          // Esconder indicador de carregamento
          if (carregando) carregando.style.display = "none"
          if (icone) icone.style.display = "inline-block"
          
          // Mostrar mensagem de erro
          mostrarNotificacao("Não foi possível carregar mais produtos. Tente novamente mais tarde.", "erro")
        })
    }, 1000)
  })
}

// Função para carregar mais produtos
function carregarMaisProdutos(pagina, quantidade) {
  return new Promise((resolve, reject) => {
    // Buscar produtos da página todos-produtos.html
    fetch("todos-produtos.html")
      .then(response => response.text())
      .then(html => {
        // Criar elemento temporário para extrair produtos
        const tempDiv = document.createElement("div")
        tempDiv.innerHTML = html
        
        // Selecionar todos os produtos da página
        const todosProdutos = tempDiv.querySelectorAll(".produto")
        
        // Calcular índices para paginação
        const inicio = (pagina - 1) * quantidade
        const fim = inicio + quantidade
        
        // Verificar se há mais produtos para carregar
        const temMaisProdutos = fim < todosProdutos.length
        
        // Selecionar apenas os produtos da página atual
        const produtosPagina = Array.from(todosProdutos).slice(inicio, fim)
        
        // Se não houver produtos para esta página
        if (produtosPagina.length === 0) {
          resolve({ temMaisProdutos: false })
          return
        }
        
        // Adicionar produtos à grade
        const gridProdutos = document.querySelector(".ofertas__grid")
        
        if (gridProdutos) {
          produtosPagina.forEach(produto => {
            // Adicionar classe para animação
            produto.classList.add("produto--novo")
            gridProdutos.appendChild(produto)
          })
          
          // Mostrar notificação de sucesso
          mostrarNotificacao(`${produtosPagina.length} novos produtos carregados!`)
          
          // Resolver a promessa
          resolve({ temMaisProdutos })
        } else {
          reject(new Error("Container de produtos não encontrado"))
        }
      })
      .catch(erro => reject(erro))
  })
}

// Função para mostrar notificações
function mostrarNotificacao(mensagem, tipo = "sucesso") {
  // Criar elemento de notificação
  const notificacao = document.createElement("div")
  notificacao.className = `notificacao notificacao--${tipo}`
  
  // Definir ícone com base no tipo
  let icone = "check-circle"
  if (tipo === "erro") icone = "exclamation-circle"
  else if (tipo === "aviso") icone = "exclamation-triangle"
  
  notificacao.innerHTML = `
    <div class="notificacao__conteudo">
      <i class="fas fa-${icone}"></i>
      <span>${mensagem}</span>
    </div>
  `
  
  // Adicionar estilo inline para a notificação
  notificacao.style.cssText = `
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: ${tipo === "sucesso" ? "var(--cor-destaque)" : tipo === "erro" ? "#ef4444" : "#f59e0b"};
    color: white;
    padding: 12px 20px;
    border-radius: 4px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    z-index: 1000;
    transform: translateY(100px);
    opacity: 0;
    transition: transform 0.3s, opacity 0.3s;
  `
  
  // Adicionar estilo para o conteúdo
  const conteudo = notificacao.querySelector(".notificacao__conteudo")
  conteudo.style.cssText = `
    display: flex;
    align-items: center;
    gap: 10px;
  `
  
  // Adicionar ao body
  document.body.appendChild(notificacao)
  
  // Mostrar com animação
  setTimeout(() => {
    notificacao.style.transform = "translateY(0)"
    notificacao.style.opacity = "1"
  }, 10)
  
  // Remover após 3 segundos
  setTimeout(() => {
    notificacao.style.transform = "translateY(100px)"
    notificacao.style.opacity = "0"
    
    // Remover do DOM após a animação
    setTimeout(() => {
      document.body.removeChild(notificacao)
    }, 300)
  }, 3000)
}