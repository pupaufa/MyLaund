/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import mylaund.model.Pekerja;

public class PekerjaView extends JFrame {
    private Pekerja pekerja;
    private PesananController controller;

    public PekerjaView(Pekerja pekerja, PesananController controller) {
        this.pekerja = pekerja;
        this.controller = controller;
        setTitle("MyLaund - Dashboard Pekerja");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Pekerja: " + this.pekerja.getNama() + " - Bagian " + this.pekerja.getBagian()
                + " | Jumlah layanan: " + this.controller.getDaftarLayanan().size(),
                SwingConstants.CENTER));
    }
}