package dao;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String query = "INSERT INTO PRODUTO(nome,desrição,desconto,preço,data_inicio) VALUES (?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDesricao());
            st.setDouble(3, produto.getDesconto());
            st.setDouble(4, produto.getPreço());
            st.setString(5, produto.getDataInicio());
            st.execute();
        }
    }

    public boolean verificar(int id) throws SQLException,validation {
        String query = "SELECT * FROM PRODUTO WHERE ID = ?";
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, id);
            st.execute();
            try (ResultSet rst = st.getResultSet()) {
                if (rst.next()) {
                    return true;
                } else {
                    throw new validation("ID NÃO EXISTE EM NOSSA BASE DADOS, FAVOR REALIZAR CADASTRO");
                }
            }
        }
    }






    public void atualizar(Produto produto,int id) throws SQLException {
        connection.setAutoCommit(false);
        String query = "UPDATE PRODUTO SET nome =  ?, desrição = ?, desconto = ?, preço = ?, data_inicio = ? WHERE id = " + id;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, produto.getNome());
            st.setString(2, produto.getDesricao());
            st.setDouble(3, produto.getDesconto());
            st.setDouble(4, produto.getPreço());
            st.setString(5, produto.getDataInicio());

            connection.commit();
            st.execute();
        }catch (Exception e){
            System.out.println("ROLLBACK EXECUTADO");
            connection.rollback();
        }

    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM PRODUTO WHERE id = " + id;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.execute();
        }

    }


    public List<Produto> listarPorNome(String palavra) throws SQLException {
        List<Produto> list = new LinkedList<>();
        String query = "SELECT * FROM PRODUTO WHERE NOME LIKE '%" + palavra + "%'" ;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.execute();
            try (ResultSet rst = st.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getDouble(5), rst.getString(6));
                    list.add(produto);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if(list.isEmpty()){
            System.out.println("NÃO FOI ENCONTRADO NADA");
            return list;
        }else {
            return list;
        }
    }


}
