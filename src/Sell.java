import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Sell extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Sell frame = new Sell();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    Connection conn=null;
    PreparedStatement pst;
    Statement stmt = null;
    ResultSet rs = null;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtUnit;

    /**
     * Create the frame.
     */
    public Sell() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("audio/newwin.wav")));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn=Connectionz.getConnection();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSell = new JLabel("Sell Item(s)");
        lblSell.setForeground(Color.WHITE);
        lblSell.setBounds(378, 9, 343, 80);
        contentPane.add(lblSell);
        lblSell.setFont(new Font("Trajan Pro", Font.BOLD, 50));

        JLabel lblId = new JLabel("ID :");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));
        lblId.setBounds(39, 182, 53, 34);
        contentPane.add(lblId);

        JLabel lblName = new JLabel("NAME :");
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));
        lblName.setBounds(39, 267, 81, 34);
        contentPane.add(lblName);

        JLabel lblPrice = new JLabel("PRICE :");
        lblPrice.setForeground(Color.WHITE);
        lblPrice.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));
        lblPrice.setBounds(39, 350, 81, 34);
        contentPane.add(lblPrice);

        JLabel lblUnit = new JLabel("UNIT(s) :");
        lblUnit.setForeground(Color.WHITE);
        lblUnit.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));
        lblUnit.setBounds(39, 430, 92, 34);
        contentPane.add(lblUnit);

        txtUnit = new JTextField();
        txtUnit.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        txtUnit.setBounds(209, 430, 209, 32);
        contentPane.add(txtUnit);
        txtUnit.setColumns(10);

        txtPrice = new JTextField();
        txtPrice.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        txtPrice.setBounds(209, 350, 209, 34);
        contentPane.add(txtPrice);
        txtPrice.setColumns(10);

        txtName = new JTextField();
        txtName.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        txtName.setBounds(209, 272, 209, 32);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtID = new JTextField();
        txtID.setFont(new Font("Bahnschrift", Font.BOLD, 16));
        txtID.setBounds(209, 187, 209, 32);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JButton btnSell = new JButton("Sell");
        btnSell.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 19));
        btnSell.setBounds(56, 517, 111, 34);
        contentPane.add(btnSell);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 19));
        btnBack.setBounds(247, 517, 111, 34);
        contentPane.add(btnBack);

        Image backg=new ImageIcon("res/kash.jpg").getImage().getScaledInstance(1100, 700,  java.awt.Image.SCALE_SMOOTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(475, 100, 499, 427);
        scrollPane.setBackground(new Color(0,0,0,0));

        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));

        //customized table
        //table.setBackground(new Color(0,0,0,0));
        /*((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        table.setGridColor(Color.WHITE);
        table.setForeground(Color.WHITE);
        table.setOpaque(false);
        table.setShowGrid(true);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        scrollPane.getViewport().setOpaque(false);*/

        table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent arg0) {
                    int i=table.getSelectedRow();
                    TableModel model=table.getModel();
                    txtID.setText(model.getValueAt(i,0).toString());
                    txtName.setText(model.getValueAt(i,1).toString());
                    txtPrice.setText(model.getValueAt(i,2).toString());
                    txtUnit.setText(model.getValueAt(i,3).toString());

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/mouseclick.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        scrollPane.setViewportView(table);

        table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "ID", "NAME", "PRICE", "Unit(s)"
                }
            ));

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("res/sell.png"));
        label.setBounds(280, 0, 64, 89);
        contentPane.add(label);

        JLabel lblItemDetails = new JLabel("Item Details");
        lblItemDetails.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemDetails.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 24));
        lblItemDetails.setForeground(Color.WHITE);		
        lblItemDetails.setBorder(new LineBorder(Color.WHITE, 2));

        lblItemDetails.setBounds(10, 100, 442, 50);
        contentPane.add(lblItemDetails);

        JLabel label_1 = new JLabel("");
        label_1.setForeground(Color.WHITE);
        label_1.setBackground(Color.BLACK);
        label_1.setBorder(new LineBorder(Color.WHITE, 2));
        label_1.setBounds(10, 161, 442, 327);
        contentPane.add(label_1);

        JLabel lblTipsClickOn = new JLabel("Tips: Click on the Item you want to Sell");
        lblTipsClickOn.setForeground(Color.WHITE);
        lblTipsClickOn.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 13));
        lblTipsClickOn.setBounds(733, 527, 251, 24);
        contentPane.add(lblTipsClickOn);

        JLabel lblback = new JLabel("");		
        lblback.setBounds(0, 0, 994, 572);
        lblback.setIcon(new ImageIcon(backg));
        contentPane.add(lblback);

        btnBack.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Welcome welcome = new Welcome();
                    welcome.setVisible(true);
                    dispose();
                }
            });
        btnSell.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    String id = txtID.getText();
                    String name = txtName.getText();
                    String price = txtPrice.getText();
                    String unit = txtUnit.getText();

                    int netunit=0;

                    try{

                        conn=Connectionz.getConnection();
                        Statement st=conn.createStatement();
                        String query="select * from Items where Pdt_Id="+id;
                        ResultSet rs=st.executeQuery(query);
                        while(rs.next()){
                            String val=rs.getString("Pdt_Unit");
                            if(Integer.parseInt(val)>Integer.parseInt(unit)){
                                netunit=Integer.parseInt(val)-Integer.parseInt(unit);

                                User use=new User();
                                use.update(id,name,price,Integer.toString(netunit));
                                load();
                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/confirm.wav")));
                                    clip.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                JOptionPane.showMessageDialog(null, "Sold Successfully...\n_____________________\n\nPRODUCT ID : "+id+"\nNAME : "+name+"\nUNIT SOLD : "+unit+"\nREMAINIG : "+netunit,"Done...", 1);

                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else{
                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/error1.wav")));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                JOptionPane.showMessageDialog(null, "!!! Entered Unit(s) can not be sold !!!","W A R N I N G !", JOptionPane.WARNING_MESSAGE);

                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                                    clip.start();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                    }catch(Exception e){
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/error1.wav")));
                            clip.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(contentPane, "Please fill the required details to proceed!!!", "W A R N I N G !", JOptionPane.WARNING_MESSAGE);

                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                            clip.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            });
        load();
    }

    public void load(){

        try{
            String query="select Pdt_Id,Pdt_Name,Pdt_Price,Pdt_Unit from Items";
            PreparedStatement pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));

            //table.setAutoCreateRowSorter(true);

        }catch(Exception e){
            System.out.println(""+e);	
        }
    }	
}