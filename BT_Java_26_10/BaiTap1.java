import java.util.Scanner;

/**
 * Bai tap 1: Lop PhanSo.
 *
 * Moi phan so P = T/M, voi T (tu so) va M (mau so) la so nguyen.
 * - Thuoc tinh, ham tao mac dinh va co doi so
 * - Getter, setter, toString
 * - Tong, hieu, tich, thuong, so sanh, rut gon
 *
 * Chuong trinh voi mot tap phan so:
 * - Tim phan so lon nhat
 * - Tinh tong cac phan so
 * - Dem so phan so khong phai so nguyen
 */
public class BaiTap1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== BAI TAP 1: LOP PHAN SO =====");
        demoHamCoBan();

        System.out.println("\n===== TAP PHAN SO =====");
        System.out.println("1. Dung tap phan so mau");
        System.out.println("2. Nhap tap phan so tu ban phim");
        System.out.print("Chon (1-2): ");
        int choice = sc.nextInt();

        PhanSo[] ds;
        if (choice == 2) {
            ds = nhapTapPhanSo(sc);
        } else {
            ds = taoTapMau();
            System.out.println("Dang dung tap phan so mau.");
        }

        xuLyTapPhanSo(ds);
        sc.close();
    }

    /** Minh hoa ham tao mac dinh, getter, setter, toString va rut gon. */
    static void demoHamCoBan() {
        PhanSo macDinh = new PhanSo();
        System.out.println("Ham tao mac dinh: " + macDinh);

        macDinh.setTuSo(8);
        macDinh.setMauSo(12);
        System.out.println("Sau setter: tu = " + macDinh.getTuSo()
                + ", mau = " + macDinh.getMauSo()
                + " -> " + macDinh);
        macDinh.rutGon();
        System.out.println("Rut gon 8/12: " + macDinh);

        PhanSo a = new PhanSo(1, 2);
        PhanSo b = new PhanSo(3, 4);
        System.out.println("\nPhep toan voi " + a + " va " + b + ":");
        System.out.println("Tong   = " + a.cong(b));
        System.out.println("Hieu   = " + a.tru(b));
        System.out.println("Tich   = " + a.nhan(b));
        System.out.println("Thuong = " + a.chia(b));
        inKetQuaSoSanh(a, b);
    }

    static PhanSo[] taoTapMau() {
        return new PhanSo[] {
            new PhanSo(1, 2),
            new PhanSo(3, 4),
            new PhanSo(2, 1),
            new PhanSo(8, 12),
            new PhanSo(-6, 3),
            new PhanSo(5, -3),
            new PhanSo(0, 7)
        };
    }

    static PhanSo[] nhapTapPhanSo(Scanner sc) {
        int n;
        do {
            System.out.print("Nhap so phan so n (> 0): ");
            n = sc.nextInt();
        } while (n <= 0);

        PhanSo[] ds = new PhanSo[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Phan so " + (i + 1) + " - nhap tu so va mau so: ");
            int tu = sc.nextInt();
            int mau = sc.nextInt();
            while (mau == 0) {
                System.out.print("Mau so phai khac 0. Nhap lai mau so: ");
                mau = sc.nextInt();
            }
            ds[i] = new PhanSo(tu, mau);
        }
        return ds;
    }

    static void xuLyTapPhanSo(PhanSo[] ds) {
        System.out.println("\n--- Tap phan so ban dau ---");
        inTap(ds);

        System.out.println("\n--- Rut gon tung phan so ---");
        for (int i = 0; i < ds.length; i++) {
            String truoc = ds[i].toString();
            ds[i].rutGon();
            System.out.println("P[" + i + "]: " + truoc + " -> " + ds[i]);
        }

        PhanSo lonNhat = timPhanSoLonNhat(ds);
        System.out.println("\nPhan so lon nhat: " + lonNhat);

        PhanSo tong = tinhTong(ds);
        System.out.println("Tong cac phan so: " + tong);

        int soKhongNguyen = demPhanSoKhongNguyen(ds);
        System.out.println("So phan so khong phai so nguyen: " + soKhongNguyen);
    }

    static void inTap(PhanSo[] ds) {
        for (int i = 0; i < ds.length; i++) {
            System.out.println("P[" + i + "] = " + ds[i]);
        }
    }

    static PhanSo timPhanSoLonNhat(PhanSo[] ds) {
        PhanSo max = ds[0];
        for (int i = 1; i < ds.length; i++) {
            if (ds[i].soSanh(max) > 0) {
                max = ds[i];
            }
        }
        return max;
    }

    static PhanSo tinhTong(PhanSo[] ds) {
        PhanSo tong = new PhanSo(0, 1);
        for (PhanSo p : ds) {
            tong = tong.cong(p);
        }
        return tong;
    }

    static int demPhanSoKhongNguyen(PhanSo[] ds) {
        int dem = 0;
        for (PhanSo p : ds) {
            if (!p.laSoNguyen()) {
                dem++;
            }
        }
        return dem;
    }

    static void inKetQuaSoSanh(PhanSo a, PhanSo b) {
        int kq = a.soSanh(b);
        if (kq > 0) {
            System.out.println(a + " > " + b);
        } else if (kq < 0) {
            System.out.println(a + " < " + b);
        } else {
            System.out.println(a + " = " + b);
        }
    }
}

