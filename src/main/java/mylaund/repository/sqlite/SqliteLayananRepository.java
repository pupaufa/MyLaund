/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository.sqlite;

import java.util.ArrayList;
import java.util.List;
import mylaund.model.LayananLaundry;
import mylaund.repository.LayananRepository;

public class SqliteLayananRepository implements LayananRepository {
    private DatabaseConnection dbConnection;
    private List<LayananLaundry> dataLayanan;

    public SqliteLayananRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.dataLayanan = new ArrayList<>();
    }

    @Override
    public void simpan(LayananLaundry layanan) {
        if (dbConnection != null) {
            dataLayanan.add(layanan);
        }
    }

    @Override
    public LayananLaundry cariById(String idLayanan) {
        for (LayananLaundry l : dataLayanan) {
            if (l.getIdLayanan().equals(idLayanan)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public List<LayananLaundry> ambilSemua() {
        return new ArrayList<>(dataLayanan);
    }
}