

const obj = {

}

const QUANTITY_IDENTIFIER = 'quantity_counter';
document.addEventListener('DOMContentLoaded', () => {
    const up = document.querySelectorAll('.up');
    const down = document.querySelectorAll('.down');

    const incrementQuantity = () => {
        alert("1");
    }

    const decreaseQuantity = () => {
        alert(2);
    }

    up.forEach(up => up.addEventListener('click', incrementQuantity))
    down.forEach(down => down.addEventListener('click', decreaseQuantity))
})