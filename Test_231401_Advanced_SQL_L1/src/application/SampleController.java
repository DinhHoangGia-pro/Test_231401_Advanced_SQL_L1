package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;



public class SampleController 
{
	String event="UPDATE";
	@FXML	
	public TextField txtSearch, txt2, txt3;
	
	@FXML
	private Button btnAdd, btnEdit, btnDel, btnSave, btnSearch, btnCancel;
	
	@FXML
	private TableView<Product> productTable;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, Double> priceColumn;
	@FXML
	private TableColumn<Product, Integer> quantityColumn;
	
	@FXML
	private TableColumn<Product, String> clSupplierID;
	@FXML
	private TableColumn<Product, String> clCategoryID;
	
	private ObservableList<Product> productList =
	FXCollections.observableArrayList();
	
	private Product selectedProduct;
	int id=0;
	
	
	@FXML
	public void initialize() {
	nameColumn.setCellValueFactory(new
	PropertyValueFactory<>("productName"));
	priceColumn.setCellValueFactory(new
	PropertyValueFactory<>("unitPrice"));
	quantityColumn.setCellValueFactory(new
	PropertyValueFactory<>("quantityPerUnit"));
	
	clSupplierID.setCellValueFactory(new
			PropertyValueFactory<>("CompanyName"));
	clCategoryID.setCellValueFactory(new
			PropertyValueFactory<>("CategoryName"));
	
	productTable.setItems(productList);
	
//	Product newProduct = new Product(1,"Bia Hà Nội",  "10 lon x 1 thung", 300.0);
//	productList.add(newProduct);
//	
//	newProduct = new Product(2,"Bia Sài Gòn",  "10 lon x thung", 300.0);
//	productList.add(newProduct);
//	
//	newProduct = new Product(3,"Khoai tây", "bich 1kg", 100.0);
//	productList.add(newProduct);
	loadProducts();
	
	//setEnable(false);/*Khong cho sua*/
	
	}
	
	public void setEnable(boolean dk)
	{
		txtSearch.setEditable(dk);
		
		
		btnAdd.setDisable(false);
		//btnEdit.setDisable(false);
		btnDel.setDisable(false);
		
	}
	
	@FXML
	public void loadProducts() 
		{
		//Xóa sạch dữ liệu của giao diện trước khi add
		productTable.getSelectionModel().clearSelection();
		productTable.getItems().clear();
		
		//setEnable(false);/*Khong cho sua*/
		
		String query = "SELECT ProductID, ProductName,QuantityPerUnit, UnitPrice,\r\n"
				+ "P.SupplierID, P.CategoryID, C.CategoryName, S.CompanyName FROM Products P \r\n"
				+ "JOIN Categories C ON P.CategoryID=C.CategoryID \r\n"
				+ "JOIN Suppliers S ON P.SupplierID=S.SupplierID";
		try (Connection conn = DataConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		
		ResultSet rs = pstmt.executeQuery()) 
		{
				
				while (rs.next()) {
					Product product = new Product();
					product.setProductID(rs.getInt("ProductID"));					
					product.setProductName(rs.getString("ProductName"));
					product.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
					product.setUnitPrice(rs.getDouble("UnitPrice"));
					product.setCompanyName(rs.getString("CompanyName"));
					product.setCategoryName(rs.getString("CategoryName"));				
					productList.add(product);
				}
				productTable.setItems(productList);
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	@FXML
	public void searchProduct()
	{
		productTable.getSelectionModel().clearSelection();
		productTable.getItems().clear();
		
				
		String chuoitim=txtSearch.getText();
		String query = "SELECT P.ProductID, P.ProductName,P.QuantityPerUnit, \r\n"
				+ "P.UnitPrice,P.SupplierID,P.CategoryID, S.CompanyName, C.CategoryName FROM Products P\r\n"
				+ "JOIN Suppliers S ON P.SupplierID=S.SupplierID\r\n"
				+ "JOIN Categories C ON P.CategoryID=C.CategoryID \r\n"
				+ " where P.ProductName LIKE '%"+chuoitim+"%'"
				+ " OR C.CategoryName LIKE '%" +chuoitim +"%'"
				+ " OR S.CompanyName LIKE '%" +chuoitim+ "%'";
		try (
				Connection conn = DataConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query))
		{
			
		/*
		 
		 
		 'UNION select id ProductID, username+','+[password] ProductName,null QuantityPerUnit,
		 0 UnitPrice, 0 SupplierID, 0 CategoryID, null CompanyName,null CategoryName from tbl_User--
		 
		 
		 'UNION select 0 ProductID, username ProductName, [password] QuantityPerUnit, 
		 0 UnitPrice, 0 SupplierID, 0 CategoryID, null CompanyName,null CategoryName from tbl_user--
		  */
				
//	     pstmt.setString(1, "%" + txtSearch.getText() + "%");			
//	     pstmt.setString(2, "%" + txtSearch.getText() + "%");
//	     pstmt.setString(3, "%" + txtSearch.getText() + "%");
		 ResultSet rs = pstmt.executeQuery();
		
				
				while (rs.next()) {
					Product product = new Product();
					product.setProductID(rs.getInt("ProductID"));					
					product.setProductName(rs.getString("ProductName"));
					product.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
					product.setUnitPrice(rs.getDouble("UnitPrice"));
					product.setCompanyName(rs.getString("CompanyName"));
					product.setCategoryName(rs.getString("CategoryName"));				
					productList.add(product);
				}
				productTable.setItems(productList);
		}
		
		catch (SQLException e) {
		e.printStackTrace();
		}
		
	}
	
	
	@FXML
	public void searchProduct_fix()
	{
		productTable.getSelectionModel().clearSelection();
		productTable.getItems().clear();
		
				
		String chuoitim=txtSearch.getText();
		String query = "SELECT P.ProductID, P.ProductName,P.QuantityPerUnit, \r\n"
				+ "P.UnitPrice,P.SupplierID,P.CategoryID, S.CompanyName, C.CategoryName FROM Products P\r\n"
				+ "JOIN Suppliers S ON P.SupplierID=S.SupplierID\r\n"
				+ "JOIN Categories C ON P.CategoryID=C.CategoryID \r\n"
				+ " where P.ProductName LIKE ?"
				+ " OR C.CategoryName LIKE ?"
				+ " OR S.CompanyName LIKE ?";
		try (
				Connection conn = DataConnection.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query))
		{
				
	     pstmt.setString(1, "%" + txtSearch.getText() + "%");			
	     pstmt.setString(2, "%" + txtSearch.getText() + "%");
	     pstmt.setString(3, "%" + txtSearch.getText() + "%");
		 ResultSet rs = pstmt.executeQuery();
		
				
				while (rs.next()) {
					Product product = new Product();
					product.setProductID(rs.getInt("ProductID"));					
					product.setProductName(rs.getString("ProductName"));
					product.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
					product.setUnitPrice(rs.getDouble("UnitPrice"));
					product.setCompanyName(rs.getString("CompanyName"));
					product.setCategoryName(rs.getString("CategoryName"));				
					productList.add(product);
				}
				productTable.setItems(productList);
		}
		
		catch (SQLException e) {
		e.printStackTrace();
		}
		
	}
	
