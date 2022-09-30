/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jonathandamasiomedeiros
 */
// Comandos com @ são para mapear/correlacionar os campos do model 
// com as colunas do banco de dados
@Entity
@Table(name = "atendimento")
public class AtendimentoModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private Date data;
    private Date atendimento;
    private int status;//0 = não atendido, 1 = em atendimento, 2 = já atendidos

    public AtendimentoModel(int id, String nome, Date data, Date atendimento,
            int status) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.atendimento = atendimento;
        this.status = status;
    }

    public AtendimentoModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Date atendimento) {
        this.atendimento = atendimento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

}
