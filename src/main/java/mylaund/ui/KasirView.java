/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import mylaund.model.Kasir;

public class KasirView extends JFrame {
    private Kasir kasir;
    private PesananController controller;

    public KasirView(Kasir kasir, PesananController controller) {
        this.kasir = kasir;
        this.controller = controller;
        setTitle("MyLaund - Dashboard Kasir");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Kasir: " + this.kasir.getNama() + " (" + this.kasir.getShift()
                + ") | Layanan tersedia: " + this.controller.getDaftarLayanan().size(),
                SwingConstants.CENTER));
    }
}