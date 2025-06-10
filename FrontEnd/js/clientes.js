fetch("http://localhost:8080/api/clientes")
  .then((res) => res.json())
  .then((data) => {
    const lista = document.getElementById("lista-clientes");
    data.forEach((cliente) => {
      const li = document.createElement("li");
      li.innerHTML = `
        <strong>Nome:</strong> ${cliente.nome} |
        <strong>Email:</strong> ${cliente.email} |
        <strong>Telefone:</strong> ${cliente.telefone}
      `;
      lista.appendChild(li);
    });
  })
  .catch((err) => {
    console.error("Erro ao buscar clientes:", err);
  });
