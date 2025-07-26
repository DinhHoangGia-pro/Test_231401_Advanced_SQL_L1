package application;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Global 
{
	
	public static boolean ok=false;
	public static boolean Question(String msg)
	{
		ok=false;
		
		//Thử Message Box
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Xác nhận...");
		alert.setHeaderText("Xác nhận");
		alert.setContentText(msg);
		alert.showAndWait().ifPresent(rs -> {
		if (rs == ButtonType.OK) {
		//System.out.println("Pressed OK.");
			ok=true;
		}
		else if (rs==ButtonType.CANCEL)
		{
			ok=false;
		}
		});
		
		return ok;
		
	}
	public static void Thongbao(String msg)
	{
		
		
		//Thử Message Box
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Thông báo");
		alert.setHeaderText("Thông báo");
		alert.setContentText(msg);
		alert.showAndWait().ifPresent(rs -> {
		if (rs == ButtonType.OK) {
		//System.out.println("Pressed OK.");
			
		}
		
		});
		
		
		
	}


}
