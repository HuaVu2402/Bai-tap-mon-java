import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PhanSo {
    private int t;
    private int m;

    public PhanSo() {
        this.t = 0;
        this.m = 1;
    }

    public PhanSo(int t, int m) {
        this.t = t;
        setM(m);
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        if (m == 0) {
            this.m = 1;
        } else {
            this.m = m;
        }
    }

    private int ucln(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a == 0 ? 1 : a;
    }

    public void rutGon() {
        int u = ucln(this.t, this.m);
        this.t /= u;
        this.m /= u;
        if (this.m < 0) {
            this.t = -this.t;
            this.m = -this.m;
        }
    }

    public PhanSo cong(PhanSo p) {
        int tu = this.t * p.m + p.t * this.m;
        int mau = this.m * p.m;
        PhanSo ketQua = new PhanSo(tu, mau);
        ketQua.rutGon();
        return ketQua;
    }

    public PhanSo tru(PhanSo p) {
        int tu = this.t * p.m - p.t * this.m;
        int mau = this.m * p.m;
        PhanSo ketQua = new PhanSo(tu, mau);
        ketQua.rutGon();
        return ketQua;
    }

    public PhanSo nhan(PhanSo p) {
        int tu = this.t * p.t;
        int mau = this.m * p.m;
        PhanSo ketQua = new PhanSo(tu, mau);
        ketQua.rutGon();
        return ketQua;
    }

    public PhanSo chia(PhanSo p) {
        int tu = this.t * p.m;
        int mau = this.m * p.t;
        PhanSo ketQua = new PhanSo(tu, mau);
        ketQua.rutGon();
        return ketQua;
    }

    public int soSanh(PhanSo p) {
        double v1 = (double) this.t / this.m;
        double v2 = (double) p.t / p.m;
        if (v1 < v2) return -1;
        if (v1 > v2) return 1;
        return 0;
    }

    public boolean laSoNguyen() {
        this.rutGon();
        return this.m == 1;
    }

    @Override
    public String toString() {
        this.rutGon();
        if (this.m == 1) return String.valueOf(this.t);
        if (this.t == 0) return "0";
        return this.t + "/" + this.m;
    }
}

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<PhanSo> danhSach = new ArrayList<>();

        System.out.print("Nhap so luong phan so trong tap hop: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phan so thu " + (i + 1) + ":");
            System.out.print("  Tu so T: ");
            int t = sc.nextInt();
            System.out.print("  Mau so M: ");
            int m = sc.nextInt();
            danhSach.add(new PhanSo(t, m));
        }

        System.out.println("\nTap hop cac phan so da nhap:");
        for (int i = 0; i < danhSach.size(); i++) {
            System.out.println("P[" + (i + 1) + "] = " + danhSach.get(i));
        }

        if (!danhSach.isEmpty()) {
            PhanSo maxPs = danhSach.get(0);
            for (PhanSo p : danhSach) {
                if (p.soSanh(maxPs) > 0) {
                    maxPs = p;
                }
            }
            System.out.println("\nPhan so lon nhat la: " + maxPs);
        }

        PhanSo tongTatCa = new PhanSo(0, 1);
        for (PhanSo p : danhSach) {
            tongTatCa = tongTatCa.cong(p);
        }
        System.out.println("Tong cua cac phan so la: " + tongTatCa);

        System.out.println("\nCac phan so KHONG PHAI la so nguyen:");
        boolean coKhongNguyen = false;
        for (PhanSo p : danhSach) {
            if (!p.laSoNguyen()) {
                System.out.println("- " + p);
                coKhongNguyen = true;
            }
        }
        if (!coKhongNguyen) {
            System.out.println("Khong co phan so nao.");
        }
        sc.close();
    }
}