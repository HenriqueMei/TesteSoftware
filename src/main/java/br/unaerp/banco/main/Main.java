package br.unaerp.banco.main;

import br.unaerp.banco.entitys.Cliente;
import br.unaerp.banco.entitys.Conta;
import br.unaerp.banco.enums.TipoConta;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Data.clients = new HashMap<String, Cliente>();


        Conta conta = new Conta(1, TipoConta.CONTA_CORRENTE, 500);
        Cliente cliente = new Cliente("Alfredo", "1", conta);

        cliente.getConta().depositar(500);


        Conta conta2 = new Conta(1, TipoConta.CONTA_CORRENTE, 500);
        Cliente cliente2 = new Cliente("Alfredo", "2", conta2);

        Conta conta3 = new Conta(1, TipoConta.CONTA_SALARIO, 500);
        Cliente cliente3 = new Cliente("Alfredo", "3", conta3);

        Data.clients.put("1", cliente);
        Data.clients.put("2", cliente2);
        Data.clients.put("3", cliente3);


        new Menu();
    }
}
