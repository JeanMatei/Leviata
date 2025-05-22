
  document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const nomeInput = document.getElementById('nome');
    const emailTelefoneInput = document.getElementById('email-telefone');
    const senhaInput = document.getElementById('senha');

    form.addEventListener('submit', function (event) {
      event.preventDefault();

      const nome = nomeInput.value.trim();
      const emailTelefone = emailTelefoneInput.value.trim();
      const senha = senhaInput.value.trim();

      if (!nome || !emailTelefone || !senha) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      if (senha.length < 6) {
        alert('A senha deve ter no mínimo 6 caracteres.');
        return;
      }

      alert('Conta criada com sucesso!');
      form.reset();
    });

    // Newsletter do rodapé
    const newsletterInput = document.querySelector('.input-button-wrapper input[type="email"]');
    const newsletterButton = document.querySelector('.input-button-wrapper button');

    if (newsletterButton) {
      newsletterButton.addEventListener('click', function () {
        const email = newsletterInput.value.trim();
        if (!email || !validateEmail(email)) {
          alert('Insira um e-mail válido para se inscrever.');
          return;
        }

        alert(`Obrigado por se inscrever, ${email}!`);
        newsletterInput.value = '';
      });
    }

    // Função de validação simples de e-mail
    function validateEmail(email) {
      const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return re.test(email.toLowerCase());
    }

    // Botões da loja de apps (placeholders)
    document.querySelectorAll('.googleplay-btn, .appstore-btn').forEach(btn => {
      btn.addEventListener('click', function (event) {
        event.preventDefault();
        alert('Redirecionando para a loja...');
      });
    });
  });

