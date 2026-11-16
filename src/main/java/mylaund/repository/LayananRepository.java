/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository;

import java.util.List;
import mylaund.model.LayananLaundry;

public interface LayananRepository {
    void simpan(LayananLaundry layanan);
    LayananLaundry cariById(String idLayanan);
    List<LayananLaundry> ambilSemua();
}