
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.io.File;

import javax.swing.UIManager;

public class Add extends JFrame {
    private JPanel contentPane;
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtPrice;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String uname;
    private JTextField txtUnit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Add frame = new Add();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the frame.
     */
    public Add() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("audio/newwin.wav")));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 714, 608);
        setSize(700,500);
        setResizable(false);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblStockManager = new JLabel("Add  Item");
        lblStockManager.setBounds(216, 0, 194, 75);
        contentPane.add(lblStockManager);
        lblStockManager.setForeground(Color.BLACK);
        lblStockManager.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 40));

        JLabel lblNewLabel = new JLabel("ID :");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBounds(276, 101, 48, 30);
        contentPane.add(lblNewLabel);
        lblNewLabel.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        JLabel lblNewLabel_1 = new JLabel("NAME :");
        lblNewLabel_1.setForeground(Color.BLACK);
        lblNewLabel_1.setBounds(276, 165, 77, 30);
        contentPane.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        JLabel lblNewLabel_2 = new JLabel("PRICE :");
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setBounds(276, 245, 89, 30);
        contentPane.add(lblNewLabel_2);
        lblNewLabel_2.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        JLabel lblNoOfUnit = new JLabel("No of Unit :");
        lblNoOfUnit.setForeground(Color.BLACK);
        lblNoOfUnit.setBounds(276, 309, 132, 30);
        contentPane.add(lblNoOfUnit);
        lblNoOfUnit.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        txtID = new JTextField();
        txtID.setBounds(416, 104, 240, 30);
        contentPane.add(txtID);
        txtID.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        txtID.setColumns(10);

        txtName = new JTextField();
        txtName.setBounds(416, 168, 240, 30);
        contentPane.add(txtName);
        txtName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        txtName.setColumns(10);

        txtPrice = new JTextField();
        txtPrice.setBounds(416, 245, 240, 30);
        contentPane.add(txtPrice);
        txtPrice.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        txtPrice.setColumns(10);

        txtUnit = new JTextField();
        txtUnit.setBounds(416, 309, 240, 30);
        contentPane.add(txtUnit);
        txtUnit.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        txtUnit.setColumns(10);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setBackground(UIManager.getColor("Button.background"));
        btnAdd.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 16));
        btnAdd.setBounds(177, 406, 111, 30);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("BACK");
        btnBack.setBackground(UIManager.getColor("Button.background"));
        btnBack.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 16));
        btnBack.setBounds(387, 406, 111, 30);
        contentPane.add(btnBack);

        Image backg=new ImageIcon("res/backgroun.png").getImage().getScaledInstance(700, 500,  java.awt.Image.SCALE_SMOOTH);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("res/admin.png"));
        label.setBounds(-16, 108, 281, 264);
        contentPane.add(label);

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("res/add.png"));
        label_1.setBounds(137, 0, 69, 74);
        contentPane.add(label_1);

        JLabel lblback = new JLabel("");		
        lblback.setBounds(0, 0, 694, 483);
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
        btnAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    String id = txtID.getText();
                    String name = txtName.getText();
                    String price = txtPrice.getText();
                    String unit = txtUnit.getText();

                    if(id.isEmpty()||name.isEmpty()||price.isEmpty()||unit.isEmpty()){
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/error1.wav")));
                            clip.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(contentPane, "Please fill the required details to proceed!!!", "W A R N I N G !", JOptionPane.WARNING_MESSAGE);

                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                            clip.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try{
                            con=Connectionz.getConnection();
                            pst=con.prepareStatement("insert into Items (Pdt_Id,Pdt_Name,Pdt_Price,Pdt_Unit) values (?,?,?,?)");

                            pst.setString(1, id);
                            pst.setString(2, name);
                            pst.setString(3, price);
                            pst.setString(4, unit);

                            try {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("audio/confirm.wav")));
                                clip.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            int ok = JOptionPane.showConfirmDialog(null, "Confirm to store", "Are you sure...", JOptionPane.YES_NO_OPTION);
                            if(ok==0){

                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/confirm.wav")));
                                    clip.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                pst.execute();
                                JOptionPane.showMessageDialog(null, "Data is stored...Successfully\n_____________________\n\nPRODUCT ID : "+id+"\nNAME : "+name+"\nPRICE PER UNIT : "+price+"\nUNIT : "+unit, "Done...", 1);
                                txtID.setText(null);
                                txtName.setText(null);
                                txtPrice.setText(null);
                                txtUnit.setText(null);

                                try {
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(AudioSystem.getAudioInputStream(new File("audio/open.wav")));
                                    clip.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }catch(Exception e){

                            try {
                                Clip clip = AudioSystem.getClip();
                                clip.open(AudioSystem.getAudioInputStream(new File("audio/error1.wav")));
                                clip.start();
                            } catch (Exception ex) {
                                e.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(null, "Item already exists with this Index","W A R N I N G", JOptionPane.WARNING_MESSAGE);	
                        }
                    }
                }
            });
    }
}
