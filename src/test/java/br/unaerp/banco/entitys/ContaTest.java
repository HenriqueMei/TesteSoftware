package br.unaerp.banco.entitys;

import br.unaerp.banco.enums.TipoConta;
import br.unaerp.banco.exceptions.IncorrectAccountException;
import br.unaerp.banco.exceptions.WithoutBalanceException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaTest {
    @Test
    public void sacar() {
        Conta conta = new Conta(1, TipoConta.CONTA_CORRENTE, 500);
        Cliente cliente = new Cliente("Alfredo", "12345678912", conta);

        cliente.getConta().depositar(500);
        cliente.getConta().sacar(450);

        assertEquals(50, cliente.getConta().getSaldo(), 0.0001);
    }

    @Test
    public void depositar() {
        Conta conta = new Conta(2, TipoConta.CONTA_CORRENTE, 500);
        Cliente cliente = new Cliente("Luis", "32165498712", conta);

        cliente.getConta().depositar(50);

        assertEquals(50, cliente.getConta().getSaldo(), 0.0001);
    }

    @Test(expected = WithoutBalanceException.class)
    public void sacarComsaldoZero() {
        Conta conta = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente = new Cliente("Alfredo", "161231561321", conta);

        cliente.getConta().sacar(50);
    }

    @Test(expected = WithoutBalanceException.class)
    public void sacarValorMaiorQueOSaldo() {
        Conta conta = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente = new Cliente("Alfredo", "161231561321", conta);

        cliente.getConta().depositar(50);
        cliente.getConta().sacar(100);
    }

    @Test
    public void exibirExtrato(){
        Conta conta1 = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente1 = new Cliente("Alfredo", "161231561321", conta1);
        // Depositando 50 para o cliente 1
        cliente1.getConta().depositar(50);
        cliente1.getConta().depositar(50);
        cliente1.getConta().depositar(50);
        cliente1.getConta().depositar(50);

        conta1.exibirExtrato();
    }

    @Test(expected = IncorrectAccountException.class)
    public void depositarEmContaSalario(){
        Conta conta1 = new Conta(52116, TipoConta.CONTA_SALARIO, 50);
        Cliente cliente1 = new Cliente("Alfredo", "161231561321", conta1);
        // Depositando 50 para o cliente 1
        cliente1.getConta().depositar(50);
    }
}