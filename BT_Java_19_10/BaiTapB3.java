/**
 * Bai tap 1 va Bai tap 2 trong slide Java - B3 - OOP.
 *
 * Bai tap 1 (trang 24):
 *  - Lop Diem: double x, y; constructor; getter/setter
 *  - Lop DuongThang: Diem A, B; constructor; getter; tinh do dai
 *
 * Bai tap 2 (trang 25):
 *  - Lop Diem
 *  - Lop TamGiac (3 diem): chu vi, dien tich
 *  - Lop ChuNhat (2 canh): chu vi, dien tich
 *  - Lop HinhTron (diem O, ban kinh): chu vi, dien tich
 *  - Main: khoi tao 2 TamGiac, 2 ChuNhat, 2 HinhTron; in chu vi & dien tich
 */
public class BaiTapB3 {

    public static void main(String[] args) {
        System.out.println("===== BAI TAP 1: Diem & DuongThang =====");
        chayBaiTap1();

        System.out.println("\n===== BAI TAP 2: TamGiac, ChuNhat, HinhTron =====");
        chayBaiTap2();
    }

    static void chayBaiTap1() {
        Diem a = new Diem(0, 0);
        Diem b = new Diem(3, 4);
        DuongThang dt = new DuongThang(a, b);

        System.out.println("Diem A: (" + dt.getA().getX() + ", " + dt.getA().getY() + ")");
        System.out.println("Diem B: (" + dt.getB().getX() + ", " + dt.getB().getY() + ")");
        System.out.printf("Do dai AB = %.4f%n", dt.doDai());

        // Thu getter/setter
        Diem c = new Diem(1, 1);
        c.setX(2);
        c.setY(5);
        DuongThang dt2 = new DuongThang(c, new Diem(5, 9));
        System.out.println("Diem C sau set: (" + c.getX() + ", " + c.getY() + ")");
        System.out.printf("Do dai CD = %.4f%n", dt2.doDai());
    }

    static void chayBaiTap2() {
        TamGiac tg1 = new TamGiac(new Diem(0, 0), new Diem(4, 0), new Diem(0, 3));
        TamGiac tg2 = new TamGiac(new Diem(1, 1), new Diem(5, 1), new Diem(3, 4));

        ChuNhat cn1 = new ChuNhat(5, 3);
        ChuNhat cn2 = new ChuNhat(10, 2.5);

        HinhTron ht1 = new HinhTron(new Diem(0, 0), 2);
        HinhTron ht2 = new HinhTron(new Diem(1, 1), 5);

        System.out.println("--- Tam giac ---");
        inHinh("Tam giac 1", tg1.chuVi(), tg1.dienTich());
        inHinh("Tam giac 2", tg2.chuVi(), tg2.dienTich());

        System.out.println("--- Chu nhat ---");
        inHinh("Chu nhat 1", cn1.chuVi(), cn1.dienTich());
        inHinh("Chu nhat 2", cn2.chuVi(), cn2.dienTich());

        System.out.println("--- Hinh tron ---");
        inHinh("Hinh tron 1", ht1.chuVi(), ht1.dienTich());
        inHinh("Hinh tron 2", ht2.chuVi(), ht2.dienTich());
    }

    static void inHinh(String ten, double chuVi, double dienTich) {
        System.out.printf("%s | Chu vi = %.4f | Dien tich = %.4f%n", ten, chuVi, dienTich);
    }
}

/** Diem tren mat phang toa do. */
class Diem {
    private double x;
    private double y;

    public Diem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double khoangCach(Diem other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

/** Duong thang qua 2 diem A, B. */
class DuongThang {
    private Diem A;
    private Diem B;

    public DuongThang(Diem A, Diem B) {
        this.A = A;
        this.B = B;
    }

    public Diem getA() {
        return A;
    }

    public Diem getB() {
        return B;
    }

    public double doDai() {
        return A.khoangCach(B);
    }
}

/** Tam giac tao boi 3 diem. */
class TamGiac {
    private Diem a;
    private Diem b;
    private Diem c;

    public TamGiac(Diem a, Diem b, Diem c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double chuVi() {
        return a.khoangCach(b) + b.khoangCach(c) + c.khoangCach(a);
    }

    /** Dien tich theo cong thuc Heron. */
    public double dienTich() {
        double ab = a.khoangCach(b);
        double bc = b.khoangCach(c);
        double ca = c.khoangCach(a);
        double p = (ab + bc + ca) / 2.0;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
    }
}

/** Hinh chu nhat voi do dai 2 canh. */
class ChuNhat {
    private double chieuDai;
    private double chieuRong;

    public ChuNhat(double chieuDai, double chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    public double chuVi() {
        return 2 * (chieuDai + chieuRong);
    }

    public double dienTich() {
        return chieuDai * chieuRong;
    }
}

/** Hinh tron voi tam O va ban kinh. */
class HinhTron {
    private Diem O;
    private double banKinh;

    public HinhTron(Diem O, double banKinh) {
        this.O = O;
        this.banKinh = banKinh;
    }

    public Diem getO() {
        return O;
    }

    public double getBanKinh() {
        return banKinh;
    }

    public double chuVi() {
        return 2 * Math.PI * banKinh;
    }

    public double dienTich() {
        return Math.PI * banKinh * banKinh;
    }
}
