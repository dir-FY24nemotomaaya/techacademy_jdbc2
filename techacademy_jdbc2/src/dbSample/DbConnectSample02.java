package dbSample;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectSample02{

	public static void main(String[] args){
		
		 // データベース接続と結果取得のための変数
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
	    try{
	    	// 1. ドライバーのクラスをJava上で読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. DBと接続する
		    con = DriverManager.getConnection(
	            "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
	            "root",
	            "Kamekame_com0223"
	        );

			// 3. DBとやりとりする窓口（Statementオブジェクト）の作成
			stmt = con.createStatement();

			// 4,5. Select文の実行と結果を格納／代入
			String sql = "SELECT * FROM country LIMIT 50";
	        rs = stmt.executeQuery(sql);

			// 6. 結果を表示する
	        while( rs.next() ){
	            // Name列の値を取得
                String name = rs.getString("Name");
                // 取得した値を表示
	            System.out.println(name);
	        }
	        
	        // 6-1. データの更新を行う
            sql = "update country set Population = 105000 where Code = 'ABW'";
            int count = stmt.executeUpdate(sql);
			try {
				count = stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
            System.out.println(count);

	    	} catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
        	
			// 7. 接続を閉じる
	    	if( rs != null ){
                 try {
                     rs.close();
                 } catch (SQLException e) {
                     System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                     e.printStackTrace();
                 }
            }
	    	
	    	if( stmt != null ){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
	    	
	    	if( con != null ){
	            try {
	                   con.close();
	            } catch (SQLException e) {
	                System.err.println("データベース切断時にエラーが発生しました。");
	                e.printStackTrace();
                }
            }
	    }
	}
		


