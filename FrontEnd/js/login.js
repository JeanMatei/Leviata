
  document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const emailTelefoneInput = document.getElementById('email-telefone');
    const senhaInput = document.getElementById('senha');

    form.addEventListener('submit', function (event) {
      event.preventDefault();

      const emailTelefone = emailTelefoneInput.value.trim();
      const senha = senhaInput.value.trim();

      if (!emailTelefone || !senha) {
        alert('Por favor, preencha todos os campos.');
        return;
      }

      if (senha.length < 6) {
        alert('A senha deve ter pelo menos 6 caracteres.');
        return;
      }

      // Simulação de login
      alert('Login realizado com sucesso!');
      form.reset();
    });

    // Newsletter no rodapé
    const newsletterInput = document.querySelector('.input-button-wrapper input[type="email"]');
    const newsletterButton = document.querySelector('.input-button-wrapper button');

    if (newsletterButton) {
      newsletterButton.addEventListener('click', function (e) {
        e.preventDefault();
        const email = newsletterInput.value.trim();

        if (!validateEmail(email)) {
          alert('Por favor, insira um e-mail válido para se inscrever.');
          return;
        }

        alert(`Obrigado por se inscrever com o e-mail: ${email}`);
        newsletterInput.value = '';
      });
    }

    function validateEmail(email) {
      const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return re.test(email.toLowerCase());
    }

    // Botões de download de app
    document.querySelectorAll('.googleplay-btn, .appstore-btn').forEach(btn => {
      btn.addEventListener('click', function (event) {
        event.preventDefault();
        alert('Redirecionando para a loja...');
      });
    });
  });
