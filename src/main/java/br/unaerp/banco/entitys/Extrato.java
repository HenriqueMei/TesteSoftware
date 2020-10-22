package br.unaerp.banco.entitys;

import java.util.Date;

public class Extrato {
    private Date horario;
    private int numConta;
    private double valor;
    private String acao;

    public Extrato(Date horario, int numConta, double valor, String acao) {
        this.horario = horario;
        this.numConta = numConta;
        this.valor = valor;
        this.acao = acao;
    }

    public Date getHorario() {
        return horario;
    }

    public int getNumConta() {
        return numConta;
    }

    public double getValor() {
        return valor;
    }

    public String getAcao() {
        return acao;
    }
}
