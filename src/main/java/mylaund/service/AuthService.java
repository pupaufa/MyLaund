/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.service;

import mylaund.model.Pengguna;
import mylaund.repository.PenggunaRepository;

public class AuthService {
    private PenggunaRepository penggunaRepo;

    public AuthService(PenggunaRepository penggunaRepo) {
        this.penggunaRepo = penggunaRepo;
    }

    public Pengguna login(String username, String password) {
        Pengguna pengguna = penggunaRepo.cariByUsername(username);
        if (pengguna != null && pengguna.login(username, hashPassword(password))) {
            return pengguna;
        }
        return null;
    }

    public String hashPassword(String password) {
        return "HASH_" + password;
    }
}