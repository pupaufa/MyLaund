/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.ui;

import java.util.List;
import mylaund.model.ITransaksi;
import mylaund.model.LayananLaundry;
import mylaund.model.Notifikasi;
import mylaund.model.Pelanggan;
import mylaund.model.Pembayaran;
import mylaund.model.Pengguna;
import mylaund.model.Pesanan;
import mylaund.model.StatusPesanan;
import mylaund.service.AuthService;
import mylaund.service.NotifikasiService;
import mylaund.service.PembayaranService;
import mylaund.service.PesananService;

public class PesananController {
    private AuthService authService;
    private PesananService pesananService;
    private PembayaranService pembayaranService;
    private NotifikasiService notifikasiService;

    public PesananController(
            AuthService authService,
            PesananService pesananService,
            PembayaranService pembayaranService,
            NotifikasiService notifikasiService) {
        this.authService = authService;
        this.pesananService = pesananService;
        this.pembayaranService = pembayaranService;
        this.notifikasiService = notifikasiService;
    }

    public Pengguna handleLogin(String username, String password) {
        return authService.login(username, password);
    }

    public List<LayananLaundry> getDaftarLayanan() {
        return pesananService.lihatDaftarLayanan();
    }

    public Pesanan handleBuatPesanan(String idPesanan, Pelanggan pelanggan) {
        return pesananService.buatPesanan(idPesanan, pelanggan);
    }

    public void handleTambahItem(Pesanan pesanan, LayananLaundry layanan, double jumlah) {
        pesananService.tambahItemPesanan(pesanan, layanan, jumlah);
    }

    public Pembayaran handlePembayaran(String idPembayaran, Pesanan pesanan, double jumlahBayar, String metode) {
        return pembayaranService.prosesPembayaran(idPembayaran, pesanan, jumlahBayar, metode);
    }

    public void handlePerbaruiStatus(Pesanan pesanan, StatusPesanan statusBaru) {
        pesananService.perbaruiStatusPesanan(pesanan, statusBaru);
    }

    public List<ITransaksi> getRiwayatTransaksi() {
        return pembayaranService.lihatRiwayatTransaksi();
    }

    public List<Notifikasi> getNotifikasiPelanggan(String idPelanggan) {
        return notifikasiService.ambilNotifikasiPelanggan(idPelanggan);
    }
}