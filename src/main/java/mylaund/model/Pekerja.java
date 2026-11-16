/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.model;

public class Pekerja extends Pengguna {
    private String bagian;

    public Pekerja(String idPengguna, String nama, String username, String passwordHash, String bagian) {
        super(idPengguna, nama, username, passwordHash);
        this.bagian = bagian;
    }

    @Override
    public String getPeran() {
        return "PEKERJA";
    }

    public String getBagian() {
        return bagian;
    }
}