
document.addEventListener('DOMContentLoaded', function() {
   
    const thumbnails = document.querySelectorAll('.produto__galeria .thumb');
    
    
    thumbnails.forEach(function(thumb, index) {
        thumb.addEventListener('click', function() {
            
            thumbnails.forEach(function(t) {
                t.classList.remove('active');
            });
            
     
            this.classList.add('active');
            
          
        });
        
  
        thumb.addEventListener('mouseenter', function() {
            this.style.transform = 'scale(1.05)';
        });
        
        thumb.addEventListener('mouseleave', function() {
            if (!this.classList.contains('active')) {
                this.style.transform = 'scale(1)';
            }
        });
    });

    const botoes = document.querySelectorAll('.botao, .avaliar');
    
    botoes.forEach(function(botao) {
        botao.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-3px)';
        });
        
        botao.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });
    

    const botaoAvaliar = document.querySelector('.avaliar');
    let avaliado = false;
    
    if (botaoAvaliar) {
        botaoAvaliar.addEventListener('click', function() {
            const icone = this.querySelector('i');
            
            if (!avaliado) {
                icone.classList.remove('far');
                icone.classList.add('fas');
                this.innerHTML = '<i class="fas fa-heart"></i> Produto avaliado!';
                this.style.background = '#f85149';
                this.style.color = 'white';
                avaliado = true;
                
                setTimeout(() => {
                    this.innerHTML = '<i class="far fa-heart"></i> Avaliar produto';
                    this.style.background = 'transparent';
                    this.style.color = '#f85149';
                    avaliado = false;
                }, 2000);
            }
        });
    }
    
    const links = document.querySelectorAll('a[href^="#"]');
    
    links.forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            const targetId = this.getAttribute('href');
            const targetElement = document.querySelector(targetId);
            
            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
});