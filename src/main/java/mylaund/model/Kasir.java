/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.model;

public class Kasir extends Pengguna {
    private String shift;

    public Kasir(String idPengguna, String nama, String username, String passwordHash, String shift) {
        super(idPengguna, nama, username, passwordHash);
        this.shift = shift;
    }

    @Override
    public String getPeran() {
        return "KASIR";
    }

    public String getShift() {
        return shift;
    }
}