/** Phan so P = tuSo / mauSo. */
class PhanSo {
    private int tuSo;
    private int mauSo;

    /** Ham tao mac dinh: 0/1. */
    public PhanSo() {
        this.tuSo = 0;
        this.mauSo = 1;
    }

    /** Ham tao co doi so. Mau so bang 0 thi gan mau so = 1. */
    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        if (mauSo == 0) {
            this.mauSo = 1;
        } else {
            this.mauSo = mauSo;
        }
        chuanHoaDau();
    }

    public int getTuSo() {
        return tuSo;
    }

    public void setTuSo(int tuSo) {
        this.tuSo = tuSo;
    }

    public int getMauSo() {
        return mauSo;
    }

    public void setMauSo(int mauSo) {
        if (mauSo == 0) {
            System.out.println("Mau so khong duoc bang 0.");
            return;
        }
        this.mauSo = mauSo;
        chuanHoaDau();
    }

    @Override
    public String toString() {
        return tuSo + "/" + mauSo;
    }

    /** Tong hai phan so: a/b + c/d = (a*d + c*b) / (b*d). */
    public PhanSo cong(PhanSo p) {
        int tu = this.tuSo * p.mauSo + p.tuSo * this.mauSo;
        int mau = this.mauSo * p.mauSo;
        PhanSo kq = new PhanSo(tu, mau);
        kq.rutGon();
        return kq;
    }

    /** Hieu hai phan so: a/b - c/d = (a*d - c*b) / (b*d). */
    public PhanSo tru(PhanSo p) {
        int tu = this.tuSo * p.mauSo - p.tuSo * this.mauSo;
        int mau = this.mauSo * p.mauSo;
        PhanSo kq = new PhanSo(tu, mau);
        kq.rutGon();
        return kq;
    }

    /** Tich hai phan so: a/b * c/d = (a*c) / (b*d). */
    public PhanSo nhan(PhanSo p) {
        PhanSo kq = new PhanSo(this.tuSo * p.tuSo, this.mauSo * p.mauSo);
        kq.rutGon();
        return kq;
    }

    /** Thuong hai phan so: (a/b) / (c/d) = (a*d) / (b*c). */
    public PhanSo chia(PhanSo p) {
        if (p.tuSo == 0) {
            throw new ArithmeticException("Khong the chia cho phan so 0.");
        }
        PhanSo kq = new PhanSo(this.tuSo * p.mauSo, this.mauSo * p.tuSo);
        kq.rutGon();
        return kq;
    }

    /**
     * So sanh hai phan so.
     * @return 1 neu this lon hon, -1 neu this nho hon, 0 neu bang nhau
     */
    public int soSanh(PhanSo p) {
        long trai = (long) this.tuSo * p.mauSo;
        long phai = (long) p.tuSo * this.mauSo;
        if (trai > phai) {
            return 1;
        }
        if (trai < phai) {
            return -1;
        }
        return 0;
    }

    /** Rut gon phan so bang uoc chung lon nhat. */
    public void rutGon() {
        chuanHoaDau();
        int u = ucln(tuSo, mauSo);
        tuSo /= u;
        mauSo /= u;
    }

    /** Phan so la so nguyen khi tu so chia het cho mau so. */
    public boolean laSoNguyen() {
        return mauSo != 0 && tuSo % mauSo == 0;
    }

    /** Dua dau am ve tu so, mau so luon duong. */
    private void chuanHoaDau() {
        if (mauSo < 0) {
            tuSo = -tuSo;
            mauSo = -mauSo;
        }
    }

    private int ucln(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a == 0 ? 1 : a;
    }
}
