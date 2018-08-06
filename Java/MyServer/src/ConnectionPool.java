import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.pool.OracleConnectionCacheManager;
import java.util.Properties;
import java.sql.*;
// 전역객체, 싱글턴
// 접속시간을 줄임, 시스템 자원을 낭비하지 않음 network나 시스템 메모리 등, 최소의 자원으로 최소효과를 내기 위함
// connection하기 위해 걸리는 시간을 줄여줌
public class ConnectionPool {
    private final  static String CACHE_NAME = "MYCACHE";
    private  static OracleDataSource ods = null;

    private ConnectionPool() { }

    public static Connection getConnection() throws SQLException {
        return getConnection("env. unspecified");
    }


    public static Connection getConnection(String env)
       throws SQLException
    {
        System.out.println("Request connection for " + env);
        if (ods == null) {
            throw new SQLException("OracleDataSource is null.");
        }
        return ods.getConnection();
    }
    static {
        try {
            ods = new OracleDataSource();
            ods.setURL("jdbc:oracle:thin:@ec2-13-125-210-91.ap-northeast-2.compute.amazonaws.com:1521:xe");
            ods.setUser("scott");
            ods.setPassword("tiger");
            
//          ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
            // caching parms
            ods.setConnectionCachingEnabled(true);
            ods.setConnectionCacheName(CACHE_NAME);

            Properties cacheProps = new Properties();
            cacheProps.setProperty("MinLimit", "3");
            cacheProps.setProperty("MaxLimit", "3");
            cacheProps.setProperty("InitialLimit", "1");
            cacheProps.setProperty("ConnectionWaitTimeout", "5");
            cacheProps.setProperty("ValidateConnection", "true");

            ods.setConnectionCacheProperties(cacheProps);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * private constructor for static class
     */
   

    public static void closePooledConnections() throws SQLException{
        if (ods != null ) {
            ods.close();
        }
    }

    public static void listCacheInfos() throws SQLException{
        OracleConnectionCacheManager occm =
            OracleConnectionCacheManager.getConnectionCacheManagerInstance();
        System.out.println
            (occm.getNumberOfAvailableConnections(CACHE_NAME)
                + " connections are available in cache " + CACHE_NAME);
        System.out.println
            (occm.getNumberOfActiveConnections(CACHE_NAME)
                + " connections are active");

      }
 }