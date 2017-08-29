function exe1_calcular() {
    numero = document.getElementById('exe1_num1').value;

    result = 'Metade: ';
    result += numero / 2;
    result += '<br>Dobro: ';
    result += numero * 2;

    document.getElementById('exe1_result').innerHTML = result;
}

function exe2_calcular() {
    result = 'Nome digitado: ';
    result += document.getElementById('exe2_nome').value;
    result += '<br>Idade daqui a 30 anos: ';
    result += (parseInt(document.getElementById('exe2_idade').value) + 30);

    document.getElementById('exe2_result').innerHTML = result;
}

function exe3_calcular() {
    num1 = parseInt(document.getElementById('exe3_num1').value);
    num2 = parseInt(document.getElementById('exe3_num2').value);

    result = '<table border="1"><tr><th>Operação</th><th>Resultado</th></tr>';

    result += '<tr><td>' + num1 + ' + ' + num2 + '</td><td>' + (num1 + num2) + '</td></tr>';
    result += '<tr><td>' + num1 + ' - ' + num2 + '</td><td>' + (num1 - num2) + '</td></tr>';
    result += '<tr><td>' + num1 + ' * ' + num2 + '</td><td>' + (num1 * num2) + '</td></tr>';
    result += '<tr><td>' + num1 + ' / ' + num2 + '</td><td>' + (num1 / num2) + '</td></tr>';
    result += '<tr><td>' + num1 + ' % ' + num2 + '</td><td>' + (num1 % num2) + '</td></tr>';

    result += '</table>';

    document.getElementById('exe3_result').innerHTML = result;
}

function exe4_calcular() {
    salario = parseFloat(document.getElementById('exe4_salario').value);

    if (salario <= 280) {
        salario *= 1.2;
    } else if (salario > 280 && salario <= 700) {
        salario *= 1.15;
    } else if (salario > 700 && salario <= 1500) {
        salario *= 1.1;
    } else if (salario > 1500) {
        salario *= 1.05;
    }

    result = 'Novo Salário: ';

    result += salario;

    document.getElementById('exe4_result').innerHTML = result;
}

function exe5_calcular() {
    num1 = parseInt(document.getElementById('exe5_num1').value);
    num2 = parseInt(document.getElementById('exe5_num2').value);

    result = '';

    for (i = num1; i <= num2; i++) {
        result += i + '² = ' + (i * i) + '<br>';
    }

    document.getElementById('exe5_result').innerHTML = result;
}

function exe6_calcular() {
    num = parseInt(document.getElementById('exe6_num').value);

    result = 'Soma = ' + ((num * (num + 1)) / 2);



    document.getElementById('exe6_result').innerHTML = result;
}

function exe7_calcular() {
    num = parseInt(document.getElementById('exe7_num').value);

    var func_fatorial = function (num) {
        if (num > 1) {
            return num * func_fatorial(num - 1);
        } else {
            return num;
        }
    };

    result = 'Fatorial = ' + func_fatorial(num);

    document.getElementById('exe7_result').innerHTML = result;
}

function exe8_calcular() {
    num = parseInt(document.getElementById('exe8_num').value);

    result = '<table border="1"><tr><th>Operação</th><th>Resultado</th></tr>';

    for (i = 1; i <= 10; i++) {
        result += '<tr><td>' + num + ' * ' + i + '</td><td>' + (num * i) + '</td></tr>';
    }

    result += '</table>';

    document.getElementById('exe8_result').innerHTML = result;
}