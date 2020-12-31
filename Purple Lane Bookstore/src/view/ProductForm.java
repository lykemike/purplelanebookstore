package view;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import connect.Connect;

public class ProductForm extends View{

	Connect connect = new Connect();
	JPanel top, mid, bot;
	JTable table;
	JScrollPane scrollPane;
	JLabel labelId, labelName, labelAuthor, labelPrice, labelStock, labelValue;
	JTextField txtName, txtAuthor, txtPrice, txtStock;
	JButton insertButton, updateButton, deleteButton;
	Vector<Vector> data;
	Vector detail, header;
	
	public ProductForm() {
		super();
		this.width = 500;
		this.height = 700;
	}

	@Override
	public void initialize() {
		top = new JPanel();
		mid = new JPanel(new GridLayout(5, 2));
		bot = new JPanel();
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		
		labelId = new JLabel("Product ID: ");
		labelName = new JLabel("Product Name: ");
		labelAuthor = new JLabel("Product Author: ");
		labelPrice = new JLabel("Product Price: ");
		labelStock = new JLabel("Product Stock: ");
		labelValue = new JLabel("-");
		
		txtName = new JTextField();
		txtAuthor = new JTextField();
		txtPrice = new JTextField();
		txtStock = new JTextField();
		
		insertButton = new JButton("Insert");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		
		loadData(connect.executeQuery("SELECT * FROM products"));
		
	}

	@Override
	public void addComponent() {
		top.add(scrollPane);
		
		mid.add(labelId);
		mid.add(labelValue);
		mid.add(labelName);
		mid.add(txtName);
		mid.add(labelAuthor);
		mid.add(txtAuthor);
		mid.add(labelPrice);
		mid.add(txtPrice);
		mid.add(labelStock);
		mid.add(txtStock);
		
		bot.add(insertButton);
		bot.add(updateButton);
		bot.add(deleteButton);
		
		add(top, BorderLayout.NORTH);
		add(mid, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		/*
		 * INSERT NEW PRODUCT
		 */
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String author = txtAuthor.getText();
				Integer price = 0;
				Integer stock = 0;
				
				try {
					price = Integer.parseInt(txtPrice.getText());
					stock = Integer.parseInt(txtStock.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Price and stock must be integer");
					return;
				}
				
				String query = String.format("INSERT INTO products VALUES(NULL, ?, ?, ?, ?)");
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				
				try {
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, author);
					preparedStatement.setInt(3, price);
					preparedStatement.setInt(4, stock);
					preparedStatement.executeUpdate();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				loadData(connect.executeQuery("SELECT * FROM products"));
			}
		});
		
		/* 
		 * DISPLAY PRODUCT INFORMATION WHEN CLICKED
		 */
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				labelValue.setText(table.getValueAt(row, 0).toString());
				txtName.setText(table.getValueAt(row, 1).toString());
				txtAuthor.setText(table.getValueAt(row, 2).toString());
				txtPrice.setText(table.getValueAt(row, 3).toString());
				txtStock.setText(table.getValueAt(row, 4).toString());
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		/*
		 * UPDATE NEW PRODUCT
		 */
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(labelValue.getText());
				String name = txtName.getText();
				String author = txtAuthor.getText();
				Integer price = 0;
				Integer stock = 0;
				
				try {
					price = Integer.parseInt(txtPrice.getText());
					stock = Integer.parseInt(txtStock.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Price and stock must be integer");
					return;
				}
				
				String query = String.format("UPDATE products SET ProductName = ?, ProductAuthor =  ?, ProductPrice = ?, ProductStock = ? WHERE ProductId = ?");
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				
				try {
					preparedStatement.setString(1, name);
					preparedStatement.setString(2, author);
					preparedStatement.setInt(3, price);
					preparedStatement.setInt(4, stock);
					preparedStatement.setInt(5, id);
					preparedStatement.executeUpdate();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				loadData(connect.executeQuery("SELECT * FROM products"));
			}
		});
		
		
		/*
		 * DELETE NEW PRODUCT
		 */
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(labelValue.getText());
				
				String query = String.format("DELETE FROM products where ProductId = ?");
				PreparedStatement preparedStatement = connect.prepareStatement(query);
				try {
					preparedStatement.setInt(1, id);
					preparedStatement.executeUpdate();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				loadData(connect.executeQuery("SELECT * FROM products"));
			}
		});
	}
	
	private void loadData(ResultSet resultSet) {
		header = new Vector<>();
		data = new Vector<>();
		
		header.add("Product ID");
        header.add("Name");
        header.add("Author");
        header.add("Price");
        header.add("Stock");
        
        try {
			while(resultSet.next()) {
				Integer id = resultSet.getInt("ProductId");
				String name = resultSet.getString("ProductName");
				String author = resultSet.getString("ProductAuthor");
				Integer price = resultSet.getInt("ProductPrice");
				Integer stock = resultSet.getInt("ProductStock");
				
				detail = new Vector<>();
				detail.add(id+"");
				detail.add(name);
				detail.add(author);
				detail.add(price+"");
				detail.add(stock+"");
				
				data.add(detail);
				}
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        DefaultTableModel dtm = new DefaultTableModel(data, header) {
        	
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        table.setModel(dtm);
        
	}

}
