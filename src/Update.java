
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
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

import javax.swing.border.LineBorder;

public class Update extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Update frame = new Update();
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
    private JTextField txtSearch;

    /**
     * Create the frame.
     */
    public Update() {
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

        Image backg=new ImageIcon("res/sun.jpg").getImage().getScaledInstance(1000, 600,  java.awt.Image.SCALE_SMOOTH);

        JLabel lblUpdate = new JLabel("Search & Update");
        lblUpdate.setForeground(Color.WHITE);
        lblUpdate.setBounds(267, 11, 472, 61);
        contentPane.add(lblUpdate);
        lblUpdate.setFont(new Font("Trajan Pro", Font.PLAIN, 50));

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 18));
        btnUpdate.setBounds(96, 517, 100, 34);
        contentPane.add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 18));
        btnBack.setBounds(282, 517, 100, 34);
        contentPane.add(btnBack);
        Border border = BorderFactory.createLineBorder(Color.white, 1);

        JLabel lblUpdateStock = new JLabel("Update Stock");
        lblUpdateStock.setHorizontalAlignment(SwingConstants.CENTER);
        lblUpdateStock.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 26));
        lblUpdateStock.setForeground(Color.WHITE);
        lblUpdateStock.setBorder(border);

        lblUpdateStock.setBounds(10, 99, 442, 45);
        contentPane.add(lblUpdateStock);

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
        txtUnit.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
        txtUnit.setBounds(209, 430, 209, 32);
        contentPane.add(txtUnit);
        txtUnit.setColumns(10);

        txtPrice = new JTextField();
        txtPrice.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
        txtPrice.setBounds(209, 350, 209, 34);
        contentPane.add(txtPrice);
        txtPrice.setColumns(10);

        txtName = new JTextField();
        txtName.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
        txtName.setBounds(209, 272, 209, 32);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtID = new JTextField();
        txtID.setFont(new Font("Lucida Handwriting", Font.BOLD, 16));
        txtID.setBounds(209, 187, 209, 32);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblTipsClickOn = new JLabel("Tips: Click on the Item you want to Update");
        lblTipsClickOn.setForeground(Color.WHITE);
        lblTipsClickOn.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 13));
        lblTipsClickOn.setBounds(707, 537, 263, 24);
        contentPane.add(lblTipsClickOn);

        JLabel lblSearch = new JLabel("Search :");
        lblSearch.setForeground(Color.WHITE);
        lblSearch.setBounds(535, 134, 100, 33);
        contentPane.add(lblSearch);
        lblSearch.setFont(new Font("PrimusW00-Bold", Font.BOLD, 26));

        txtSearch = new JTextField();
        txtSearch.setBounds(645, 133, 303, 34);
        contentPane.add(txtSearch);
        txtSearch.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DefaultTableModel tabel=(DefaultTableModel)table.getModel();
                    String search=txtSearch.getText();
                    TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(tabel);
                    table.setRowSorter(tr);
                    tr.setRowFilter(RowFilter.regexFilter(search));
                }
            });
        txtSearch.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(477, 206, 497, 321);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Lucida Handwriting", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 20));
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
        label.setBounds(477, 99, 497, 96);
        label.setBorder(border);
        contentPane.add(label);

        JLabel label_2 = new JLabel("");
        label_2.setIcon(new ImageIcon("res/update.png"));
        label_2.setBounds(176, 0, 81, 77);
        contentPane.add(label_2);

        JLabel lblback = new JLabel("");		
        lblback.setBounds(0, 0, 1027, 572);
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
        btnUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String id = txtID.getText();
                    String name = txtName.getText();
                    String price = txtPrice.getText();
                    String unit = txtUnit.getText();

                    User use=new User();
                    use.update(id, name, price, unit);
                    load();
                    if(id.isEmpty()){

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
                    else{
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/confirm.wav")));
                            clip.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(null, "Update Successfull...","Done...", 1);

                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                            clip.start();
                        } catch (Exception e) {
                            e.printStackTrace();
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

        }catch(Exception e){
            System.out.println(""+e);	
        }
    }	
}

