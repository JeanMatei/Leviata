const radios = document.querySelectorAll('input[name="pagamento"]');
const formCartao = document.getElementById('form-cartao');
const formPix = document.getElementById('form-pix');
const codigoPix = document.getElementById('codigo-pix');
const canvasPix = document.getElementById('qr-pix');
const botaoPix = document.getElementById('finalizar-pix');

radios.forEach((radio) => {
  radio.addEventListener('change', () => {
    if (radio.value === 'cartao') {
      formCartao.classList.add('ativo');
      formPix.classList.remove('ativo');
    } else {
      formPix.classList.add('ativo');
      formCartao.classList.remove('ativo');
      gerarQRCodePix();
    }
  });
});

function gerarQRCodePix() {
  const chavePix = '12345678900';
  const nome = 'Leviatã';
  const cidade = 'TUBARAO';
  const valor = '119.80';
  const descricao = 'Pagamento Leviatã';

  const codigo = `00020126360014BR.GOV.BCB.PIX0114${chavePix}5204000053039865406${valor}5802BR5912${nome}6006${cidade}62140515Pagamento Leviatã6304`;

  codigoPix.value = codigo;

  desenharQRCode(canvasPix, codigo);
}

function desenharQRCode(canvas, texto) {
  const ctx = canvas.getContext('2d');
  const size = 200;
  canvas.width = size;
  canvas.height = size;

  ctx.fillStyle = 'white';
  ctx.fillRect(0, 0, size, size);

  ctx.fillStyle = 'black';
  const scale = 5;
  for (let y = 0; y < size / scale; y++) {
    for (let x = 0; x < size / scale; x++) {
      if (Math.random() > 0.5) ctx.fillRect(x * scale, y * scale, scale, scale);
    }
  }

  ctx.fillStyle = 'black';
  ctx.font = '14px Arial';
  ctx.fillText('QR PIX', 10, size - 10);
}

document.getElementById('form-cartao').addEventListener('submit', function(e) {
  e.preventDefault();
  window.location.href = 'avaliacao.html';
});

botaoPix.addEventListener('click', function(e) {
  e.preventDefault();
  window.location.href = 'avaliacao.html';
});
