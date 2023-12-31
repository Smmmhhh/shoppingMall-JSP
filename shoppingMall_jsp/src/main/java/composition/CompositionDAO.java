package composition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cart.Cart;
import product.Product;
import util.DatabaseUtil;

public class CompositionDAO {

	public List<Composition> getCompositionList(String memberID){
		String SQL = "select * from carts C inner join products P on C.prodID = P.prodID "
				+ "where C.memberID = ?" ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Composition> compositionList = new ArrayList<>();
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, memberID);
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				int prodID = rs.getInt("prodID");
				int prodCtgID = rs.getInt("ctgID");
				String prodName = rs.getString("prodName");
				int prodPrice = rs.getInt("price");
				int prodStock = rs.getInt("stock");
				String prodDetail = rs.getString("detail");
				String prodSize = rs.getString("prodSize");
				String prodOrigin = rs.getString("prodOrigin");
				String prodDate = rs.getString("prodDate");
				int prodValidity = rs.getInt("prodValidity");
			
				int cartID = rs.getInt("cartID");
				int cartQuantity = rs.getInt("cartQuantity");

						
				Product product = new Product(prodID, prodCtgID, prodName, prodPrice, prodStock,
						prodDetail, prodSize, prodOrigin, prodDate, prodValidity);
				
				Cart cart = new Cart(cartID, prodID, memberID, cartQuantity); 
				
				Composition composition = new Composition(cart, product);
			
				compositionList.add(composition);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Database와 연결 후 반드시 해제시켜주기
			try {
				if (rs != null) {
					rs.close(); // ResultSet을 닫음
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return compositionList;
	}
	
	public Composition getCompositionList(String memberID, int getprodID){
		String SQL = "select * from carts C inner join products P on C.prodID = P.prodID "
				+ "where C.memberID =? and C.prodID = ?" ;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Composition composition = null;

		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, memberID);
			pstmt.setInt(2, getprodID);
			rs = pstmt.executeQuery();
		
			if (rs.next()) {
				int prodID = rs.getInt("prodID");
				int prodCtgID = rs.getInt("ctgID");
				String prodName = rs.getString("prodName");
				int prodPrice = rs.getInt("price");
				int prodStock = rs.getInt("stock");
				String prodDetail = rs.getString("detail");
				String prodSize = rs.getString("prodSize");
				String prodOrigin = rs.getString("prodOrigin");
				String prodDate = rs.getString("prodDate");
				int prodValidity = rs.getInt("prodValidity");
			
				int cartID = rs.getInt("cartID");
				int cartQuantity = rs.getInt("cartQuantity");

						
				Product product = new Product(prodID, prodCtgID, prodName, prodPrice, prodStock,
						prodDetail, prodSize, prodOrigin, prodDate, prodValidity);
				
				Cart cart = new Cart(cartID, prodID, memberID, cartQuantity); 
				
				composition = new Composition(cart, product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Database와 연결 후 반드시 해제시켜주기
			try {
				if (rs != null) {
					rs.close(); // ResultSet을 닫음
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return composition;
	}
}
