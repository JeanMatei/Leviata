document.addEventListener("DOMContentLoaded", () => {

  carregarComponente("cabecalho__container", "cabecalho.html")
  carregarComponente("banner__container", "banner.html")
  carregarComponente("ofertas__container", "ofertas-relampago.html")
  carregarComponente("todos__produtos-container", "todos-produtos.html")


  setTimeout(() => {
    inicializarContador()
    inicializarSlider()
    inicializarBotoesProduto()
    inicializarNavegacao()
    inicializarBotaoVerMais() 
  }, 500)
})

function inicializarContador() {
  
  console.log("Contador inicializado")
}

function inicializarSlider() {
 
  console.log("Slider inicializado")
}

function inicializarBotoesProduto() {
  
  console.log("Botões do produto inicializados")
}

function inicializarNavegacao() {
  
  console.log("Navegação inicializada")
}



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