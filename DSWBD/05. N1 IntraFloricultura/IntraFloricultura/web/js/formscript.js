function maskCadClientes() {
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
}

function maskCadFornecedores() {
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
}

function maskCadProdutos() {

}

function validaCadFornecedor() {
// IDF
    if (!validaIDF($("#idf").val())) {
        alert("Favor informar um CPF/CNPJ válido.");
        return false;
    } else if ($("#nome").val() === "" || $("#endereco").val() === "" || $("#fone").val() === "" ||
            ($("#statustrue").prop("checked") !== true && $("#statusfalse").prop("checked") !== true)) {
        alert("Todos os campos marcados com (*) são obrigatórios.");
        return false;
    }
    return true;
}

function validaCadCliente() {
// IDF
    if (!validaIDF($("#idf").val())) {
        alert("Favor informar um CPF/CNPJ válido.");
        return false;
    } else if ($("#nome").val() === "" || $("#endereco").val() === "" || $("#fone").val() === "" ||
            ($("#statustrue").prop("checked") !== true && $("#statusfalse").prop("checked") !== true)) {
        alert("Todos os campos marcados com (*) são obrigatórios.");
        return false;
    }
    return true;
}

function validaCadProduto() {
    if ($("#codigo").val() === "" || $("#nome").val() === "" || $("#descricao").val() === "" ||
            $("#unidade").val() === "" || $("#quantidade").val() === "" || $("#valor").val() === "" ||
            ($("#statustrue").prop("checked") !== true && $("#statusfalse").prop("checked") !== true)) {
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

function removeItemPedido(idItem) {
    if (confirm('Você tem certeza que deseja excluir o produto?')) {
        $("#" + idItem).remove();
        updateTotalPedido();
    }
    return false;
}

function adicionaItemPedido() {
    count++;
    newitem = '<tr class="tablerow ' + ((count % 2 !== 0) ? "list-odd" : "") + '" id="item' + count + '">' +
            '<td><input name="item' + count + '_codigo" id="item' + count + '_codigo" onblur="itemPedidoCodigoBlur(' + count + ');" type="number" placeholder="Código do Produto"></td>' +
            '<td id="item' + count + '_nome"></td>' +
            '<td id="item' + count + '_unidade"></td>' +
            '<td><input name="item' + count + '_quantidade" id="item' + count + '_quantidade" type="number" step="0.01" value="0" onblur="updateTotalPedido();"></td>' +
            '<td><input name="item' + count + '_valor" id="item' + count + '_valor" type="number" step="0.01" readonly value="0"></td>' +
            '<td><input id="item' + count + '_valortotal" type="number" step="0.01" readonly value="0"></td>' +
            '<td><input type="button" alt="Excluir" onclick="return removeItemPedido(\'item' + count + '\');" value="  -  "/></td>' +
            '</tr>';
    $("#itens").append(newitem);
}

function updateTotalPedido() {
    total = 0.0;
    itens = $('#itens').children();
    for (i = 0; i < itens.length; i++) {
        valor = $('#' + itens[i].id + '_quantidade').val() * $('#' + itens[i].id + '_valor').val();
        $('#' + itens[i].id + '_valortotal').val(valor.toFixed(2));
        total += valor;
    }
    $('#totpedido').val(total.toFixed(2));
}

function itemPedidoCodigoBlur(count) {
    $.get("cadProdutos", {action: "checkproduto", codigo: $("#item" + count + "_codigo").val()})
            .done(function (data) {
                dados = data.split("||");
                if (dados.length > 1) {
                    $('#item' + count + '_nome').html(dados[0]);
                    $('#item' + count + '_unidade').html(dados[1]);
                    $('#item' + count + '_valor').val(dados[2]);
                } else {
                    alert(data);
                }
            });
    updateTotalPedido();
}

function idfBlurPedido() {
    var valor = $("#idf").val();
    if (!(valor === "" || valor.indexOf("_") !== -1)) {
        var resultado = validaIDF(valor);
        if (resultado) {
            valor = valor.split(".").join("").split("-").join("").split("/").join("");
            $.get("cadClientes", {action: "checkidf", tipo: $("input:radio[name ='tipo']:checked").val(), idf: valor})
                    .done(function (data) {
                        $("#verifidf").html(data);
                    });
        } else {
            $("#verifidf").html("<p style=\"font-weight:bold;color:red;\">Inválido</p>");
        }
    }
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