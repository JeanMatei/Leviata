document.addEventListener("DOMContentLoaded", function () {
  const button = document.getElementById("installBtn");

  button.addEventListener("click", function () {
    if (
      button.classList.contains("installing") ||
      button.classList.contains("installed")
    )
      return;

    button.classList.add("installing");
    button.textContent = "Instalando...";

    setTimeout(() => {
      button.classList.remove("installing");
      button.classList.add("installed");
      button.innerHTML = 'Instalado <span class="checkmark">✔</span>';
    }, 4000);
  });
});
