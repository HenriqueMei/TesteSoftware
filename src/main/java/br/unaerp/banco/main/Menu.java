package br.unaerp.banco.main;

import br.unaerp.banco.entitys.Cliente;
import br.unaerp.banco.entitys.Conta;
import br.unaerp.banco.enums.TipoConta;

import java.util.Scanner;
import java.util.Set;

public class Menu {
    public Menu() {
        exibirMenu();
    }


    private void exibirMenu() {
        System.out.println("1..Cadastrar nova conta");
        System.out.println("2..Ver contas cadastradas");
        System.out.println("3..Realizar Operacao");

        int op = new Scanner(System.in).nextInt();


        switch (op) {
            case 1:
                novaConta();
                break;
            case 2:
                todasContas();
                break;
            case 3:
                operacoes();
                break;
        }
    }

    private void todasContas() {
        Set<String> allUsers = Data.clients.keySet();
        for (String key : allUsers) {
            System.out.println(Data.clients.get(key).getNome() + " - " + key);
        }
        exibirMenu();
    }

    private void novaConta() {
        System.out.println("Nome: ");
        String scannerNome = new Scanner(System.in).nextLine();

        System.out.println("Cpf: ");
        String scannerCpf = new Scanner(System.in).nextLine();

        if (Data.clients.containsKey(scannerCpf)) {
            System.out.println("CPF ja cadastrado");
            exibirMenu();
        }

        System.out.println("** Criando Conta **");

        System.out.println("Numero da conta");
        int numeroDaConta = new Scanner(System.in).nextInt();

        System.out.println("Limite Contratado");
        double limiteCOntratado = new Scanner(System.in).nextDouble();

        System.out.println("Tipo de conta (1. Conta corrente, 2. Conta Poupanca , 3. Conta Salario)");
        int tipo = new Scanner(System.in).nextInt();

        TipoConta tipoConta;
        switch (tipo) {
            case 1:
                tipoConta = TipoConta.CONTA_CORRENTE;
                break;
            case 2:
                tipoConta = TipoConta.CONTA_POUPANCA;
                break;
            case 3:
                tipoConta = TipoConta.CONTA_SALARIO;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipo);
        }

        Conta c = new Conta(numeroDaConta, tipoConta, limiteCOntratado);
        Cliente cliente = new Cliente(scannerNome, scannerCpf, c);

        Data.clients.put(scannerCpf, cliente);

        System.out.println(cliente.toString() + "\n\n");

        exibirMenu();
    }

    private void operacoes() {
        System.out.println("Qual operacao deseja?");
        System.out.println("1..Depositar");
        System.out.println("2..Sacar");
        System.out.println("3..Transferir");
        System.out.println("4..Verificar Saldo");
        System.out.println("5..Voltar");

        int op = new Scanner(System.in).nextInt();


        switch (op) {
            case 1:
                depositar();
                break;
            case 2:
                sacar();
                break;
            case 3:
                transferir();
                break;
            case 4:
                verSaldo();
                break;
            case 5:
                exibirMenu();
                break;
        }

    }

    private void verSaldo() {
        System.out.println("Informar cpf da conta desejada ou ? para ver todas");
        String cpf = new Scanner(System.in).next();


        if (cpf.equals("?")) {
            Set<String> allUsers = Data.clients.keySet();
            for (String key : allUsers) {
                System.out.println(Data.clients.get(key).getNome() + " - " + key);
            }
            depositar();
        }

        if (!Data.clients.containsKey(cpf)) {
            System.out.println("Cpf nao encontrado...");
            operacoes();
        }

        try {
            System.out.println(Data.clients.get(cpf).getConta().getSaldo());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        operacoes();
    }

    private void depositar() {
        System.out.println("Informar cpf da conta desejada ou ? para ver todas");
        String cpf = new Scanner(System.in).next();


        if (cpf.equals("?")) {
            Set<String> allUsers = Data.clients.keySet();
            for (String key : allUsers) {
                System.out.println(Data.clients.get(key).getNome() + " - " + key);
            }
            depositar();
        }

        if (!Data.clients.containsKey(cpf)) {
            System.out.println("Cpf nao encontrado...");
            operacoes();
        }


        System.out.println("Valor a ser depositado");
        double valorDeposito = new Scanner(System.in).nextDouble();

        try {
            Data.clients.get(cpf).getConta().depositar(valorDeposito);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        operacoes();
    }

    private void sacar() {
        System.out.println("Informar cpf da conta desejada ou ? para ver todas");
        String cpf = new Scanner(System.in).next();


        if (cpf.equals("?")) {
            Set<String> allUsers = Data.clients.keySet();
            for (String key : allUsers) {
                System.out.println(Data.clients.get(key).getNome() + " - " + key);
            }
            depositar();
        }

        if (!Data.clients.containsKey(cpf)) {
            System.out.println("Cpf nao encontrado...");
            operacoes();
        }


        System.out.println("Valor a ser retirado");
        double valorDeposito = new Scanner(System.in).nextDouble();

        try {
            Data.clients.get(cpf).getConta().sacar(valorDeposito);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        operacoes();
    }

    private void transferir() {
        System.out.println("Informar cpf da conta desejada ou ? para ver todas");
        String cpf = new Scanner(System.in).next();


        if (cpf.equals("?")) {
            Set<String> allUsers = Data.clients.keySet();
            for (String key : allUsers) {
                System.out.println(Data.clients.get(key).getNome() + " - " + key);
            }
            depositar();
        }

        if (!Data.clients.containsKey(cpf)) {
            System.out.println("Cpf nao encontrado...");
            operacoes();
        }


        System.out.println("Valor a ser transferido");
        double valorDeposito = new Scanner(System.in).nextDouble();

        System.out.println("Informar cpf da conta que recebera a transferencia ou ? para ver todas");
        String cpfTarget = new Scanner(System.in).next();

        if (!Data.clients.containsKey(cpfTarget)) {
            System.out.println("Cpf nao encontrado...");
            operacoes();
        }

        try {
            Data.clients.get(cpf).getConta().transferirValor(valorDeposito, Data.clients.get(cpfTarget).getConta());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        operacoes();
    }

}
