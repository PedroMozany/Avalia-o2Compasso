package dao;

import model.Emoticons;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmoticonsDAO {
    private Connection connection;

    public EmoticonsDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void salvar(Emoticons emoticons) throws SQLException {
        String query = "INSERT INTO EMOTICONS(FRASE,EXPRESSAO) VALUES (?,?)";
        try (PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, emoticons.getFrase());
            st.setString(2, emoticons.getDescricao());
            st.execute();
        }
    }

}
