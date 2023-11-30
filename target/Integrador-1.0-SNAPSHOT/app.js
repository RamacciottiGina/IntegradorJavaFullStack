
const form = document.getElementById("buyForm")
const submitBtn = document.getElementById('submit')
function openNav(){
    document.getElementById("mobile-menu").style.width = "100%";
}

function closeNav(){
    document.getElementById("mobile-menu").style.width = "0%";
}

function calculateTotal() {
    const combo1Quantity = parseInt(document.getElementById("combo1").value);
    const combo2Quantity = parseInt(document.getElementById("combo2").value);
    const combo3Quantity = parseInt(document.getElementById("combo3").value);
  
    const combo1Price = 5500;
    const combo2Price = 4000;
    const combo3Price = 6000;

  
    let subtotal = combo1Quantity * combo1Price + combo2Quantity * combo2Price + combo3Quantity * combo3Price;
    
    const extrasList = document.getElementById("extras-list");
    const extrasCheckboxes = extrasList.getElementsByTagName("input");

    for (let i = 0; i < extrasCheckboxes.length; i++) {
      if (extrasCheckboxes[i].checked) {
        subtotal += parseInt(extrasCheckboxes[i].value);
      }
    }

    
    document.getElementById("subtotal").textContent = "Subtotal: $" + subtotal.toFixed(2);
    let discount = 0;
    let total = subtotal;

  
    if (subtotal > 15000) {
      discount = subtotal * 0.15;
    } else if (subtotal > 10000) {
      discount = subtotal * 0.1;
    } else if (subtotal > 7000) {
      discount = subtotal * 0.05;
    }
  
    total = total - discount;

    document.getElementById("total").textContent = "Total (con descuento): $" + total.toFixed(2);
    $( "#total" ).last().addClass( "focusColorTotal" );
    $( "#subtotal" ).last().addClass( "focusColorSubtotal" );

}

    function resetFields() {
      document.getElementById("combo1").value = 0;
      document.getElementById("combo2").value = 0;
      document.getElementById("combo3").value = 0;
      document.getElementById("subtotal").textContent = "";
      document.getElementById("total").textContent = "";

      var extrasList = document.getElementById("extras-list");
      var extrasCheckboxes = extrasList.getElementsByTagName("input");
    
      for (let i = 0; i < extrasCheckboxes.length; i++) {
        extrasCheckboxes[i].checked = false;
      }

    }

let orderNro =Math.floor(Math.random()*1000) ;

  function confirm () {
    Swal.fire({
      title: 'Completaste tu compra?',
      showDenyButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `Rechazar`,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(`Gracias por elegirnos!`, `<i>Tu numero de pedido es #${orderNro} </i>`, 'success');   
      } 
      else if (result.isDenied) {
        Swal.fire('Segui comprando!', '', 'info')
      }
    });
  }

  const verifyForm = (form) => {

    const { nombre, apellido, mail, celular, direccion, metodoPago } = form

    const verified = {
        nombre: nombre.value !== '',
        apellido: apellido.value !== '',
        mail: mail.value.includes('@'),
        celular: celular.value !== '',
        direccion: direccion.value !== '',
        metodoPago: metodoPago.value !== 'none'
    }

    const values = Object.values(verified)
    const accepted = values.every(value => value)

    return accepted
}

const adapterForm = ({ nombre, apellido, mail, celular, direccion, metodoPago }) => {

    const data = {
        nombre: nombre.value,
        apellido: apellido.value,
        mail: mail.value,
        celular: celular.value,
        direccion: direccion.value,
        metodoPago: metodoPago.value
    }

    return data
}

const submitForm = async (form) => {

    const data = adapterForm(form)

    const config = {
        method: 'POST',
        body: JSON.stringify(data),
    }
    console.log(JSON.stringify(data))
    fetch('/api/buy', config)
}


const submit = (e) => {

    e.preventDefault()

    const submitAccepted = verifyForm(form)

    submitAccepted
        ? submitForm(form)
        : alert('Debes completar todos los campos correctamente')
}


form.addEventListener('submit', submit)

