
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

public class Welcome extends javax.swing.JFrame {
    private JPanel contentPane;
    private JLabel lbladd,lblSell,lblDel,lblUpdate,lblLogout;
    private Image addimg,addimg1;
    private JLabel lblByRajaMajumdar;
    private JLabel lblAddItem;
    private JLabel lblSellItem;
    private JLabel lblUdateItem;
    private JLabel lblDeleteItem;
    private JLabel lblLogoutItem;

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Welcome frame = new Welcome();
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
    public Welcome(){
        initialize();
    }

    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setBounds(100, 100, 1000, 600);
        setSize(1000,600);
        setLocationRelativeTo(null);	
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWelcome = new JLabel("Inventory Management System");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(97, 5, 663, 79);
        contentPane.add(lblWelcome);
        lblWelcome.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 44));

        lblAddItem = new JLabel("");
        lblAddItem.setForeground(Color.WHITE);
        lblAddItem.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblAddItem.setBounds(220, 359, 126, 40);
        contentPane.add(lblAddItem);

        lbladd = new JLabel("");

        lbladd.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/hover1.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Add add=new Add();
                    add.setVisible(true);
                    dispose();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    lbladd.setIcon(new ImageIcon("res/addbig.png"));
                    lblAddItem.setText("Add Item");

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    lbladd.setIcon(new ImageIcon("res/add.png"));
                    lblAddItem.setText("");
                }
            });		
        lbladd.setIcon(new ImageIcon("res/add.png"));
        lbladd.setBounds(216, 409, 94, 99);
        contentPane.add(lbladd);

        lblSellItem = new JLabel("");
        lblSellItem.setForeground(Color.WHITE);
        lblSellItem.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblSellItem.setBounds(386, 359, 105, 27);
        contentPane.add(lblSellItem);

        lblSell = new JLabel("");
        lblSell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    //audio
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/hover1.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Sell sell=new Sell();
                    sell.setVisible(true);
                    dispose();
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lblSell.setIcon(new ImageIcon("res/sellbig.png"));
                    lblSellItem.setText("Sell Item");
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    lblSell.setIcon(new ImageIcon("res/sell.png"));
                    lblSellItem.setText("");
                }
            });
        lblSell.setIcon(new ImageIcon("res/sell.png"));
        lblSell.setBounds(386, 409, 94, 99);
        contentPane.add(lblSell);

        lblUdateItem = new JLabel("");
        lblUdateItem.setForeground(Color.WHITE);
        lblUdateItem.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblUdateItem.setBounds(520, 359, 170, 27);
        contentPane.add(lblUdateItem);

        lblUpdate = new JLabel("");
        lblUpdate.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    //audio
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/hover1.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Update updt=new Update();
                    updt.setVisible(true);
                    dispose();
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lblUpdate.setIcon(new ImageIcon("res/updatebig.png"));
                    lblUdateItem.setText("Update & Search");
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    lblUpdate.setIcon(new ImageIcon("res/update.png"));
                    lblUdateItem.setText("");
                }
            });
        lblUpdate.setIcon(new ImageIcon("res/update.png"));
        lblUpdate.setBounds(557, 409, 94, 99);
        contentPane.add(lblUpdate);

        lblDeleteItem = new JLabel("");
        lblDeleteItem.setForeground(Color.WHITE);
        lblDeleteItem.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblDeleteItem.setBounds(741, 359, 126, 27);
        contentPane.add(lblDeleteItem);

        lblDel = new JLabel("");
        lblDel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    //audio
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/hover1.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Delete del=new Delete();
                    del.setVisible(true);
                    dispose();
                }

                @Override
                public void mouseEntered(MouseEvent arg0) {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lblDel.setIcon(new ImageIcon("res/deletebig.png"));
                    lblDeleteItem.setText("Delete Item");
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    lblDel.setIcon(new ImageIcon("res/delete.png"));
                    lblDeleteItem.setText("");
                }
            });
        lblDel.setIcon(new ImageIcon("res/delete.png"));
        lblDel.setBounds(752, 409, 94, 99);
        contentPane.add(lblDel);

        JLabel lblIcon = new JLabel("");		
        lblIcon.setIcon(new ImageIcon("res/sys.png"));
        lblIcon.setBounds(10, 5, 94, 99);
        contentPane.add(lblIcon);

        lblByRajaMajumdar = new JLabel("By Raja Majumdar");
        lblByRajaMajumdar.setForeground(Color.WHITE);
        lblByRajaMajumdar.setFont(new Font("PrimusW00-Bold", Font.ITALIC, 12));
        lblByRajaMajumdar.setBounds(107, 80, 147, 14);
        contentPane.add(lblByRajaMajumdar);

        lblLogoutItem = new JLabel("");
        lblLogoutItem.setForeground(Color.WHITE);
        lblLogoutItem.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblLogoutItem.setBounds(890, 138, 94, 27);
        contentPane.add(lblLogoutItem);

        lblLogout = new JLabel("");
        lblLogout.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    //audio
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/hover1.wav")));
                        clip.start();
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //return to Login Page
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/notify1.wav")));
                        clip.start();

                        LoginPage.main(null);
                        dispose();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 

                }

                @Override
                public void mouseEntered(MouseEvent arg0) {

                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File("audio/click.wav")));
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    lblLogout.setIcon(new ImageIcon("res/logoutbig.png"));
                    lblLogoutItem.setText("Log out");
                }

                @Override
                public void mouseExited(MouseEvent arg0) {
                    lblLogout.setIcon(new ImageIcon("res/logout.png"));
                    lblLogoutItem.setText("");
                }
            });

        lblLogout.setForeground(Color.WHITE);
        lblLogout.setIcon(new ImageIcon("res/logout.png"));
        lblLogout.setFont(new Font("PrimusW00-Bold", Font.PLAIN, 20));
        lblLogout.setBounds(869, 5, 94, 122);
        contentPane.add(lblLogout);

        JLabel lblBack = new JLabel("");		
        lblBack.setBounds(0, 0, 1000, 573);
        lblBack.setIcon(new ImageIcon("res/honeycomb1.jpg"));
        contentPane.add(lblBack);
    }
}
