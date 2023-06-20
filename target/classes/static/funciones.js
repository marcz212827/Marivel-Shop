function limpiar_parametros_url() {
    window.history.replaceState(null, '', '/producto/listar')
}

function eliminar_pregunta(el) {
    Swal.fire({
        title: '¿Estás seguro de eliminar?',
        text: "Es una acción irreversible.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#626b73',
        confirmButtonText: 'Si, elimínalo.'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = el.dataset.url
        }
    })
}

function eliminar_confirmacion() {
    Swal.fire(
        '¡Eliminado!',
        'El producto ha sido eliminado.',
        'success'
    ).then(() => {
        limpiar_parametros_url()
    })
}