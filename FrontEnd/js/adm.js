

document.addEventListener('DOMContentLoaded', () => {
    const listaAdms = document.getElementById('lista-adms');

    if (!listaAdms) {
        console.error('Elemento #lista-adms não encontrado no HTML. A lista de administradores não será carregada.');
        return;
    }

    fetch('http://localhost:8080/api/adms')
        .then(res => {
            if (!res.ok) {
                // Lança um erro se a resposta da rede não for bem-sucedida (status 4xx ou 5xx)
                throw new Error(`Erro HTTP! Status: ${res.status}`);
            }
            return res.json();
        })
        .then(data => {
            if (data.length === 0) {
                listaAdms.innerHTML = '<li>Nenhum administrador cadastrado.</li>';
                return;
            }
            data.forEach(adm => {
                const li = document.createElement('li');
                li.style.marginBottom = '10px'; // Estilo básico para cada item
                li.style.padding = '8px';
                li.style.border = '1px solid #ddd';
                li.style.borderRadius = '4px';
                li.textContent = `Nome: ${adm.nome}, Empresa: ${adm.nm_empresa}, Email: ${adm.email}, WhatsApp: ${adm.whatsapp}`;
                listaAdms.appendChild(li);
            });
        })
        .catch(err => {
            console.error('Erro ao buscar dados dos administradores:', err);
            listaAdms.innerHTML = `<li style="color: red;">Erro ao carregar administradores: ${err.message}. Verifique se o backend está rodando em http://localhost:8080.</li>`;
        });
});