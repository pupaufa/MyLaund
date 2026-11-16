/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.model;

public abstract class Pengguna {
    private String idPengguna;
    private String nama;
    private String username;
    private String passwordHash;

    public Pengguna(String idPengguna, String nama, String username, String passwordHash) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public boolean login(String username, String passwordHash) {
        return this.username.equals(username) && this.passwordHash.equals(passwordHash);
    }

    public void logout() {
        System.out.println("Pengguna " + nama + " (" + getPeran() + ") telah logout.");
    }

    public abstract String getPeran();

    public String getIdPengguna() {
        return idPengguna;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
