/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_card;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import javax.smartcardio.CardException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ducvu
 */
public class Form_KhoiTao extends javax.swing.JFrame {

    private SmartCardService smartCardService;
    Integer id = null;
    private Card card;
    private ArrayList<Card> list;
    private ArrayList<Log> listLog;
    private DAO dao;
    int i = 1, j = 1;
    DefaultTableModel model;
    DefaultTableModel modelTabLog;
    Boolean statusQuetThe = false;
    byte[] imgCreate, imgPreview;

    public Form_KhoiTao() throws NoSuchPaddingException, NoSuchAlgorithmException {
        smartCardService = new SmartCardService();
        card = new Card();
        dao = new DAO();
        initComponents();
        model = (DefaultTableModel) jTable1.getModel();
        modelTabLog = (DefaultTableModel) jTable2.getModel();
        list = dao.getListCard();
        listLog = dao.getListLog(null);
        this.setLocationRelativeTo(null);
        showTable();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        text_ngaysinh.setFormats(dateFormat);
    }

    public void showTable() {
        for (Card c : list) {
            model.addRow(new Object[]{
                i++, c.getHoTen(), c.getNgaySinh(), c.getBienSoXe(), c.getGioiTinh(), c.getThongTinNha()
            });
        }
        for (Log c : listLog) {
            modelTabLog.addRow(new Object[]{
                j++, c.getHoTen(), c.getNgaySinh(), c.getBienSoXe(), c.getGioiTinh(), c.getThongTinNha(), c.getNgayQuetThe(), c.getType()
            });
        }
    }

