$(document).ready(function () {
    $("#cpf").mask("999.999.999-99");

    $("#formCliente").validate();
});

//$(document).ready( function() {
//  $("#validate").validate({
//    // Define as regras
//    rules:{
//      nome:{
//        required: true
//      },
//      email:{
//        required: true, email: true
//      },
//
//    },
//
//    messages:{
//      nome:{
//        required: "Digite o seu nome",
//      },
//      email:{
//        required: "Digite o seu e-mail para contato",
//        email: "Digite um e-mail válido"
//      },
//
//    }
//  });
//});


////$(document).ready(function () {
//    $("#data").mask("99/99/9999");
//    $("#telefone").mask("(99) 9999-9999");
//    $("#cpf").mask("999.999.999-99");
//    $("#cep").mask("99999-999");
//
//    $('#celular').focusout(function () {
//        var phone, element;
//        element = $(this);
//        element.unmask();
//        phone = element.val().replace(/\D/g, '');
//        if (phone.length > 10) {
//            element.mask("(99) 99999-999?9");
//        } else {
//            element.mask("(99) 9999-9999?9");
//        }
//    }).trigger('focusout');
//});