function abrirModal(id) {
    const modal = document.getElementById(id);
    modal.classList.add('show');
    modal.style.display = 'flex';
}

function fecharModal(id) {
    const modal = document.getElementById(id);
    modal.classList.remove('show');
    modal.style.display = 'none';
}

window.addEventListener('click', function(event) {
    const modais = document.querySelectorAll('.modal');
    modais.forEach(modal => {
        if (event.target === modal) {
            modal.classList.remove('show');
            modal.style.display = 'none';
        }
    });
});