    public void checkInit(){
        if(smartCardService.isConnected){
            smartCardService.getId();
            if(smartCardService.id == 0){
                JOptionPane.showMessageDialog(rootPane, "Thẻ chưa được khởi tạo!");
                btnLock.setEnabled(false);
                btnClear.setEnabled(false);
                btnUnlock.setEnabled(false);
                btnLogin.setEnabled(false);
                btnSwipe.setEnabled(false);
                swipeBtnChangeImage.setEnabled(false);
                btnUpdatePin.setEnabled(false);
                btnUpdateInfo.setEnabled(false);

                btnOk.setEnabled(true);
            } else {
                btnOk.setEnabled(false);

                btnLock.setEnabled(true);
                btnClear.setEnabled(true);
                btnUnlock.setEnabled(true);
                btnLogin.setEnabled(true);
                btnSwipe.setEnabled(true);
                swipeBtnChangeImage.setEnabled(true);
                btnUpdatePin.setEnabled(true);
                btnUpdateInfo.setEnabled(true);

                try {
                    smartCardService.setPublicKey(dao.getPublicKey(smartCardService.id));
                } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            btnLock.setEnabled(false);
            btnClear.setEnabled(false);
            btnUnlock.setEnabled(false);
            btnLogin.setEnabled(false);
            btnSwipe.setEnabled(false);
            swipeBtnChangeImage.setEnabled(false);
            btnUpdatePin.setEnabled(false);
            btnUpdateInfo.setEnabled(false);

            btnOk.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        buttton_remove = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        radio_nam = new javax.swing.JRadioButton();
        radio_nu = new javax.swing.JRadioButton();
        radio_khac = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        text_mapin = new javax.swing.JPasswordField();
        text_xnmapin = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        text_hoten = new javax.swing.JTextField();
        text_biensoxe = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        text_thongtinnha = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        jImage = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        btnPickImage = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        text_ngaysinh = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttton_removeLog = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        text_search = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        radio_nam1 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        radio_nu1 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        radio_khac1 = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        text_hoten1 = new javax.swing.JTextField();
        text_mapin1 = new javax.swing.JPasswordField();
        text_ngaysinh1 = new javax.swing.JTextField();
        text_xnmapin1 = new javax.swing.JPasswordField();
        text_biensoxe1 = new javax.swing.JTextField();
        text_thongtinnha1 = new javax.swing.JTextField();
        jImagePreview = new javax.swing.JLabel();
        swipeBtnChangeImage = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        swipeBtnConnect = new javax.swing.JButton();
        btnSwipe = new javax.swing.JButton();
        btnUpdatePin = new javax.swing.JButton();
        btnUnlock = new javax.swing.JButton();
        btnLock = new javax.swing.JButton();
        btnUpdateInfo = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1107, 530));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        buttton_remove.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        buttton_remove.setText("Xóa");
        buttton_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttton_removeActionPerformed(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnConnect.setText("Kết nối");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_nam);
        radio_nam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_nam.setText("Nam");
        radio_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_namActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_nu);
        radio_nu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_nu.setText("Nữ");
        radio_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_nuActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_khac);
        radio_khac.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_khac.setText("Khác");
        radio_khac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_khacActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Xác nhận mã PIN: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Mã PIN: ");

        text_mapin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_mapin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_mapinActionPerformed(evt);
            }
        });

        text_xnmapin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_xnmapin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_xnmapinActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Họ tên: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Ngày sinh: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Biển số xe: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Thông tin nhà:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Giới tính: ");

        text_hoten.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        text_biensoxe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên", "Ngày sinh", "Biển số xe", "Giới tính", "Thông tin nhà"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        text_thongtinnha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        btnOk.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnOk.setText("Lưu");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnOkActionPerformed(evt);
                } catch (CardException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jImage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImage.setText("3x4");
        jImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnExit.setText("Thoát");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnPickImage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnPickImage.setText("Chọn ảnh");
        btnPickImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPickImageActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(54, 33, 89));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Thông tin cơ bản của thẻ người dùng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(383, 383, 383)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnPickImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(209, 209, 209)
                        .addComponent(buttton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(268, 268, 268))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_mapin)
                            .addComponent(text_thongtinnha)
                            .addComponent(text_hoten)
                            .addComponent(text_biensoxe)
                            .addComponent(text_xnmapin, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radio_nam)
                                .addGap(15, 15, 15)
                                .addComponent(radio_nu)
                                .addGap(15, 15, 15)
                                .addComponent(radio_khac)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(text_ngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(584, 584, 584))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnPickImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(text_ngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_biensoxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_nam)
                            .addComponent(radio_nu)
                            .addComponent(radio_khac)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_thongtinnha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_mapin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(text_xnmapin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttton_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TT cơ bản thẻ", jPanel1);

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ tên", "Ngày sinh", "Biển số xe", "Giới tính", "Thông tin nhà", "Ngày quẹt thẻ", "Loại"
            }
        ));
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(6).setMinWidth(150);
            jTable2.getColumnModel().getColumn(7).setMinWidth(150);
        }

        buttton_removeLog.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        buttton_removeLog.setText("Xóa");
        buttton_removeLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttton_removeLogActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Tìm kiếm/Lọc:");

        text_search.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        text_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                none(evt);
            }
        });
        text_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_searchKeyPressed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(54, 33, 89));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Lịch sử cơ bản của thẻ người dùng");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 922, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(304, 304, 304)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(text_search, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttton_removeLog, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(466, 466, 466))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(text_search, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttton_removeLog, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.addTab("Lịch sử quẹt thẻ", jPanel2);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Họ tên: ");

        radio_nam1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_nam1.setText("Nam");
        radio_nam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_nam1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Ngày sinh: ");

        radio_nu1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_nu1.setText("Nữ");
        radio_nu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_nu1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("Biển số xe: ");

        radio_khac1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        radio_khac1.setText("Khác");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Thông tin nhà:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setText("Xác nhận mã PIN: ");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel17.setText("Giới tính: ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel18.setText("Mã PIN: ");

        text_hoten1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_hoten1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_hoten1ActionPerformed(evt);
            }
        });

        text_mapin1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_mapin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_mapin1ActionPerformed(evt);
            }
        });

        text_ngaysinh1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        text_xnmapin1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        text_xnmapin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_xnmapin1ActionPerformed(evt);
            }
        });

        text_biensoxe1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        text_thongtinnha1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jImagePreview.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jImagePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImagePreview.setText("Hình ảnh");
        jImagePreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        swipeBtnChangeImage.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        swipeBtnChangeImage.setText("Thay ảnh");
        swipeBtnChangeImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swipeBtnChangeImageActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(54, 33, 89));

        swipeBtnConnect.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        swipeBtnConnect.setText("Kết nối");
        swipeBtnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swipeBtnConnectActionPerformed(evt);
            }
        });

        btnSwipe.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnSwipe.setText("Quẹt thẻ");
        btnSwipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwipeActionPerformed(evt);
            }
        });

        btnUpdatePin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUpdatePin.setText("Thay đổi mã pin");
        btnUpdatePin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePinActionPerformed(evt);
            }
        });

        btnUnlock.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUnlock.setText("Mở khoá thẻ");
        btnUnlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnUnlockActionPerformed(evt);
                } catch (NoSuchPaddingException | BadPaddingException | CardException | InvalidKeyException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnLock.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLock.setText("Khoá thẻ");
        btnLock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLockActionPerformed(evt);
            }
        });

        btnUpdateInfo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnUpdateInfo.setText("Cập nhật t.tin");
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnClear.setText("Xoá thẻ");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(swipeBtnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSwipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdatePin, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(btnUnlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLock, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(swipeBtnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSwipe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnUpdatePin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnUnlock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnUpdateInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel6.setBackground(new java.awt.Color(54, 33, 89));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Trạng thái khi quẹt thẻ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jImagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(swipeBtnChangeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_xnmapin1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_mapin1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_ngaysinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_hoten1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(text_biensoxe1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(radio_nam1)
                                .addGap(29, 29, 29)
                                .addComponent(radio_nu1)
                                .addGap(36, 36, 36)
                                .addComponent(radio_khac1))
                            .addComponent(text_thongtinnha1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(164, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_hoten1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_ngaysinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_biensoxe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radio_nam1)
                            .addComponent(radio_nu1)
                            .addComponent(radio_khac1)
                            .addComponent(jLabel17))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_thongtinnha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_mapin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(text_xnmapin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jImagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(swipeBtnChangeImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Quẹt thẻ", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 589, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void showResult() {
        Card c = list.get(list.size() - 1);
        model.addRow(new Object[]{
            i++, c.getHoTen(), c.getNgaySinh(), c.getBienSoXe(), c.getGioiTinh(), c.getThongTinNha()
        });
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
//        NewJFrame.patient = this.patient;
    }//GEN-LAST:event_formWindowClosed

    private void buttton_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttton_removeActionPerformed
        int removeIndex = jTable1.getSelectedRow();
        if (removeIndex == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn một dòng rồi ấn nút xóa!");
        } else if (list.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Không có thông tin để xóa!");
        } else {
            Card cl = list.get(removeIndex);
            dao.delCard(cl.getId());
            model.removeRow(jTable1.getSelectedRow());
            renderListToTable();
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
        }
    }//GEN-LAST:event_buttton_removeActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        if (!smartCardService.isConnected) {
            btnConnect.setText("Đang kết nối ...");
            swipeBtnConnect.setText("Đang kết nối ...");
            try {
                smartCardService.connectCard();
            } catch (CardException ex) {
                Logger.getLogger(Form_KhoiTao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Kết nối thẻ bị lỗi!");
            }
            btnConnect.setText(smartCardService.isConnected ? "Ngắt kết nối" : "Kết nối");
            swipeBtnConnect.setText(smartCardService.isConnected ? "Ngắt kết nối" : "Kết nối");
        } else {
            smartCardService.disconnectCard();
            btnConnect.setText("Kết nối");
            swipeBtnConnect.setText("Kết nối");
        }
        checkInit();
    }//GEN-LAST:event_btnConnectActionPerformed

    private void renderListToTable() {
        model.setRowCount(0);
        list = dao.getListCard();
        i = 1;
        for (Card c : list) {
            System.out.println(c.getId());
            model.addRow(new Object[]{
                i++, c.getHoTen(), c.getNgaySinh(), c.getBienSoXe(), c.getGioiTinh(), c.getThongTinNha()
            });
        }
    }

    private void radio_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_namActionPerformed

    private void radio_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_nuActionPerformed

    private void text_mapinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_mapinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_mapinActionPerformed

    private void text_xnmapinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_xnmapinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_xnmapinActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int indexTab = jTabbedPane1.getSelectedIndex();
        if (indexTab == 2) {
            if (!statusQuetThe) {
//                JOptionPane.showMessageDialog(rootPane, "Quẹt thẻ không thành công");
                text_hoten1.setText("");
                text_ngaysinh1.setText("");
                text_biensoxe1.setText("");
                radio_nam1.setSelected(false);
                radio_nu1.setSelected(false);
                radio_khac1.setSelected(false);
                text_thongtinnha1.setText("");
                text_mapin1.setText("");
                text_mapin1.setText("");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Quẹt thẻ thành công");
                text_hoten1.setText("ok");
                text_ngaysinh1.setText("ok");
                text_biensoxe1.setText("ok");
                radio_nam1.setSelected(true);
                text_thongtinnha1.setText("ok");
                text_mapin1.setText("ok");
                text_mapin1.setText("ok");
            }
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void buttton_removeLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttton_removeLogActionPerformed
        int removeIndex = jTable2.getSelectedRow();
        if (removeIndex == -1) {
            JOptionPane.showMessageDialog(rootPane, "Hãy chọn một dòng rồi ấn nút xóa!");
        } else if (listLog.size() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Không có thông tin để xóa!");
        } else {
            Log cl = listLog.get(removeIndex);
            dao.delLog(cl.getId());
            modelTabLog.removeRow(jTable2.getSelectedRow());
            renderListToTableLog(null);
            JOptionPane.showMessageDialog(rootPane, "Xóa thành công");
        }
    }//GEN-LAST:event_buttton_removeLogActionPerformed

    private void none(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_none

    }//GEN-LAST:event_none

    private void text_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_searchKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            renderListToTableLog(text_search.getText());
        }
    }//GEN-LAST:event_text_searchKeyPressed

    private void radio_nam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_nam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_nam1ActionPerformed

    private void radio_nu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_nu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_nu1ActionPerformed

    private void text_mapin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_mapin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_mapin1ActionPerformed

    private void text_hoten1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_hoten1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_hoten1ActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) throws CardException, NoSuchAlgorithmException, InvalidKeySpecException {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        String pin = Arrays.toString(text_mapin.getPassword());
        String xnpin = Arrays.toString(text_xnmapin.getPassword());
        if (!(pin.equals(xnpin))) {
            JOptionPane.showMessageDialog(null, "Xác nhận mã pin sai");
        } else {
            if (pin.length() != 18) {
                JOptionPane.showMessageDialog(null, "Mã PIN phải có độ dài 6 kí tự");
            } else {
                String hoten = text_hoten.getText();
                System.out.println("hello");
                System.out.println(text_ngaysinh.getDate());
                String biensoxe = text_biensoxe.getText();

                String gioitinh = "";

                if (radio_nam.isSelected()) {
                    gioitinh += "Nam";
                } else if (radio_nu.isSelected()) {
                    gioitinh += "Nu";
                } else if (radio_khac.isSelected()) {
                    gioitinh += "Khac";
                }

                String thongtinnha = text_thongtinnha.getText();

                if (id != null) {
                    card.setId(id);
                } else {
                    card.setId(null);
                }
                card.setHoTen(hoten);
                card.setBienSoXe(biensoxe);
                card.setNgaySinh(text_ngaysinh.getDate());

                card.setGioiTinh(gioitinh);
                card.setThongTinNha(thongtinnha);
                card.setPublicKey("23897uyrdf8c0sweqah8f");
                card.setMapin(new String(text_mapin.getPassword()));

                JOptionPane.showMessageDialog(null, "Khởi tạo nội dung thẻ thành công");
                dao.addCard(card);

                renderListToTable();
                ArrayList<Card> _list = dao.getListCard();
                id = _list.get(_list.size()-1).getId();
                System.out.println("id: "+ id);
                card.setId(id);
                dao.updatePublicKey(card, smartCardService.initCard(card, card.getMapin()));
                smartCardService.changeImage(imgCreate);
                System.out.println("image create: "+ Arrays.toString(imgCreate));
                id = null;
                text_hoten.setText("");
                text_ngaysinh.setDate(null);
                text_biensoxe.setText("");
                buttonGroup1.clearSelection();
                text_thongtinnha.setText("");
                text_mapin.setText("");
                text_xnmapin.setText("");
                checkInit();
            }

        }
    }//GEN-LAST:event_btnOkActionPerformed

    private void swipeBtnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swipeBtnConnectActionPerformed
        // TODO add your handling code here:
        if (!smartCardService.isConnected) {
            btnConnect.setText("Đang kết nối ...");
            swipeBtnConnect.setText("Đang kết nối ...");
            try {
                smartCardService.connectCard();
            } catch (CardException ex) {
                Logger.getLogger(Form_KhoiTao.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Kết nối thẻ bị lỗi!");
            }
            btnConnect.setText(smartCardService.isConnected ? "Ngắt kết nối" : "Kết nối");
            swipeBtnConnect.setText(smartCardService.isConnected ? "Ngắt kết nối" : "Kết nối");
        } else {
            smartCardService.disconnectCard();
            btnConnect.setText("Kết nối");
            swipeBtnConnect.setText("Kết nối");
        }
        checkInit();
    }//GEN-LAST:event_swipeBtnConnectActionPerformed

    private void btnSwipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwipeActionPerformed
        // TODO add your handling code here:
        try {
            boolean _temp = smartCardService.verify();
            switch (smartCardService.getStatus()){
                case SmartCardService.STATUS_LOCKED : {
                    JOptionPane.showMessageDialog(null, "Thẻ bị khoá");
                    break;
                }
                case SmartCardService.STATUS_TRUE : {
                    if(_temp){
                        JOptionPane.showMessageDialog(null, "Quẹt thẻ thành công");
                        Card _card = smartCardService.getInfoCard();
                        text_hoten1.setText(_card.getHoTen());
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        text_ngaysinh1.setText(dateFormat.format(_card.getNgaySinh()));
                        text_biensoxe1.setText(_card.getBienSoXe());
                        text_thongtinnha1.setText(_card.getThongTinNha());
                        switch (_card.getGioiTinh()){
                            case "Nam": {
                                radio_nam1.setSelected(true);
                                radio_nu1.setSelected(false);
                                radio_khac1.setSelected(false);
                                break;
                            }
                            case "Nu": {
                                radio_nam1.setSelected(false);
                                radio_nu1.setSelected(true);
                                radio_khac1.setSelected(false);
                                break;
                            }
                            case "Khac": {
                                radio_nam1.setSelected(false);
                                radio_nu1.setSelected(false);
                                radio_khac1.setSelected(true);
                                break;
                            }
                        }
                        dao.addLog(_card);
                        renderListToTableLog("");
                        byte[] img = smartCardService.getImage();
                        System.out.println("data: "+ Arrays.toString(img));
                        ByteArrayInputStream bais= new ByteArrayInputStream(img);
                        BufferedImage b;
                        b = ImageIO.read(bais);
                        ImageIcon icon= new ImageIcon(b.getScaledInstance(jImagePreview.getWidth(),jImagePreview.getHeight(), Image.SCALE_SMOOTH));
                        icon.getImage();
                        jImagePreview.setIcon(icon);
                    } else {
                        JOptionPane.showMessageDialog(null, "Xác thực không thành công!");
                    }
                    break;
                }
            }
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 BadPaddingException | CardException | InvalidKeyException | SignatureException | IOException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btnSwipeActionPerformed

    private void btnUpdatePinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePinActionPerformed
        // TODO add your handling code here:
        String pin = String.valueOf(text_mapin1.getPassword());
        String xnpin = String.valueOf(text_xnmapin1.getPassword());
        if (!(pin.equals(xnpin))) {
            JOptionPane.showMessageDialog(null, "Xác nhận mã pin sai");
        } else {
            if (pin.length() != 6) {
                JOptionPane.showMessageDialog(null, "Mã PIN phải có độ dài 6 kí tự");
            } else {
                smartCardService.updatePin(pin);
                JOptionPane.showMessageDialog(null, "Đổi mã pin thành công");
            }
        }
        
    }//GEN-LAST:event_btnUpdatePinActionPerformed

    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInfoActionPerformed
        // TODO add your handling code here:
        Card _card = new Card();
        _card.setHoTen(text_hoten1.getText());
        _card.setThongTinNha(text_thongtinnha1.getText());
        String gioitinh = "";

        if (radio_nam.isSelected()) {
            gioitinh += "Nam";
        } else if (radio_nu.isSelected()) {
            gioitinh += "Nu";
        } else if (radio_khac.isSelected()) {
            gioitinh += "Khac";
        }
        _card.setGioiTinh(gioitinh);
        _card.setBienSoXe(text_biensoxe1.getText());
        try{
            _card.setNgaySinh(new SimpleDateFormat("dd/MM/yyyy").parse(text_ngaysinh1.getText()));
        } catch (Exception ignored){}
        smartCardService.updateInfo(_card);
        JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công!");
    }//GEN-LAST:event_btnUpdateInfoActionPerformed

    private void btnLockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLockActionPerformed
        // TODO add your handling code here:
        smartCardService.lockCard();
        JOptionPane.showMessageDialog(null, "Khoá thẻ thành công");
    }//GEN-LAST:event_btnLockActionPerformed

    private void btnUnlockActionPerformed(java.awt.event.ActionEvent evt) throws NoSuchPaddingException, BadPaddingException, CardException, InvalidKeyException {//GEN-FIRST:event_btnUnlockActionPerformed
        // TODO add your handling code here:
        smartCardService.unlockCard();
        JOptionPane.showMessageDialog(null, "Mở khoá thẻ thành công");
    }//GEN-LAST:event_btnUnlockActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPickImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPickImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            BufferedImage bimage;
            try{
                bimage = ImageIO.read(file);
                ByteArrayOutputStream _imageBytes = new ByteArrayOutputStream();
                ImageIO.write(bimage, "jpg", _imageBytes);
                byte[] img = _imageBytes.toByteArray();
                if(img.length >= 32767)
                {
                    JOptionPane.showMessageDialog(this, "Ảnh bạn chọn lớn hơn kích thước tối đa (32Kb), có thể xảy ra lỗi!\nHãy chọn ảnh khác!", "Warning!!!", JOptionPane.WARNING_MESSAGE);
                }else{
                    imgCreate = img;
                    ByteArrayInputStream bais= new ByteArrayInputStream(img);
                    BufferedImage b;
                    b = ImageIO.read(bais);
                    ImageIcon icon= new ImageIcon(b.getScaledInstance(jImage.getWidth(),jImage.getHeight(), Image.SCALE_SMOOTH));
                    icon.getImage();
                    jImage.setIcon(icon);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnPickImageActionPerformed

    private void swipeBtnChangeImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swipeBtnChangeImageActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(this);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            BufferedImage bimage;
            try{
                bimage = ImageIO.read(file);
                ByteArrayOutputStream _imageBytes = new ByteArrayOutputStream();
                ImageIO.write(bimage, "jpg", _imageBytes);
                byte[] img = _imageBytes.toByteArray();
                if(img.length >= 32767)
                {
                    JOptionPane.showMessageDialog(this, "Ảnh bạn chọn lớn hơn kích thước tối đa (32Kb), có thể xảy ra lỗi!\nHãy chọn ảnh khác!", "Warning!!!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ByteArrayInputStream bais= new ByteArrayInputStream(img);
                    BufferedImage b;
                    b = ImageIO.read(bais);
                    ImageIcon icon= new ImageIcon(b.getScaledInstance(jImagePreview.getWidth(),jImagePreview.getHeight(), Image.SCALE_SMOOTH));
                    icon.getImage();
                    jImagePreview.setIcon(icon);
                    if(smartCardService.isConnected){
                        smartCardService.changeImage(img);
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_swipeBtnChangeImageActionPerformed

    private void radio_khacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_khacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_khacActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        String pin = String.valueOf(text_mapin1.getPassword());
        String xnpin = String.valueOf(text_xnmapin1.getPassword());
        if (pin.length() != 6) {
            JOptionPane.showMessageDialog(null, "Mã PIN phải có độ dài 6 kí tự");
        } else {
            try {
                boolean _temp = smartCardService.login(pin);
                switch (smartCardService.getStatus()){
                    case SmartCardService.STATUS_LOCKED : {
                        JOptionPane.showMessageDialog(null, "Thẻ bị khoá");
                        break;
                    }
                    case SmartCardService.STATUS_TRUE : {
                        if(_temp){
                            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                            Card _card = smartCardService.getInfoCard();
                            text_hoten1.setText(_card.getHoTen());
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            text_ngaysinh1.setText(dateFormat.format(_card.getNgaySinh()));
                            text_biensoxe1.setText(_card.getBienSoXe());
                            text_thongtinnha1.setText(_card.getThongTinNha());
                            switch (_card.getGioiTinh()){
                                case "Nam": {
                                    radio_nam1.setSelected(true);
                                    radio_nu1.setSelected(false);
                                    radio_khac1.setSelected(false);
                                    break;
                                }
                                case "Nu": {
                                    radio_nam1.setSelected(false);
                                    radio_nu1.setSelected(true);
                                    radio_khac1.setSelected(false);
                                    break;
                                }
                                case "Khac": {
                                    radio_nam1.setSelected(false);
                                    radio_nu1.setSelected(false);
                                    radio_khac1.setSelected(true);
                                    break;
                                }
                            }
                            dao.addLog(_card);
                            renderListToTableLog("");
                            byte[] img = smartCardService.getImage();
                            System.out.println("data: "+ Arrays.toString(img));
                            ByteArrayInputStream bais= new ByteArrayInputStream(img);
                            BufferedImage b;
                            b = ImageIO.read(bais);
                            ImageIcon icon= new ImageIcon(b.getScaledInstance(jImagePreview.getWidth(),jImagePreview.getHeight(), Image.SCALE_SMOOTH));
                            icon.getImage();
                            jImagePreview.setIcon(icon);
                        } else {
                            JOptionPane.showMessageDialog(null, "Đăng nhập không thành công!");
                        }
                        break;
                    }
                    case SmartCardService.STATUS_FALSE : {
                        JOptionPane.showMessageDialog(null, "Mã pin không đúng");
                        break;
                    }
                }
            } catch (CardException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void text_xnmapin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_xnmapin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_xnmapin1ActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        int id = smartCardService.id;
        smartCardService.clearCard();
        smartCardService.getId();
        if(smartCardService.id == 0){
            JOptionPane.showMessageDialog(null, "Xóa thành công");
            dao.delCard(id);
            renderListToTable();
            checkInit();
        } else {
            JOptionPane.showMessageDialog(null, "Xóa không thành công");
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void renderListToTableLog(String textSearch) {
        modelTabLog.setRowCount(0);
        listLog = dao.getListLog(textSearch);
        j = 1;
        for (Log c : listLog) {
            modelTabLog.addRow(new Object[]{
                j++, c.getHoTen(), c.getNgaySinh(), c.getBienSoXe(), c.getGioiTinh(), c.getThongTinNha(), c.getNgayQuetThe(), c.getType()
            });
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Form_KhoiTao().setVisible(true);
//            }
//        });
//    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_KhoiTao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Form_KhoiTao().setVisible(true);
                } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLock;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPickImage;
    private javax.swing.JButton btnSwipe;
    private javax.swing.JButton btnUnlock;
    private javax.swing.JButton btnUpdateInfo;
    private javax.swing.JButton btnUpdatePin;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttton_remove;
    private javax.swing.JButton buttton_removeLog;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jImage;
    private javax.swing.JLabel jImagePreview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JRadioButton radio_khac;
    private javax.swing.JRadioButton radio_khac1;
    private javax.swing.JRadioButton radio_nam;
    private javax.swing.JRadioButton radio_nam1;
    private javax.swing.JRadioButton radio_nu;
    private javax.swing.JRadioButton radio_nu1;
    private javax.swing.JButton swipeBtnChangeImage;
    private javax.swing.JButton swipeBtnConnect;
    private javax.swing.JTextField text_biensoxe;
    private javax.swing.JTextField text_biensoxe1;
    private javax.swing.JTextField text_hoten;
    private javax.swing.JTextField text_hoten1;
    private javax.swing.JPasswordField text_mapin;
    private javax.swing.JPasswordField text_mapin1;
    private org.jdesktop.swingx.JXDatePicker text_ngaysinh;
    private javax.swing.JTextField text_ngaysinh1;
    private javax.swing.JTextField text_search;
    private javax.swing.JTextField text_thongtinnha;
    private javax.swing.JTextField text_thongtinnha1;
    private javax.swing.JPasswordField text_xnmapin;
    private javax.swing.JPasswordField text_xnmapin1;
    // End of variables declaration//GEN-END:variables
}
