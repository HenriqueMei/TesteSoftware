package br.unaerp.banco.entitys;

import br.unaerp.banco.enums.TipoConta;
import br.unaerp.banco.exceptions.IncorrectAccountException;
import br.unaerp.banco.exceptions.WithoutBalanceException;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    private final int numeroDaConta;
    private final double limiteContratado;
    private final List<Extrato> extrato;
    private TipoConta tipoConta;
    private double saldo;

    public Conta(int numeroDaConta, TipoConta tipoConta, double limiteContratado) {
        this.numeroDaConta = numeroDaConta;
        this.tipoConta = tipoConta;
        this.limiteContratado = limiteContratado;
        extrato = new ArrayList<Extrato>();
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }


    public double getLimiteContratado() {
        return limiteContratado;
    }

    public List<Extrato> getExtrato() {
        return extrato;
    }

    public double sacar(double valor) {
        if ((saldo - valor < 0) || (valor > saldo)) {
            throw new WithoutBalanceException("Valor informado maior que o saldo");
        }
        saldo -= valor;
        return saldo;
    }

    public double depositar(double valor) {
        if(this.tipoConta.equals(TipoConta.CONTA_SALARIO)){
            throw new IncorrectAccountException("Voce não pode depoistar em uma conta do tipo: Conta Salario");
        }

        saldo += valor;

        return saldo;
    }

    public void transferirValor(double valor, Conta contaPara) {
        if (this.tipoConta.equals(TipoConta.CONTA_POUPANCA)) {
            throw new IncorrectAccountException("Voce não pode fazer transferencias em uma conta poupança");
        }

        if ((saldo - valor < 0) || (valor > saldo)) {
            throw new WithoutBalanceException("Valor informado maior que o saldo");
        }

        if (contaPara.equals(this)) {
            throw new IncorrectAccountException("As duas contas nao podem ser iguais");
        }

        contaPara.depositar(valor);
        this.saldo -= valor;
    }

    public void exibirExtrato() {
        for (Extrato extrato : extrato) {
            System.out.println(extrato.getHorario() + " - " + extrato.getAcao() + " - " + extrato.getValor());
        }
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numeroDaConta=" + numeroDaConta +
                ", limiteContratado=" + limiteContratado +
                ", extrato=" + extrato +
                ", tipoConta=" + tipoConta +
                ", saldo=" + saldo +
                '}';
    }
}
