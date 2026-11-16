/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mylaund.model.Notifikasi;
import mylaund.model.Pesanan;

public class NotifikasiService {
    private List<Notifikasi> daftarNotifikasi;

    public NotifikasiService() {
        this.daftarNotifikasi = new ArrayList<>();
    }

    public Notifikasi kirimNotifikasiStatus(Pesanan pesanan) {
        String idNotif = "NTF-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        String pesan = "Status pesanan " + pesanan.getIdPesanan() + " berubah menjadi: " + pesanan.getStatus();
        Notifikasi notifikasi = new Notifikasi(idNotif, pesanan.getPelanggan(), pesan);
        notifikasi.kirim();
        daftarNotifikasi.add(notifikasi);
        return notifikasi;
    }

    public List<Notifikasi> ambilNotifikasiPelanggan(String idPelanggan) {
        List<Notifikasi> hasil = new ArrayList<>();
        for (Notifikasi n : daftarNotifikasi) {
            if (n.getPenerima().getIdPengguna().equals(idPelanggan)) {
                hasil.add(n);
            }
        }
        return hasil;
    }
}