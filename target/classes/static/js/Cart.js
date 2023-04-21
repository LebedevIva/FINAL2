function loadCart() {
    console.log('loadCart function called');
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/cart/items/count?' + Date.now());
    xhr.onload = function() {
        if (xhr.status === 200) {
            var count = parseInt(xhr.responseText);
            var cartBadge = document.getElementById('cart-badge');
            if (count > 0) {
                cartBadge.textContent = count;
            } else {
                cartBadge.textContent = '';
            }
        } else {
            console.error('Error loading cart count: ' + xhr.status);
        }
    };
    console.log('Sending XHR request');
    xhr.send();
}

// Call loadCart() on page load
window.addEventListener('load', loadCart);