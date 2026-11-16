/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mylaund.repository.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private String dbFile;
    private String url;
    private Connection connection;

    public DatabaseConnection(String dbFile) {
        this.dbFile = dbFile;
        this.url = "jdbc:sqlite:" + dbFile;
        System.setProperty("org.sqlite.tmpdir", System.getProperty("user.home"));
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("PRAGMA foreign_keys = ON;");
            }
        }
        return connection;
    }

    public String getDbFile() {
        return dbFile;
    }

    public void inisialisasiSkema() {
        try {
            Connection conn = getConnection();
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pengguna (
                        id_pengguna   TEXT PRIMARY KEY,
                        nama          TEXT NOT NULL,
                        username      TEXT NOT NULL UNIQUE,
                        password_hash TEXT NOT NULL,
                        peran         TEXT NOT NULL CHECK (peran IN ('PELANGGAN', 'KASIR', 'PEKERJA', 'OWNER')),
                        no_hp         TEXT,
                        alamat        TEXT,
                        shift         TEXT,
                        bagian        TEXT
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS layanan (
                        id_layanan     TEXT PRIMARY KEY,
                        nama_layanan   TEXT NOT NULL,
                        deskripsi      TEXT,
                        tarif          REAL NOT NULL CHECK (tarif > 0),
                        durasi_hari    INTEGER NOT NULL CHECK (durasi_hari > 0),
                        jenis_layanan  TEXT NOT NULL CHECK (jenis_layanan IN ('KILOAN', 'SATUAN', 'EXPRESS')),
                        minimal_berat  REAL,
                        kategori_item  TEXT,
                        biaya_tambahan REAL
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pesanan (
                        id_pesanan       TEXT PRIMARY KEY,
                        id_pelanggan     TEXT NOT NULL,
                        tanggal_masuk    TEXT NOT NULL,
                        estimasi_selesai TEXT NOT NULL,
                        total_biaya      REAL NOT NULL DEFAULT 0,
                        status           TEXT NOT NULL CHECK (status IN ('DITERIMA', 'DICUCI', 'DIKERINGKAN', 'DISETRIKA', 'SIAP_DIAMBIL', 'SELESAI')),
                        FOREIGN KEY (id_pelanggan) REFERENCES pengguna(id_pengguna)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS detail_pesanan (
                        id_detail   INTEGER PRIMARY KEY AUTOINCREMENT,
                        id_pesanan  TEXT NOT NULL,
                        id_layanan  TEXT NOT NULL,
                        jumlah      REAL NOT NULL CHECK (jumlah > 0),
                        subtotal    REAL NOT NULL CHECK (subtotal >= 0),
                        FOREIGN KEY (id_pesanan) REFERENCES pesanan(id_pesanan) ON DELETE CASCADE,
                        FOREIGN KEY (id_layanan) REFERENCES layanan(id_layanan)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pembayaran (
                        id_pembayaran TEXT PRIMARY KEY,
                        id_pesanan    TEXT NOT NULL,
                        tanggal_bayar TEXT NOT NULL,
                        jumlah_bayar  REAL NOT NULL CHECK (jumlah_bayar > 0),
                        metode        TEXT NOT NULL,
                        lunas         INTEGER NOT NULL DEFAULT 0,
                        FOREIGN KEY (id_pesanan) REFERENCES pesanan(id_pesanan)
                    );
                """);

                stmt.execute("""
                    CREATE TABLE IF NOT EXISTS notifikasi (
                        id_notifikasi TEXT PRIMARY KEY,
                        id_pelanggan  TEXT NOT NULL,
                        pesan         TEXT NOT NULL,
                        waktu_kirim   TEXT NOT NULL,
                        terkirim      INTEGER NOT NULL DEFAULT 0,
                        FOREIGN KEY (id_pelanggan) REFERENCES pengguna(id_pengguna)
                    );
                """);

                stmt.execute("""
                    INSERT OR IGNORE INTO pengguna (id_pengguna, nama, username, password_hash, peran, no_hp, alamat, shift, bagian) VALUES
                    ('U01', 'Wati', 'wati', 'HASH_1234', 'PELANGGAN', '08123456789', 'Jl. Merpati No. 10', NULL, NULL),
                    ('U02', 'Rina', 'kasir1', 'HASH_1234', 'KASIR', NULL, NULL, 'Pagi', NULL),
                    ('U03', 'Budi', 'pekerja1', 'HASH_1234', 'PEKERJA', NULL, NULL, NULL, 'Cuci & Setrika'),
                    ('U04', 'Pak Hendra', 'owner', 'HASH_1234', 'OWNER', NULL, NULL, NULL, NULL);
                """);

                stmt.execute("""
                    INSERT OR IGNORE INTO layanan (id_layanan, nama_layanan, deskripsi, tarif, durasi_hari, jenis_layanan, minimal_berat, kategori_item, biaya_tambahan) VALUES
                    ('L01', 'Cuci Setrika Kiloan', 'Cuci kering dan setrika rapi per kg', 7000, 3, 'KILOAN', 2.0, NULL, NULL),
                    ('L02', 'Cuci Kemeja Kantor', 'Perawatan khusus kemeja kerja satuan', 12000, 2, 'SATUAN', NULL, 'Kemeja', NULL),
                    ('L03', 'Cuci Express 1 Hari', 'Layanan kilat selesai dalam 1 hari', 10000, 1, 'EXPRESS', NULL, NULL, 5000);
                """);
            }
            System.out.println("Database SQLite siap digunakan: " + dbFile);
        } catch (SQLException e) {
            throw new RuntimeException("Gagal menginisialisasi database SQLite: " + e.getMessage(), e);
        }
    }
}