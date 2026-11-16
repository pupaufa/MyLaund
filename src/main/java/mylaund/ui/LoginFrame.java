/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import mylaund.model.Kasir;
import mylaund.model.Owner;
import mylaund.model.Pekerja;
import mylaund.model.Pelanggan;
import mylaund.model.Pengguna;

public class LoginFrame extends JFrame {
    private PesananController controller;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame(PesananController controller) {
        this.controller = controller;
        setTitle("MyLaund - Login");
        setSize(360, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 8, 8));

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> prosesLogin());

        add(new JLabel("Username:"));
        add(txtUsername);
        add(new JLabel("Password:"));
        add(txtPassword);
        add(new JLabel(""));
        add(btnLogin);
    }

    private void prosesLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        Pengguna user = controller.handleLogin(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Username atau password salah.");
            return;
        }

        if (user instanceof Pelanggan) {
            new PelangganView((Pelanggan) user, controller).setVisible(true);
        } else if (user instanceof Kasir) {
            new KasirView((Kasir) user, controller).setVisible(true);
        } else if (user instanceof Pekerja) {
            new PekerjaView((Pekerja) user, controller).setVisible(true);
        } else if (user instanceof Owner) {
            new OwnerView((Owner) user, controller).setVisible(true);
        }
    }
}