	@FXML
	public void selectProduct() {
		
	selectedProduct = productTable.getSelectionModel().getSelectedItem();
	
	if (selectedProduct != null) {
				
		Goto_EditForm(selectedProduct);
	}
	}
	
	
	
	private void Goto_EditForm(Product p) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("frm_Edit.fxml"));
	        BorderPane root = loader.load(); // Load FXML và lấy root
	        
	        // Lấy controller từ FXMLLoader
	        frmEditController controller = loader.getController();
	        
	        // Truyền tham số vào controller
	       
	        
	        controller.setData(p);
	        //controller.setStage(null);
	        
	     // Gán callback (delegate kiểu Java)
	        controller.setCallback(updatedProduct -> {
	            Capnhatdulieu(updatedProduct);
	        	//System.out.println("Form đã đóng. Dữ liệu mới: " + updatedProduct.getName());
	            
	        });
	        
	        // Tạo scene và stage
	        Scene scene = new Scene(root, 735, 390);
	        Stage stage = new Stage();
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        
	        stage.setScene(scene);
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	void Capnhatdulieu(Product p)
	{
		System.out.println("Du lieu nhan ve:");
		System.out.println(p.getProductID());
		System.out.println(p.getUnitPrice());
		System.out.println(p.getQuantityPerUnit());
		
		if (p.getProductID()==0)
		{
			/*Thêm mới*/
			productList.add(p);
			productTable.refresh();
			return;
		}
		
		// Tìm sản phẩm trong danh sách theo ID
	    for (Product prod : productList) {
	        if (prod.getProductID() == p.getProductID()) {
	            // Cập nhật thông tin
	            prod.setProductName(p.productName);
	            prod.setUnitPrice(id);
	            prod.setQuantityPerUnit(p.quantityPerUnit);
	            
	            // Làm mới lại bảng
	            productTable.refresh();
	            break;
	        }
	    }
	}
	
	@FXML
	public void addProduct_event()
	{
		Product p =new Product();
		
		Goto_EditForm(p);
		
		
	}
	
		
	
	@FXML
	public void hnd_deleteProduct() {
			Product selectedProduct =
			productTable.getSelectionModel().getSelectedItem();
			if (selectedProduct != null) {
			
				if (Global.Question("Bạn có chắc chắn xóa")==true)
				{
				productList.remove(selectedProduct);
				}
			}
	}
	
	@FXML
	public void handleClose(ActionEvent event) {
        // Lấy stage từ sự kiện
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Đóng cửa sổ hiện tại
    }
	
	
	
}
