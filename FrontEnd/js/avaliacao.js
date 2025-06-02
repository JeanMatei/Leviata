const form = document.getElementById("avaliacaoForm");
const acoes = document.getElementById("acoes");
const estrelas = document.querySelectorAll(
    'fieldset.estrelas input[type="radio"]'
);
const spans = document.querySelectorAll("fieldset.estrelas span");
const comentarioTextarea = document.getElementById("comentario"); // Adicione este ID ao seu textarea de comentário no HTML


function atualizarEstrelas(valor) {
    spans.forEach((span, index) => {
        span.style.color = index < valor ? "#ffcc00" : "#555";
    });
}

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


form.addEventListener("submit", (e) => {
    e.preventDefault();

    const avaliacaoSelecionada = form.querySelector(
        'input[name="avaliacao"]:checked'
    );
    if (!avaliacaoSelecionada) {
        alert("Por favor, escolha uma avaliação antes de continuar.");
        return;
    }

    const nota = parseInt(avaliacaoSelecionada.value);
    const comentario = comentarioTextarea ? comentarioTextarea.value.trim() : ''; // Pega o comentário, se o textarea existir

    // --- NOVA SEÇÃO: Enviar dados para o Backend ---
    fetch('http://localhost:8080/api/avaliacoes', {
        method: 'POST', // Usamos POST para criar um novo recurso (avaliação)
        headers: {
            'Content-Type': 'application/json', // Informa que estamos enviando JSON
            // Se precisar de autenticação (ex: token JWT), adicione aqui:
            // 'Authorization': 'Bearer SEU_TOKEN_AQUI' 
        },
        body: JSON.stringify({
            // idCompra: 'ALGUM_ID_DA_COMPRA', // Você precisaria de um ID de compra aqui
            nota: nota,
            comentario: comentario,
            dtAvaliacao: new Date().toISOString() // Data e hora atual no formato ISO
        })
    })
    .then(response => {
        if (!response.ok) {
            // Lança um erro se a resposta não for 2xx (ex: 400, 500)
            return response.json().then(errorData => {
                throw new Error(errorData.message || 'Falha ao enviar avaliação para o servidor.');
            });
        }
        return response.json(); // Ou response.text() se seu backend não retornar JSON
    })
    .then(data => {
        console.log('Avaliação enviada com sucesso:', data);
        alert("Obrigado pela sua avaliação! O download foi liberado.");

        // Atualizações visuais no front-end após o sucesso
        acoes.style.display = "flex";
        form
            .querySelectorAll("input, textarea, button")
            .forEach((el) => (el.disabled = true));
    })
    .catch(error => {
        console.error('Erro ao enviar avaliação:', error);
        alert(`Ocorreu um erro ao enviar sua avaliação: ${error.message}. Por favor, tente novamente.`);
        // Você pode decidir se quer desabilitar o formulário ou não em caso de erro
    });
    // --- FIM DA NOVA SEÇÃO ---
});

// A linha abaixo já está no seu código original, apenas mantendo
const selecionada = document.querySelector('input[name="avaliacao"]:checked');
if (selecionada) atualizarEstrelas(parseInt(selecionada.value));