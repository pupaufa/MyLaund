/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository.sqlite;

import java.util.ArrayList;
import java.util.List;
import mylaund.model.ITransaksi;
import mylaund.model.Pembayaran;
import mylaund.repository.TransaksiRepository;

public class SqliteTransaksiRepository implements TransaksiRepository {
    private DatabaseConnection dbConnection;
    private List<ITransaksi> riwayatTransaksi;

    public SqliteTransaksiRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.riwayatTransaksi = new ArrayList<>();
    }

    @Override
    public void simpanPembayaran(Pembayaran pembayaran) {
        if (dbConnection != null) {
            riwayatTransaksi.add(pembayaran);
        }
    }

    @Override
    public List<ITransaksi> ambilSemuaRiwayat() {
        return new ArrayList<>(riwayatTransaksi);
    }
}