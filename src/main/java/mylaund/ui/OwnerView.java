/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import mylaund.model.Owner;

public class OwnerView extends JFrame {
    private Owner owner;
    private PesananController controller;

    public OwnerView(Owner owner, PesananController controller) {
        this.owner = owner;
        this.controller = controller;
        setTitle("MyLaund - Dashboard Owner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Owner: " + this.owner.getNama()
                + " | Total Riwayat Transaksi: " + this.controller.getRiwayatTransaksi().size(),
                SwingConstants.CENTER));
    }
}