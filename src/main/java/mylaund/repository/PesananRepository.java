/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository;

import java.util.List;
import mylaund.model.Pesanan;
import mylaund.model.StatusPesanan;

public interface PesananRepository {
    void simpan(Pesanan pesanan);
    Pesanan cariById(String idPesanan);
    List<Pesanan> cariByPelanggan(String idPelanggan);
    List<Pesanan> ambilSemua();
    void perbaruiStatus(String idPesanan, StatusPesanan statusBaru);
}