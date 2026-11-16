/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository.sqlite;

import java.util.ArrayList;
import java.util.List;
import mylaund.model.Pesanan;
import mylaund.model.StatusPesanan;
import mylaund.repository.PesananRepository;

public class SqlitePesananRepository implements PesananRepository {
    private DatabaseConnection dbConnection;
    private List<Pesanan> dataPesanan;

    public SqlitePesananRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.dataPesanan = new ArrayList<>();
    }

    @Override
    public void simpan(Pesanan pesanan) {
        if (dbConnection != null) {
            dataPesanan.add(pesanan);
        }
    }

    @Override
    public Pesanan cariById(String idPesanan) {
        for (Pesanan p : dataPesanan) {
            if (p.getIdPesanan().equals(idPesanan)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pesanan> cariByPelanggan(String idPelanggan) {
        List<Pesanan> hasil = new ArrayList<>();
        for (Pesanan p : dataPesanan) {
            if (p.getPelanggan().getIdPengguna().equals(idPelanggan)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    @Override
    public List<Pesanan> ambilSemua() {
        return new ArrayList<>(dataPesanan);
    }

    @Override
    public void perbaruiStatus(String idPesanan, StatusPesanan statusBaru) {
        Pesanan target = cariById(idPesanan);
        if (target != null) {
            target.ubahStatus(statusBaru);
        }
    }
}