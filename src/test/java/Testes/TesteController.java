/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Testes;

import controller.AtendimentoController;
import java.util.Date;
import java.util.List;
import model.AtendimentoModel;
import org.junit.Test;

/**
 *
 * @author Gustavo F. Vetter em 29/09/2022
 */
public class TesteController {

    @Test
    public void insertTesteController() {
        // Criando uma inserção do DB
        AtendimentoModel atModel = new AtendimentoModel();
        atModel.setNome("Teste insert jUnit");
        atModel.setData(new Date());

        AtendimentoController atController = new AtendimentoController();
        atController.save(atModel);
        // Variável criada para comparar se é diferente de nulo
        Integer foiInserido = atModel.getId();
        // Se foi inserido
        if (foiInserido != null) {
            // Mostre o registro inserido
            System.out.println("---> Registro inserido: "
                    + atModel.toString());;

        } else {
            System.err.println("Falha ao inserir o registro desejado");
        }

    }

    @Test
    public void TesteGetALL() {
        AtendimentoController atController = new AtendimentoController();
        // Cria uma lista
        List<AtendimentoModel> atendimentoModels = atController.getAll();
        // Faz um for each para polular a lista
        for (AtendimentoModel atendimentoModel : atendimentoModels) {
            // e imprime a lista
            System.out.println("--> Lista de todos: "
                    + atendimentoModel.toString());
        }
    }

    @Test
    public void TesteGetFirst() {
        AtendimentoController atController = new AtendimentoController();
        AtendimentoModel atModel = atController.getFirst();
        System.out.println("--> Busque o próximo da fila: "
                + atModel.toString());
    }

    @Test
    public void getNextList() {
        AtendimentoController atController = new AtendimentoController();
        List<AtendimentoModel> atModels = atController.getNextList();

        for (AtendimentoModel atendimentoModel : atModels) {
            System.out.println("--> Próximos 3 a serem chamados: "
                    + atendimentoModel.toString());
        }
    }

    @Test
    public void getChamadosList() {
        AtendimentoController atController = new AtendimentoController();
        List<AtendimentoModel> atModels = atController.getChamadosList();

        for (AtendimentoModel atendimentoModel : atModels) {
            System.out.println("--> Lista dos 3 últimos chamados: "
                    + atendimentoModel.toString());
        }
    }

    @Test
    public void Chamado() {
        AtendimentoController atController = new AtendimentoController();

        AtendimentoModel atModel = atController.getChamado();

        System.out.println("--> Busca o próximo da fila de espera: "
                + atModel);

    }

    @Test
    public void update() {

        // Criando uma inserção do DB
        AtendimentoModel atModel = new AtendimentoModel();
        atModel.setNome("TesteX insert jUnit");
        atModel.setData(new Date());
        AtendimentoController atController = new AtendimentoController();
        atController.save(atModel);

        // Atualizando a inserção recém feita
        atModel.setStatus(1);
        atModel.setAtendimento(new Date());
        atController.update(atModel);

    }

    @Test
    public void updateJaAtendidos() {

        // Atualiza como "já atendido - status 2" quem estava em 
        // atendimento - status 1
        AtendimentoModel atModelX = new AtendimentoModel();
        AtendimentoController atController = new AtendimentoController();
        try {
            atController.updateJaAtendido();
            System.out.println("---> Atendimento efetuado com sucesso. "
                    + atModelX.toString());
        } catch (Exception e) {
            System.out.println("Alteração de status não efetuada.");
        }
    }
}
