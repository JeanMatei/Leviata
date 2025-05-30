const form = document.getElementById("avaliacaoForm");
const acoes = document.getElementById("acoes");
const estrelas = document.querySelectorAll(
  'fieldset.estrelas input[type="radio"]'
);
const spans = document.querySelectorAll("fieldset.estrelas span");

// Atualizar estrelas ao clicar ou passar o mouse
function atualizarEstrelas(valor) {
  spans.forEach((span, index) => {
    span.style.color = index < valor ? "#ffcc00" : "#555";
  });
}

// Eventos de clique e hover
estrelas.forEach((estrela) => {
  estrela.addEventListener("change", () => {
    atualizarEstrelas(parseInt(estrela.value));
  });

  estrela.parentElement.addEventListener("mouseenter", () => {
    atualizarEstrelas(parseInt(estrela.value));
  });

  estrela.parentElement.addEventListener("mouseleave", () => {
    const selecionada = document.querySelector(
      'input[name="avaliacao"]:checked'
    );
    atualizarEstrelas(selecionada ? parseInt(selecionada.value) : 0);
  });
});

// Submissão do formulário
form.addEventListener("submit", (e) => {
  e.preventDefault();

  const avaliacaoSelecionada = form.querySelector(
    'input[name="avaliacao"]:checked'
  );
  if (!avaliacaoSelecionada) {
    alert("Por favor, escolha uma avaliação antes de continuar.");
    return;
  }

  alert("Obrigado pela sua avaliação! O download foi liberado.");

  acoes.style.display = "flex";
  form
    .querySelectorAll("input, textarea, button")
    .forEach((el) => (el.disabled = true));
});

// Inicializar estado
const selecionada = document.querySelector('input[name="avaliacao"]:checked');
if (selecionada) atualizarEstrelas(parseInt(selecionada.value));
