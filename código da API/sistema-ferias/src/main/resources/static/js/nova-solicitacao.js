function calcularDias() {
    const inicio = document.getElementById('dataInicio').value;
    const fim = document.getElementById('dataFim').value;
    const divResumo = document.getElementById('resumoDias');

    if (inicio && fim) {
        const date1 = new Date(inicio);
        const date2 = new Date(fim);
        const diffTime = Math.abs(date2 - date1);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;

        if (diffDays > 0) {
            divResumo.textContent = `Total: ${diffDays} dias`;
            divResumo.style.color = "#ffffff";
        } else {
            divResumo.textContent = "A data final deve ser maior que a inicial";
            divResumo.style.color = "#ff6b6b";
        }
    }
}

async function enviarSolicitacao(event) {
    event.preventDefault();

    const servidorId = document.getElementById('servidorId').value;
    const dataInicio = document.getElementById('dataInicio').value;
    const dataFim = document.getElementById('dataFim').value;

    const payload = {
        servidorId: parseInt(servidorId),
        dataInicio: dataInicio,
        dataFim: dataFim
    };

    try {
        const response = await fetch('/api/ferias', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        });

        if (response.ok) {
            alert('Férias solicitadas com sucesso!');
            window.location.href = `/ferias/${servidorId}`;
        } else {
            const errorText = await response.text();
            alert('Erro ao solicitar: ' + errorText);
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro de conexão com o servidor.');
    }
}