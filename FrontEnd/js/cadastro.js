document.addEventListener('DOMContentLoaded', () => {
    const cadastroForm = document.getElementById('cadastro-form'); 

    console.log('Status de carregamento do formulário de cadastro:', cadastroForm ? 'Encontrado' : 'NÃO ENCONTRADO!');

    if (cadastroForm) { 
        cadastroForm.addEventListener('submit', function(event) {
            event.preventDefault();

            const nome = document.getElementById('nome').value.trim();
            const email = document.getElementById('email-telefone').value.trim();
            const senha = document.getElementById('senha').value.trim();

            if (nome === '' || email === '' || senha === '') {
                alert('Por favor, preencha todos os campos.');
                return;
            }

            const regexEmail = /^[^\s@]+@(hotmail\.com|gmail\.com)$/i;
            if (!regexEmail.test(email)) {
                alert('Insira um email válido que termine com @hotmail.com ou @gmail.com.');
                return;
            }

            if (senha.length < 6) {
                alert('A senha deve ter no mínimo 6 caracteres.');
                return;
            }

            window.location.href = 'index.html'; 
        });
    } else {
        console.error('ERRO: O formulário de cadastro com ID "cadastro-form" não foi encontrado. Verifique o HTML.');
    }
});