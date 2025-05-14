// Carrega os componentes HTML
document.addEventListener("DOMContentLoaded", () => {
  // Carrega os componentes
  carregarComponente("cabecalho-container", "/html/cabecalho.html")
  carregarComponente("banner-container", "/html/banner.html")
  carregarComponente("ofertas-container", "/html/ofertas-relampago.html")
  carregarComponente("todos-produtos-container", "/html/todos-produtos.html")

  // Inicializa as funcionalidades
  setTimeout(() => {
    inicializarContador()
    inicializarSlider()
    inicializarBotoesProduto()
    inicializarNavegacao()
  }, 500)
})

// Declaração das funções inicializar... (simulação, pois não temos o código real)
function inicializarContador() {
  // Lógica para inicializar o contador
  console.log("Contador inicializado")
}

function inicializarSlider() {
  // Lógica para inicializar o slider
  console.log("Slider inicializado")
}

function inicializarBotoesProduto() {
  // Lógica para inicializar os botões do produto
  console.log("Botões do produto inicializados")
}

function inicializarNavegacao() {
  // Lógica para inicializar a navegação
  console.log("Navegação inicializada")
}

// Função para carregar componentes HTML
function carregarComponente(containerId, url) {
  fetch(url)
    .then((response) => response.text())
    .then((html) => {
      document.getElementById(containerId).innerHTML = html
    })
    .catch((error) => {
      console.error(`Erro ao carregar o componente ${url}:`, error)
    })
}
