package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;


public class frmEditController 
{
	
	private int ProductID = 0;
    private Product product;
    
    @FXML
    TextField txtproductID,txtProductName,txtUnitPrice,txtQuantityPerUnit;

	public void setData(int ProductID) {
        this.ProductID = ProductID;
//        this.product = Product.GetProductfromID(ProductID);
//        FillData(this.product);
    }
	
	
	public void setData(Product p) {
       
//        this.product = Product.GetProductfromID(ProductID);
		this.product=p;
        FillData(this.product);
    }
	
	private void FillData(Product product) {
        if (product == null) return;

        txtProductName.setText(product.getProductName());
        txtQuantityPerUnit.setText(product.getQuantityPerUnit());
        txtUnitPrice.setText(String.valueOf(product.getUnitPrice()));
        ProductID=product.getProductID();
        
        txtproductID.setText(Integer.toString(ProductID));
    }
	
	private Product getUpdatedProductFromForm()
	{
		Product p=new Product();
		p.setProductID(Integer.parseInt(txtproductID.getText()));
		p.setProductName(txtProductName.getText());
		p.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
		p.setQuantityPerUnit(txtQuantityPerUnit.getText());
		//productTable.refresh(); 
		return p;
		
		

	}
	
	
	private EditFormCallback callback; // delegate giả lập

	public void setCallback(EditFormCallback callback) {
	    this.callback = callback;
	}

	@FXML
	private void handleSave(ActionEvent event) {
	    
		Product updatedProduct = getUpdatedProductFromForm(); // tự định nghĩa
	    if (callback != null) {
	        callback.onFormClosed(updatedProduct); // gọi lại callback
	    }

	    // Đóng form
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}
	
	 public void handleClose(ActionEvent event) {
	        // Lấy stage từ sự kiện
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.close(); // Đóng cửa sổ hiện tại
	    }
	 
	 
	
	 
	private Stage stage;

	public void setStage(Stage stage) {
	    this.stage = stage;
	}

	public void closeWindow() {
	    if (stage != null) {
	        stage.close();
	    }
	}
	
	




}

