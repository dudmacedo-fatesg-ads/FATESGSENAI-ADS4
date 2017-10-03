$(document).ready(function () {
    $('#fone').focusout(function () {
        var phone, element;
        element = $(this);
        element.unmask();
        phone = element.val().replace(/\D/g, '');
        if (phone.length > 10) {
            element.mask("(99) 99999-999?9");
        } else {
            element.mask("(99) 9999-9999?9");
        }
    }).trigger('focusout');
});

function validaCadCliente() {
    // IDF
    if (!validaIDF($("#idf").val())) {
        alert("Favor informar um CPF/CNPJ válido.");
        return false;
    } else if (
            ($("#statustrue").prop("checked") !== true && $("#statusfalse").prop("checked") !== true )) {
        alert("Todos os campos marcados com (*) são obrigatórios.");
        return false;
    }


    return true;
}

function setFormPJ() {
    $("#idf").prop('readonly', false);
    $("#idflabel").html("CNPJ<b>(*)</b>:");
    $("#idf").mask("99.999.999/9999-99");
    $("#verifidf").html("");
}

function setFormPF() {
    $("#idf").prop('readonly', false);
    $("#idflabel").html("CPF<b>(*)</b>:");
    $("#idf").mask("999.999.999-99");
    $("#verifidf").html("");
}

function idfBlur() {
    var valor = $("#idf").val();

    if (!(valor === "" || valor.indexOf("_") !== -1)) {
        var resultado = validaIDF(valor);
        if (resultado) {
            $("#verifidf").html("<p style=\"font-weight:bold;color:green;\">OK</p>");
        } else {
            $("#verifidf").html("<p style=\"font-weight:bold;color:red;\">Inválido</p>");
        }
    }
}

function validaIDF(valor) {
    valor = valor.split(".").join("").split("-").join("").split("/").join("");

    if (valor === "") {
        return false;
    }
    var digitos_iguais = true;
    for (var i = 0; i < valor.length - 1; i++) {
        if (valor.charAt(i) !== valor.charAt(i + 1))
        {
            digitos_iguais = false;
            break;
        }
    }
    if (digitos_iguais) {
        return false;
    }

    if (valor.length === 11) {
        var cont = 10, somadv1 = 0, somadv2 = 0;
        for (var i = 0; i < 9; i++) {
            somadv1 += cont * valor.charAt(i);
            somadv2 += (cont + 1) * valor.charAt(i);
            cont--;
        }

        var dv1 = (somadv1 * 10) % 11;
        if (dv1 === 10) {
            dv1 = 0;
        }

        somadv2 += dv1 * 2;
        var dv2 = (somadv2 * 10) % 11;
        if (dv2 === 10) {
            dv2 = 0;
        }

        var dv = (dv1 * 10) + dv2;

        if (dv !== parseInt(valor.substring(9, 11))) {
            return false;
        } else {
            return true;
        }
    } else if (valor.length === 14) {
        var cont1 = 6, cont2 = 5, somadv1 = 0, somadv2 = 0;
        for (var i = 0; i < 12; i++) {
            somadv1 += cont1 * valor.charAt(i);
            somadv2 += cont2 * valor.charAt(i);

            if (cont1 < 9) {
                cont1++;
            } else {
                cont1 = 2;
            }
            if (cont2 < 9) {
                cont2++;
            } else {
                cont2 = 2;
            }
        }

        var dv1 = somadv1 % 11;
        if (dv1 === 10) {
            dv1 = 0;
        }

        somadv2 += dv1 * 9;
        var dv2 = somadv2 % 11;
        if (dv2 === 10) {
            dv2 = 0;
        }

        var dv = (dv1 * 10) + dv2;

        if (dv !== parseInt(valor.substring(12, 14))) {
            return false;
        }
    }
    return true;
}
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