/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.model;

import java.time.LocalDateTime;

public class Pembayaran implements ITransaksi {
    private String idPembayaran;
    private Pesanan pesanan;
    private LocalDateTime tanggalBayar;
    private double jumlahBayar;
    private String metode;
    private boolean lunas;

    public Pembayaran(String idPembayaran, Pesanan pesanan, LocalDateTime tanggalBayar, double jumlahBayar, String metode) {
        this.idPembayaran = idPembayaran;
        this.pesanan = pesanan;
        this.tanggalBayar = tanggalBayar;
        this.jumlahBayar = jumlahBayar;
        this.metode = metode;
        this.lunas = false;
    }

    public boolean catat() {
        if (jumlahBayar < pesanan.getTotalBiaya()) {
            return false;
        }
        this.lunas = true;
        return true;
    }

    @Override
    public LocalDateTime getTanggal() {
        return tanggalBayar;
    }

    @Override
    public double getNominal() {
        return jumlahBayar;
    }

    @Override
    public String getDeskripsiTransaksi() {
        return "Pembayaran " + idPembayaran + " untuk Pesanan " + pesanan.getIdPesanan() + " [" + metode + "]";
    }

    public String getIdPembayaran() {
        return idPembayaran;
    }

    public Pesanan getPesanan() {
        return pesanan;
    }

    public LocalDateTime getTanggalBayar() {
        return tanggalBayar;
    }

    public double getJumlahBayar() {
        return jumlahBayar;
    }

    public String getMetode() {
        return metode;
    }

    public boolean isLunas() {
        return lunas;
    }
}
