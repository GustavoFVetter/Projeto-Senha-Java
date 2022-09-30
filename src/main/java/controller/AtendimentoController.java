/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.AtendimentoModel;
import util.ConnectionFactory;

/**
 *
 * @author Gustavo F. Vetter
 */
public class AtendimentoController {

    public int save(AtendimentoModel atendimentoModel) {
        // System.out.println("-------->  Chamou o método SAVE, pós JPA");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Iniciando uma transação
        entityManager.getTransaction().begin();
        // Todas as linhas acima substituem 
        // os comandos conectionfactory, prepared statement

        // Comando para salvar (persist) os dados no atendimentoModel
        entityManager.persist(atendimentoModel);
        // Comando para commitar (confirmar) as informações no DB
        entityManager.getTransaction().commit();
        // Após fazer e confirmar as trabnsações, é necessário 
        // encerrar todas as conexões
        // entityManager é o equivalente ao statement
        entityManager.close();
        // entityManagerFactory é o equivalente ao connectionFactory
        entityManagerFactory.close();
        // return do atendimentoModel que vai fazer o preenchmento 
        // de todos os campos
        return atendimentoModel.getId();

        // Todos os comandos abaixo serão convertidos ao JPA, nas linhas acima
//        String sql = "INSERT INTO ATENDIMENTO "
//                + "(NOME, DATA, STATUS) "
//                + "VALUES (?, ?, ?)";
//        
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet rs = null;
//        
//        try {
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement
//              (sql, Statement.RETURN_GENERATED_KEYS);
//            
//            statement.setString(1, atendimentoModel.getNome());
//            statement.setTimestamp(2, new Timestamp
//              (atendimentoModel.getData().getTime()));
//            statement.setInt(3, atendimentoModel.getStatus());
//            
//            statement.execute();
//            
//            rs = statement.getGeneratedKeys();
//            
//            if (rs.next()){
//                return rs.getInt(1);
//            }
//            
//        } catch (SQLException ex) {            
//            throw new SQLException("Erro ao inserir a senha: " 
//              + ex.getMessage(),ex);
//        }finally{
//            ConnectionFactory.closeConnection(conn, statement, rs);
//        }
//        return 0;
    }

    // Método para buscar toda a lista de pessoas em espera
    public List<AtendimentoModel> getAll() {

        System.out.println("-------->  Teste do getALL");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {
            // Criando uma query (consulta) na entidade AtendimentoModel
            Query query = entityManager.createQuery("from AtendimentoModel");
            // O comando abaixo já retorna uma lista da consulta
            return query.getResultList();
            // As 2 linhas acima substituem todo o SQL do modelo anterior
        } finally {
            // Após todo o try, é preciso fechar todas as conexões
            entityManager.close();
            entityManagerFactory.close();
        }

//        String sql = "SELECT * FROM ATENDIMENTO";
//        
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        
//        List<AtendimentoModel> atendimentoList = new ArrayList();
//        
//        try {
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//            resultSet = statement.executeQuery();
//            
//            while(resultSet.next()){
//                AtendimentoModel atendimentoModel = new AtendimentoModel();
//        
//                atendimentoModel.setId(resultSet.getInt("ID"));
//                atendimentoModel.setNome(resultSet.getString("NOME"));
//                atendimentoModel.setData(resultSet.getTimestamp("DATA"));
//                atendimentoModel.setAtendimento(resultSet.getTimestamp
//                      ("ATENDIMENTO"));
//                atendimentoModel.setStatus(resultSet.getInt("STATUS"));
//                
//                atendimentoList.add(atendimentoModel);
//            }
//            
//        } catch (SQLException ex) {
//            throw new SQLException("Erro ao inserir a senha: " 
//              + ex.getMessage(),ex);
//        }finally{
//            ConnectionFactory.closeConnection(conn, statement, resultSet);
//        }
//        
//        return atendimentoList;
    }

