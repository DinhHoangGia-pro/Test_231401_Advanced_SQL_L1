package application;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class frmDangnhapController 
{
	
	@FXML 
	TextField txtUsername;
	
	@FXML
	PasswordField txtPassword;
	
	public String hashText(String text)  {
		try {
		String plainText = text;
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = digest.digest(plainText.getBytes());
		String str=Base64.getEncoder().encodeToString(hashedBytes);
		return str;
		}catch(Exception ex) 
		{
			return text;
		}
	}
	
	@FXML
	public void dangnhap_clicked(ActionEvent event)
	{
		
		//Goto_mainscreen();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
		//Dangnhap(stage,txtUsername.getText(), txtPassword.getText());	
		//Dangnhap_fix1(stage,txtUsername.getText(), txtPassword.getText());	
		Dangnhap_fix2(stage,txtUsername.getText(),  hashText(txtPassword.getText()));	
	}
	
	public void Dangnhap(Stage stage,String username, String password)
	{
		try {
			Connection conn=DataConnection.getConnection();
			System.out.println("conn="+conn.isClosed());
			
			String query = "SELECT id, username FROM	tbl_User "
					+ "where username='"+username+"' and password='"+password+"'";
			
			
			
			PreparedStatement pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, username);
//			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				//Dang nhap thanh cong
				Goto_mainscreen();
				
		        stage.close();
			}
			else
			{
				Global.Thongbao("Đăng nhập không thành công.\n Hãy xem lại tên và mật khẩu");
			}
			
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	public void Dangnhap_fix1(Stage stage,String username, String password)
	{
		try {
			Connection conn=DataConnection.getConnection();
			System.out.println("conn="+conn.isClosed());
			
			String query = " DECLARE @tennguoidung nvarchar(100), @matkhau nvarchar(300);\r\n"
					+ " set @tennguoidung='"+username+"';"
					+ " set @matkhau='"+password+"';"
					+ " SELECT id, username FROM	tbl_User "
					+ "  where username=@tennguoidung and [password]=@matkhau ";
			
			
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				//Dang nhap thanh cong
				Goto_mainscreen();
				
		        stage.close();
			}
			else
			{
				Global.Thongbao("Đăng nhập không thành công.\n Hãy xem lại tên và mật khẩu");
			}
			
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	
	public void Dangnhap_fix2(Stage stage,String username, String password)
	{
		try {
			Connection conn=DataConnection.getConnection();
						
			String query = "SELECT id, username FROM	tbl_User "
					+ "where username=? and password=?";
			
				
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				//Dang nhap thanh cong
				Goto_mainscreen();
				
		        stage.close();
			}
			else
			{
				Global.Thongbao("Đăng nhập không thành công.\n Hãy xem lại tên và mật khẩu");
			}			
			
		}
		
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
	}
	
	
	private void Goto_mainscreen()
	{
		//Parent root;
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,1062,702);
			Stage stage = new Stage();
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
