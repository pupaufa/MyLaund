/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.model;

public class Pelanggan extends Pengguna {
    private String noHp;
    private String alamat;

    public Pelanggan(String idPengguna, String nama, String username, String passwordHash, String noHp, String alamat) {
        super(idPengguna, nama, username, passwordHash);
        this.noHp = noHp;
        this.alamat = alamat;
    }

    @Override
    public String getPeran() {
        return "PELANGGAN";
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }
}