    // Método para buscar o próximo a lista a ser atendido
    public AtendimentoModel getFirst() {

        System.out.println("-------->  Aqui chama o getFirst");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {
            // Criando uma query (consulta) na entidade AtendimentoModel
            Query query = entityManager.createQuery("from AtendimentoModel "
                    + "where status = 0 order by id asc");
            // O comando abaixo retorna o máximo de resultado, limitado a 1
            query.setMaxResults(1);
            // COmo só retornará 1 registro, usa-se o getSingleResult
            return (AtendimentoModel) query.getSingleResult();
            // As 2 linhas acima substituem todo o SQL do modelo anterior
        } catch (Exception e) {
            return null;
        } finally {
            // Após todo o try, é preciso fechar todas as conexões
            entityManager.close();
            entityManagerFactory.close();
        }

//        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 0 
//          order by id asc limit 1";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//
//                AtendimentoModel atendimentoModel = new AtendimentoModel();
//
//                atendimentoModel.setId(resultSet.getInt("ID"));
//                atendimentoModel.setNome(resultSet.getString("NOME"));
//                atendimentoModel.setData(resultSet.getDate("DATA"));
//                atendimentoModel.setStatus(resultSet.getInt("STATUS"));
//                return atendimentoModel;
//            }
//            return null;
//            
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar fazer select que obtém a 
//              pessoa a ser atendida no banco de dados " + e.getMessage(), e);
//
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement, resultSet);
//        }
    }

    // Método para mostrar os que estão esperando na fila, limitado a 3 nomes
    public List<AtendimentoModel> getNextList() {

        System.out.println("-------->  Aqui chama o getNextList");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {
            // Criando uma query (consulta) na entidade AtendimentoModel
            Query query = entityManager.createQuery("from AtendimentoModel "
                    + "where status = 0 order by id asc");
            // Comando abaixo lista os primeiros 3 na lista de espera
            query.setMaxResults(3);
            // O comando abaixo já retorna a lista da consulta
            return query.getResultList();
            // As 2 linhas acima substituem todo o SQL do modelo anterior
        } finally {
            // Após todo o try, é preciso fechar todas as conexões
            entityManager.close();
            entityManagerFactory.close();
        }

//        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 0 
//          order by id asc limit 3";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        List<AtendimentoModel> atendimentoList = new ArrayList<>();
//
//        try {
//
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//
//                AtendimentoModel atendimentoModel = new AtendimentoModel();
//
//                atendimentoModel.setId(resultSet.getInt("ID"));
//                atendimentoModel.setNome(resultSet.getString("NOME"));
//                atendimentoModel.setData(resultSet.getDate("DATA"));
//                atendimentoModel.setStatus(resultSet.getInt("STATUS"));
//
//                atendimentoList.add(atendimentoModel);
//
//            }
//            return atendimentoList;
//            
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar obter as próximas pessoas 
//              a serem atendidas no banco de dados " + e.getMessage(), e);
//
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement, resultSet);
//        }
    }

    // Método para mostrar os que já foram chamados, limitado a 3 nomes
    public List<AtendimentoModel> getChamadosList() {

        System.out.println("-------->  Aqui chama o getChamadosList");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {
            // Criando uma query (consulta) na entidade AtendimentoModel
            Query query = entityManager.createQuery("from AtendimentoModel "
                    + "where status = 2 order by id desc");
            // Comando abaixo lista os primeiros 3 na lista de espera
            query.setMaxResults(3);
            // O comando abaixo já retorna a lista da consulta
            return query.getResultList();
            // As 2 linhas acima substituem todo o SQL do modelo anterior
        } finally {
            // Após todo o try, é preciso fechar todas as conexões
            entityManager.close();
            entityManagerFactory.close();
        }

//        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 2 
//          order by id desc limit 3";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        List<AtendimentoModel> atendimentoList = new ArrayList<>();
//
//        try {
//
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//
//                AtendimentoModel atendimentoModel = new AtendimentoModel();
//
//                atendimentoModel.setId(resultSet.getInt("ID"));
//                atendimentoModel.setNome(resultSet.getString("NOME"));
//                atendimentoModel.setData(resultSet.getDate("DATA"));
//                atendimentoModel.setStatus(resultSet.getInt("STATUS"));
//
//                atendimentoList.add(atendimentoModel);
//
//            }
//            return atendimentoList;
//            
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar obter as pessoas 
//              já chamadas no banco de dados " + e.getMessage(), e);
//
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement, resultSet);
//        }
    }

    // Método para mostrar quem está em atendimento
    public AtendimentoModel getChamado() {

        //System.out.println("-------->  Aqui chama o getChamado");
        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {
            // Criando uma query (consulta) na entidade AtendimentoModel
            Query query = entityManager.createQuery("from AtendimentoModel "
                    + "where status = 1 order by id asc");
            // O comando abaixo retorna o máximo de resultado, limitado a 1
            query.setMaxResults(1);
            // COmo só retornará 1 registro, usa-se o getSingleResult
            return (AtendimentoModel) query.getSingleResult();
            // As 2 linhas acima substituem todo o SQL do modelo anterior
        } catch (Exception e) {

            System.out.println("Não há clientes na fila de espera.");
            return null;
        } finally {
            // Após todo o try, é preciso fechar todas as conexões
            entityManager.close();
            entityManagerFactory.close();
        }

//        String sql = "SELECT * FROM ATENDIMENTO WHERE STATUS = 1 
//          order by id asc limit 1";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//
//            resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//
//                AtendimentoModel atendimentoModel = new AtendimentoModel();
//
//                atendimentoModel.setId(resultSet.getInt("ID"));
//                atendimentoModel.setNome(resultSet.getString("NOME"));
//                atendimentoModel.setData(resultSet.getDate("DATA"));
//                atendimentoModel.setStatus(resultSet.getInt("STATUS"));
//                return atendimentoModel;
//            }
//            return null;
//            
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar recuperar registro do 
//              banco de dados " + e.getMessage(), e);
//
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement, resultSet);
//        }
    }

    public void updateJaAtendido() {

        System.out.println("-------->  Aqui chama o updateJaAtendido");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Todas as linhas acima substituem os comandos 
        // conectionfactory, prepared statement

        try {

            // Criando uma query na entidade AtendimentoModel para fazer update
            Query query = entityManager.createQuery("UPDATE AtendimentoModel "
                    + "SET STATUS = 2 WHERE STATUS = 1");
            // Abrindo a transação com o DB
            entityManager.getTransaction().begin();
            // Executando o update
            query.executeUpdate();
            // Confirmando (commit) o update no DB
            entityManager.getTransaction().commit();
        } finally {
            // Após fazer e confirmar as transações, é necessário 
            // encerrar todas as conexões
            // entityManager é o equivalente ao statement
            entityManager.close();
            // entityManagerFactory é o equivalente ao connectionFactory
            entityManagerFactory.close();

        }

//        String sql = "UPDATE ATENDIMENTO SET STATUS = 2 "
//                + "WHERE STATUS = 1";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//
//        try {
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//            statement.execute();
//
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar atualizar para clientes 
//              já atendidos" + e.getMessage(), e);
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement);
//        }
    }

    // Método para atualizar o status de atendimento
    // Método VOID não tem retorno
    public void update(AtendimentoModel atendimentoModel) {

        System.out.println("-------->  Aqui chama o update");

        // Abrindo uma "fábrica de conxão" com o DB, usando a camada 
        // Persistence, através da configuração feita no arquivo PersistenceUnit
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistenceUnit");
        // Criando uma conexão ativa
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Iniciando uma transação
        entityManager.getTransaction().begin();
        // Todas as linhas acima substituem os 
        // comandos conectionfactory, prepared statement

        // Comando para atualizar (merge) os dados no atendimentoModel no DB
        entityManager.merge(atendimentoModel);
        // Comando para commitar (confirmar) as informações no DB
        entityManager.getTransaction().commit();
        // Após fazer e confirmar as transações, é necessário 
        // encerrar todas as conexões
        // entityManager é o equivalente ao statement
        entityManager.close();
        // entityManagerFactory é o equivalente ao connectionFactory
        entityManagerFactory.close();

//        String sql = "UPDATE ATENDIMENTO SET STATUS = ?, ATENDIMENTO = ?"
//                + "WHERE ID = ?";
//
//        Connection conn = null;
//        PreparedStatement statement = null;
//
//        try {
//            conn = ConnectionFactory.getConnection();
//            statement = conn.prepareStatement(sql);
//
//            Integer i = 1;
//
//            statement.setInt(i++, 1);
//            statement.setTimestamp(i++, new java.sql.Timestamp
//              (new Date().getTime()));
//            statement.setInt(i++, atendimentoModel.getId());
//
//            statement.execute();
//
//        } catch (SQLException e) {
//            throw new SQLException("Erro ao tentar atualizar o registro 
//              no banco de dados" + e.getMessage(), e);
//        } finally {
//            ConnectionFactory.closeConnection(conn, statement);
//        }
    }

}
