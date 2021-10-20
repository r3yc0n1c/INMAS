import java.awt.EventQueue;
import java.sql.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.*;

import javax.swing.UIManager;

public class LoginPage {

    private JFrame frame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        LoginPage window = new LoginPage();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    /**
     * Create the application.
     */
    public LoginPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBackground(new Color(0,0,0,0));
        frame.getContentPane().setForeground(new Color(0, 0, 0));
        frame.getContentPane().setBackground(Color.WHITE);
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 30));

        //frame.setBounds(200, 200, 410, 414);
        frame.setSize(600, 338);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel lblNewLabel = new JLabel("STOCK MANAGEMENT SYSTEM");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setFont(new Font("PrimusW00-Bold", Font.BOLD, 28));
        lblNewLabel.setBounds(65, 0, 457, 51);
        frame.getContentPane().add(lblNewLabel);

        JButton btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setFont(new Font("PrimusW00-Bold", Font.BOLD, 18));
        btnLogin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {	

                    String password= txtPassword.getText();		//password=123
                    String username= txtUsername.getText();		//userName="admin";

                    if (password.contains("123") && username.contains("admin")){

                        frame.dispose();    
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/Open.wav")));
                            clip.start();

                            Clip clip1 = AudioSystem.getClip();
                            clip1.open(AudioSystem.getAudioInputStream(new File("audio/notify.wav")));
                            clip1.start();

                            Thread.sleep(2000);

                            Clip clip2 = AudioSystem.getClip();
                            clip2.open(AudioSystem.getAudioInputStream(new File("audio/welcome.wav")));
                            clip2.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        Welcome welcome=new Welcome();
                        welcome.setVisible(true);	
                    }
                    else
                    {
                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/Open.wav")));
                            clip.start();

                            Clip clip1 = AudioSystem.getClip();
                            clip1.open(AudioSystem.getAudioInputStream(new File("audio/error1.wav")));
                            clip1.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(null,"Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
                        txtPassword.setText(null);
                        txtUsername.setText(null);
                    }

                }
            });
        btnLogin.setBounds(254, 257, 122, 32);
        frame.getContentPane().add(btnLogin);

        JButton btnExit = new JButton("Exit");
        btnExit.setForeground(Color.WHITE);
        btnExit.setBackground(new Color(220, 20, 60));
        btnExit.setFont(new Font("PrimusW00-Bold", Font.BOLD, 18));
        btnExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/Open.wav")));
                        clip.start();

                        Clip clip1 = AudioSystem.getClip();
                        clip1.open(AudioSystem.getAudioInputStream(new File("audio/exit.wav")));
                        clip1.start();				
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    JFrame frmLoginSystem = new JFrame("Exit");
                    if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login system",
                        JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION){

                        try {
                            Clip clip = AudioSystem.getClip();
                            clip.open(AudioSystem.getAudioInputStream(new File("audio/Open.wav")));
                            clip.start();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        System.exit(0);
                    }
                }
            });
        btnExit.setBounds(421, 257, 122, 32);
        frame.getContentPane().add(btnExit);
        //Image backg=new ImageIcon("/res/back1.gif").getImage();

        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setBounds(247, 60, 129, 32);
        frame.getContentPane().add(lblUsername);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        txtUsername = new JTextField();
        txtUsername.setBounds(247, 103, 300, 38);
        frame.getContentPane().add(txtUsername);
        txtUsername.setForeground(Color.BLACK);
        txtUsername.setBackground(Color.WHITE);
        txtUsername.setFont(new Font("Lucida Handwriting", Font.PLAIN, 22));
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setBounds(247, 165, 149, 38);
        frame.getContentPane().add(lblPassword);
        lblPassword.setBackground(UIManager.getColor("Button.background"));
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("PrimusW00-Bold", Font.BOLD, 20));

        txtPassword = new JPasswordField();
        txtPassword.setBounds(247, 203, 300, 38);
        frame.getContentPane().add(txtPassword);
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setFont(new Font("Lucida Handwriting", Font.PLAIN, 22));

        JLabel lblBackImg = new JLabel("");
        lblBackImg.setIcon(new ImageIcon("res/back1.gif"));
        lblBackImg.setBounds(0, 0, 594, 313);
        frame.getContentPane().add(lblBackImg);
    }
}
