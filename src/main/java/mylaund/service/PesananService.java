/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.service;

import java.time.LocalDateTime;
import java.util.List;
import mylaund.model.LayananLaundry;
import mylaund.model.Pelanggan;
import mylaund.model.Pesanan;
import mylaund.model.StatusPesanan;
import mylaund.repository.LayananRepository;
import mylaund.repository.PesananRepository;

public class PesananService {
    private PesananRepository pesananRepo;
    private LayananRepository layananRepo;
    private NotifikasiService notifikasiService;

    public PesananService(PesananRepository pesananRepo, LayananRepository layananRepo, NotifikasiService notifikasiService) {
        this.pesananRepo = pesananRepo;
        this.layananRepo = layananRepo;
        this.notifikasiService = notifikasiService;
    }

    public Pesanan buatPesanan(String idPesanan, Pelanggan pelanggan) {
        Pesanan pesanan = new Pesanan(idPesanan, pelanggan, LocalDateTime.now());
        pesananRepo.simpan(pesanan);
        return pesanan;
    }

    public void tambahItemPesanan(Pesanan pesanan, LayananLaundry layanan, double jumlah) {
        pesanan.tambahDetail(layanan, jumlah);
    }

    public void perbaruiStatusPesanan(Pesanan pesanan, StatusPesanan statusBaru) {
        pesananRepo.perbaruiStatus(pesanan.getIdPesanan(), statusBaru);
        notifikasiService.kirimNotifikasiStatus(pesanan);
    }

    public List<LayananLaundry> lihatDaftarLayanan() {
        return layananRepo.ambilSemua();
    }

    public List<Pesanan> lihatPesananPelanggan(String idPelanggan) {
        return pesananRepo.cariByPelanggan(idPelanggan);
    }

    public List<Pesanan> lihatSemuaPesanan() {
        return pesananRepo.ambilSemua();
    }
}