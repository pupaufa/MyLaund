/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import mylaund.model.Pelanggan;

public class PelangganView extends JFrame {
    private Pelanggan pelanggan;
    private PesananController controller;

    public PelangganView(Pelanggan pelanggan, PesananController controller) {
        this.pelanggan = pelanggan;
        this.controller = controller;
        setTitle("MyLaund - Dashboard Pelanggan");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Selamat datang, " + this.pelanggan.getNama()
                + " | Notifikasi: " + this.controller.getNotifikasiPelanggan(this.pelanggan.getIdPengguna()).size(),
                SwingConstants.CENTER));
    }
}