import java.sql.*;

public class YakSQLAccess {
	private String url;
	private String user;
	private String password;
	
	private YakSQLAccess(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	private float[][] copyArray(float[][] newarray, float[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < 5; j++) {
				newarray[i][j] = array[i][j];
			}
		}
		return newarray;
	}
	
	public float[][] getYakData() {
		float[][] array = new float[100][5];
		try {
			Connection myConn = DriverManager.getConnection(url, user, password);
			Statement myStmt = myConn.createStatement();
			String sql = "select * from YAKData.contacts";
			ResultSet rs = myStmt.executeQuery(sql);
			
			int rowCount = 0;
			while(rs.next()) {
				++rowCount;
			}
			
			
			while(rs.next()) {
				for(int i = 0; i<rowCount; i++) {
					for(int j = 0; j<5; j++) {
						//only if array is full
						if (i == array.length - 1) {
							float[][] newarray = new float[2*array.length][5];
							array = copyArray(newarray, array);
						}
						//put values into array
						rs.getInt(j);
					}
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}

	public String getURL(){
		return this.url;
	}
	public String setURL(String newURL){
		return this.url = newURL;
	}
}