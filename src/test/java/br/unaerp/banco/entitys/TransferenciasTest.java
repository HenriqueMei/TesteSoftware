package br.unaerp.banco.entitys;

import br.unaerp.banco.enums.TipoConta;
import br.unaerp.banco.exceptions.IncorrectAccountException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferenciasTest{
    @Test
    public void transferirParaOutraConta() {
        Conta conta1 = new Conta(4, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente1 = new Cliente("Alberto", "45123156321", conta1);
        cliente1.getConta().depositar(50);

        Conta conta2 = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente2 = new Cliente("Alfredo", "161231561321", conta2);
        cliente2.getConta().depositar(50);

        conta1.transferirValor(50, conta2);

        assertEquals(100, cliente2.getConta().getSaldo(), 0.0001);
    }

    @Test(expected = IncorrectAccountException.class)
    public void transferirParaAMesmaConta() {
        Conta conta1 = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente1 = new Cliente("Alfredo", "161231561321", conta1);

        cliente1.getConta().depositar(50);
        conta1.transferirValor(50, conta1);
    }


    @Test(expected = IncorrectAccountException.class)
    public void transferirAPartirDeContaPoupanca() {
        Conta conta1 = new Conta(52116, TipoConta.CONTA_POUPANCA, 50);
        Cliente cliente1 = new Cliente("Alfredo", "161231561321", conta1);
        cliente1.getConta().depositar(50);

        Conta conta2 = new Conta(52116, TipoConta.CONTA_CORRENTE, 50);
        Cliente cliente2 = new Cliente("Alfredo", "161231561321", conta2);
        cliente2.getConta().depositar(50);

        conta1.transferirValor(50, conta2);
    }

    @Test(expected = IncorrectAccountException.class)
    public void transferirDeUmaContaPoupancaParaOutra() {
        Conta conta1 = new Conta(52116, TipoConta.CONTA_POUPANCA, 50);
        Cliente cliente1 = new Cliente("Alfredo", "161231561321", conta1);
        cliente1.getConta().depositar(50);

        Conta conta2 = new Conta(52116, TipoConta.CONTA_POUPANCA, 50);
        Cliente cliente2 = new Cliente("Alfredo", "161231561321", conta2);
        cliente2.getConta().depositar(50);

        conta1.transferirValor(50, conta2);
    }
}
