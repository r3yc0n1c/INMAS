import java.sql.*;
import javax.swing.JOptionPane;

public class User {
	public void update(String ID,String NAME,String PRICE,String UNIT){

		try{

			Connection con=Connectionz.getConnection();
			PreparedStatement pst=con.prepareStatement("update Items set Pdt_Name=?,Pdt_Price=?,Pdt_Unit=? where Pdt_Id=?");
			pst.setString(1, NAME);
			pst.setString(2, PRICE);
			pst.setString(3, UNIT);
			pst.setString(4, ID);
			pst.executeUpdate();
			pst.close();

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}	
	public void delete(String ID){

		try{

			Connection con=Connectionz.getConnection();
			Statement st=con.createStatement();
			String query="delete from Items where Pdt_Id="+ID;
			int a=st.executeUpdate(query);

		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);

		}
	}	
}
