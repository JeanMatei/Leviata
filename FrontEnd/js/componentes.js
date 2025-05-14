
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
} // resto foi o chat principal.js tb

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
