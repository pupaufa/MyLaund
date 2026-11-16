/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package mylaund;

import java.awt.GraphicsEnvironment;
import mylaund.model.ITransaksi;
import mylaund.model.Kasir;
import mylaund.model.LayananExpress;
import mylaund.model.LayananKiloan;
import mylaund.model.LayananSatuan;
import mylaund.model.Owner;
import mylaund.model.Pekerja;
import mylaund.model.Pelanggan;
import mylaund.model.Pembayaran;
import mylaund.model.Pesanan;
import mylaund.model.StatusPesanan;
import mylaund.repository.LayananRepository;
import mylaund.repository.PenggunaRepository;
import mylaund.repository.PesananRepository;
import mylaund.repository.TransaksiRepository;
import mylaund.repository.sqlite.DatabaseConnection;
import mylaund.repository.sqlite.SqliteLayananRepository;
import mylaund.repository.sqlite.SqlitePenggunaRepository;
import mylaund.repository.sqlite.SqlitePesananRepository;
import mylaund.repository.sqlite.SqliteTransaksiRepository;
import mylaund.service.AuthService;
import mylaund.service.NotifikasiService;
import mylaund.service.PembayaranService;
import mylaund.service.PesananService;
import mylaund.ui.LoginFrame;
import mylaund.ui.PesananController;

public class App {
    public static void main(String[] args) {
        // 1. Composition Root: membuat DatabaseConnection & implementasi Repository SQLite
        DatabaseConnection db = new DatabaseConnection("mylaund.db");
        db.inisialisasiSkema();

        PenggunaRepository penggunaRepo = new SqlitePenggunaRepository(db);
        LayananRepository layananRepo = new SqliteLayananRepository(db);
        PesananRepository pesananRepo = new SqlitePesananRepository(db);
        TransaksiRepository transaksiRepo = new SqliteTransaksiRepository(db);

        // 2. Menyuntikkan interface Repository ke lapisan Service (Constructor Injection)
        AuthService authService = new AuthService(penggunaRepo);
        NotifikasiService notifikasiService = new NotifikasiService();
        PesananService pesananService = new PesananService(pesananRepo, layananRepo, notifikasiService);
        PembayaranService pembayaranService = new PembayaranService(transaksiRepo);

        // 3. Menghubungkan Service ke lapisan Presentation (Controller & View)
        PesananController controller = new PesananController(
                authService, pesananService, pembayaranService, notifikasiService);

        // 4. Data simulasi awal untuk pengujian alur use case & kompilasi
        Pelanggan wati = new Pelanggan("U01", "Wati", "wati", authService.hashPassword("1234"), "08123456789", "Jl. Merpati No. 10");
        Kasir kasir = new Kasir("U02", "Rina", "kasir1", authService.hashPassword("1234"), "Pagi");
        Pekerja pekerja = new Pekerja("U03", "Budi", "pekerja1", authService.hashPassword("1234"), "Cuci & Setrika");
        Owner owner = new Owner("U04", "Pak Hendra", "owner", authService.hashPassword("1234"));

        penggunaRepo.simpan(wati);
        penggunaRepo.simpan(kasir);
        penggunaRepo.simpan(pekerja);
        penggunaRepo.simpan(owner);

        LayananKiloan kiloan = new LayananKiloan("L01", "Cuci Setrika Kiloan", "Cuci kering dan setrika rapi", 7000, 3, 2.0);
        LayananSatuan satuan = new LayananSatuan("L02", "Cuci Kemeja Kantor", "Perawatan khusus kemeja kerja", 12000, 2, "Kemeja");
        LayananExpress express = new LayananExpress("L03", "Cuci Express 1 Hari", "Selesai dalam 1 hari", 10000, 1, 5000);

        layananRepo.simpan(kiloan);
        layananRepo.simpan(satuan);
        layananRepo.simpan(express);

        // Simulasi alur: Buat Pesanan -> Hitung Total -> Pembayaran -> Perbarui Status -> Notifikasi -> Riwayat
        Pesanan pesananWati = controller.handleBuatPesanan("ORD-001", wati);
        controller.handleTambahItem(pesananWati, kiloan, 3.0);
        controller.handleTambahItem(pesananWati, satuan, 2.0);

        System.out.println("Total Biaya Pesanan: Rp" + pesananWati.getTotalBiaya());
        System.out.println("Estimasi Selesai   : " + pesananWati.getEstimasiSelesai());

        Pembayaran bayar = controller.handlePembayaran("PAY-001", pesananWati, 50000, "TUNAI");
        System.out.println("Status Pembayaran  : " + (bayar.isLunas() ? "LUNAS" : "BELUM LUNAS"));

        controller.handlePerbaruiStatus(pesananWati, StatusPesanan.DICUCI);
        controller.handlePerbaruiStatus(pesananWati, StatusPesanan.SIAP_DIAMBIL);

        System.out.println("=== Riwayat Transaksi ===");
        for (ITransaksi trx : controller.getRiwayatTransaksi()) {
            System.out.println("- " + trx.getDeskripsiTransaksi() + " | Nominal: Rp" + trx.getNominal());
        }

        // 5. Menampilkan LoginFrame jika berjalan di lingkungan desktop
        if (!GraphicsEnvironment.isHeadless()) {
            LoginFrame loginFrame = new LoginFrame(controller);
            loginFrame.setVisible(true);
        }
    }
}