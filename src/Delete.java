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
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Delete extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Delete frame = new Delete();
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

    public Delete() {
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

        Image backg=new ImageIcon("res/leaves.jpg").getImage().getScaledInstance(1000, 600,  java.awt.Image.SCALE_SMOOTH);

        JLabel lblTipsClickOn = new JLabel("Tips: Click on the Item you want to Delete");
        lblTipsClickOn.setForeground(Color.WHITE);
        lblTipsClickOn.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 13));
        lblTipsClickOn.setBounds(723, 538, 251, 24);
        contentPane.add(lblTipsClickOn);

        JLabel lblItemDetails = new JLabel("Item Details");
        lblItemDetails.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemDetails.setFont(new Font("PrimusW00-Bold", Font.BOLD, 24));
        lblItemDetails.setForeground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.white, 1);
        lblItemDetails.setBorder(border);

        lblItemDetails.setBounds(10, 100, 442, 46);
        contentPane.add(lblItemDetails);

        JLabel lblUpdate = new JLabel("Delete Item(s)");
        lblUpdate.setForeground(Color.WHITE);
        lblUpdate.setBounds(356, 9, 442, 80);
        contentPane.add(lblUpdate);
        lblUpdate.setFont(new Font("Trajan Pro", Font.BOLD, 50));

        JButton btnUpdate = new JButton("Delete");
        btnUpdate.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 19));
        btnUpdate.setBounds(56, 517, 111, 34);
        contentPane.add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 19));
        btnBack.setBounds(307, 517, 111, 34);
        contentPane.add(btnBack);

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

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(477, 100, 497, 432);
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

        JLabel label_1 = new JLabel("");
        label_1.setForeground(Color.WHITE);
        label_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
        label_1.setBounds(10, 157, 442, 331);
        contentPane.add(label_1);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("res/delete.png"));
        label.setBounds(273, 11, 92, 78);
        contentPane.add(label);

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
        btnUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    String id = txtID.getText();

                    User use=new User();

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
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        int ok = JOptionPane.showConfirmDialog(contentPane, "Confirm to delete", "Are you sure...", JOptionPane.YES_NO_OPTION);
                        if(ok==0){

                            try {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("audio/confirm.wav")));
                                clip.start();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            use.delete(id);
                            JOptionPane.showMessageDialog(contentPane, "Data is deleted...Successfully", "Done...", 1);
                            txtID.setText(null);
                            txtName.setText(null);
                            txtPrice.setText(null);
                            txtUnit.setText(null);
                            load();

                            try {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                                clip.start();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
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
