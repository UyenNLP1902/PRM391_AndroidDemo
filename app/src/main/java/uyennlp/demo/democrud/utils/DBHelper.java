package uyennlp.demo.democrud.utils;

import android.os.StrictMode;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper implements Serializable {

    private static String ip = "192.168.100.8";
    private static String port = "1433";
    private static String clazz = "net.sourceforge.jtds.jdbc.Driver";
    private static String db = "AndroidDemoCrud";
    private static String un = "sa";
    private static String password = "SE140355";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Class.forName(clazz);
        String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";"
                + "encrypt=false;"
                + "databaseName=" + db
                + ";user=" + un
                + ";password=" + password + ";";
        Connection connection = DriverManager.getConnection(url);

        return connection;
    }
}
