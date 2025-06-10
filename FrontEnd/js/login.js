document.addEventListener('DOMContentLoaded', () => {

    const loginForm = document.getElementById('login-form'); 

    console.log('Status de carregamento do formulário de login:', loginForm ? 'Encontrado' : 'NÃO ENCONTRADO!');

    if (loginForm) { 
        loginForm.addEventListener('submit', function(event) {
          
            event.preventDefault();

            const email = document.getElementById('email-telefone').value.trim();
            const senha = document.getElementById('senha').value.trim();

            if (email === '' || senha === '') {
                alert('Por favor, preencha todos os campos!');
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

            if (email.toLowerCase() === 'teste@hotmail.com' && senha === '123456') {
                alert('Login bem-sucedido!');

                window.location.href = 'index.html'; 
            } else {
                alert('Email ou senha incorretos!');
            }
        });
    } else {
        console.error('ERRO: O formulário de login com ID "login-form" não foi encontrado. Por favor, verifique se o ID está correto no seu HTML.');
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById('email-btn');
    const input = document.getElementById('email-email');

    btn.addEventListener('click', () => {
        const email = input.value.trim();
        const emailValido = /^[^\s@]+@(hotmail\.com|gmail\.com)$/i.test(email);

        if (!emailValido) {
            alert('Por favor, insira um e-mail válido que termine com @hotmail.com ou @gmail.com.');
        } else {
            alert(`Obrigado por se inscrever, ${email}!`);
            window.location.href = 'index.html';
        }
    });
});
