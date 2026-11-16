/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository.sqlite;

import java.util.ArrayList;
import java.util.List;
import mylaund.model.Pengguna;
import mylaund.repository.PenggunaRepository;

public class SqlitePenggunaRepository implements PenggunaRepository {
    private DatabaseConnection dbConnection;
    private List<Pengguna> dataPengguna;

    public SqlitePenggunaRepository(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.dataPengguna = new ArrayList<>();
    }

    @Override
    public void simpan(Pengguna pengguna) {
        if (dbConnection != null) {
            dataPengguna.add(pengguna);
        }
    }

    @Override
    public Pengguna cariByUsername(String username) {
        for (Pengguna p : dataPengguna) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pengguna> ambilSemua() {
        return new ArrayList<>(dataPengguna);
    }
}