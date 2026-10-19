import java.util.Calendar;
import java.util.Scanner;

/**
 * Toan bo bai tap trong slide Java - B2 - Ngon ngu.
 *
 * Scanner (trang 16):
 *  1) Nhap so nguyen [0, 100], nhap lai neu sai
 *  2) Nhap nam sinh [1900, 2010], in tuoi
 *
 * Mang (trang 21):
 *  3) Nhap mang n phan tu, in mang va tong
 *  4) Nhap mang, tim chi so cua 1 so
 *  5) Nhap mang, tim phan tu lon nhat (Math.max)
 *  6) Nhap so den khi tong > 50
 *
 * Chuoi (trang 30):
 *  7) Tach ho va ten tu chuoi ho ten
 *
 * Bai tap cuoi (trang 31):
 *  8) Nhap mang n phan tu, tinh tong
 *  9) Ham nhan 2 mang, in mang tong
 * 10) Nhap mang bang 1 chuoi cach nhau boi dau cach
 */
public class BaiTapB2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== BAI TAP B2 - NGON NGU JAVA =====");
        System.out.println("1. Nhap so nguyen [0, 100]");
        System.out.println("2. Nhap nam sinh [1900, 2010], in tuoi");
        System.out.println("3. Nhap mang, in mang va tong");
        System.out.println("4. Tim chi so phan tu trong mang");
        System.out.println("5. Tim phan tu lon nhat");
        System.out.println("6. Nhap so den khi tong > 50");
        System.out.println("7. Noi chuoi - Tach ho va ten");
        System.out.println("8. Nhap mang, tinh tong");
        System.out.println("9. Mang tong cua 2 mang");
        System.out.println("10. Nhap mang bang 1 chuoi");
        System.out.println("0. Chay demo tat ca (dung du lieu mau)");
        System.out.print("Chon bai (0-10): ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> bai1NhapSoTrongKhoang(sc);
            case 2 -> bai2TinhTuoi(sc);
            case 3 -> bai3InMangVaTong(sc);
            case 4 -> bai4TimChiSo(sc);
            case 5 -> bai5TimMax(sc);
            case 6 -> bai6TongLonHon50(sc);
            case 7 -> bai7TachHoTen(sc);
            case 8 -> bai8TongMang(sc);
            case 9 -> bai9MangTong(sc);
            case 10 -> bai10ChuoiMang(sc);
            case 0 -> chayDemoTatCa();
            default -> System.out.println("Lua chon khong hop le.");
        }

        sc.close();
    }

    // ---------- Scanner – Bai tap ----------

    /** Nhap 1 so nguyen trong [0, 100], nhap lai neu ngoai khoang. */
    static void bai1NhapSoTrongKhoang(Scanner sc) {
        System.out.println("\n--- Bai 1: Nhap so nguyen [0, 100] ---");
        int n;
        do {
            System.out.print("Nhap so nguyen trong [0, 100]: ");
            n = sc.nextInt();
            if (n < 0 || n > 100) {
                System.out.println("So ngoai khoang, vui long nhap lai!");
            }
        } while (n < 0 || n > 100);
        System.out.println("So da nhap: " + n);
    }

    /** Nhap nam sinh [1900, 2010], in tuoi (dung Calendar). */
    static void bai2TinhTuoi(Scanner sc) {
        System.out.println("\n--- Bai 2: Nam sinh & tuoi ---");
        int namSinh;
        do {
            System.out.print("Nhap nam sinh [1900, 2010]: ");
            namSinh = sc.nextInt();
            if (namSinh < 1900 || namSinh > 2010) {
                System.out.println("Nam sinh ngoai khoang, vui long nhap lai!");
            }
        } while (namSinh < 1900 || namSinh > 2010);

        Calendar c = Calendar.getInstance();
        int namHienTai = c.get(Calendar.YEAR);
        int tuoi = namHienTai - namSinh;
        System.out.println("Nam hien tai: " + namHienTai);
        System.out.println("Tuoi: " + tuoi);
    }

    // ---------- Mang – Bai tap ----------

    /** Nhap n phan tu, in mang va tong. */
    static void bai3InMangVaTong(Scanner sc) {
        System.out.println("\n--- Bai 3: In mang va tong ---");
        int[] a = nhapMang(sc);
        inMang(a);
        System.out.println("Tong: " + tinhTong(a));
    }

    /** Nhap mang, sau do nhap 1 so, in chi so cua so do. */
    static void bai4TimChiSo(Scanner sc) {
        System.out.println("\n--- Bai 4: Tim chi so phan tu ---");
        int[] a = nhapMang(sc);
        inMang(a);
        System.out.print("Nhap so can tim: ");
        int x = sc.nextInt();

        int index = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            System.out.println("Chi so cua " + x + " la: " + index);
        } else {
            System.out.println("Khong tim thay " + x + " trong mang.");
        }
    }

    /** Nhap mang, tim phan tu lon nhat (goi y: Math.max). */
    static void bai5TimMax(Scanner sc) {
        System.out.println("\n--- Bai 5: Phan tu lon nhat ---");
        int[] a = nhapMang(sc);
        inMang(a);

        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            max = Math.max(max, a[i]);
        }
        System.out.println("Phan tu lon nhat: " + max);
    }

    /** Nhap cac so nguyen, neu tong > 50 thi dung va in tong. */
    static void bai6TongLonHon50(Scanner sc) {
        System.out.println("\n--- Bai 6: Nhap so den khi tong > 50 ---");
        int tong = 0;
        while (tong <= 50) {
            System.out.print("Nhap so nguyen: ");
            int x = sc.nextInt();
            tong += x;
            System.out.println("Tong hien tai: " + tong);
        }
        System.out.println("Tong cac so da nhap: " + tong);
    }

    // ---------- Chuoi – Bai tap ----------

    /**
     * Noi chuoi – Bai tap (slide B2):
     * Nhap ho ten. Chu cuoi cung la ten, con lai la ho. Cat khoang trang thua.
     * Vi du: "Nguyen    Van   An  " -> Ho: Nguyen Van, Ten: An
     *        "Bui Trung" -> Ho: Bui, Ten: Trung
     */
    static void bai7TachHoTen(Scanner sc) {
        System.out.println("\n--- Bai 7: Noi chuoi – Tach ho va ten ---");
        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine().trim();
        // Cat khoang trang thua (nhieu dau cach lien tiep -> 1 dau cach)
        while (hoTen.contains("  ")) {
            hoTen = hoTen.replace("  ", " ");
        }

        int viTriCuoi = hoTen.lastIndexOf(' ');
        if (viTriCuoi < 0) {
            System.out.println("Ho: ");
            System.out.println("Ten: " + hoTen);
        } else {
            String ho = hoTen.substring(0, viTriCuoi).trim();
            String ten = hoTen.substring(viTriCuoi + 1).trim();
            System.out.println("Ho: " + ho);
            System.out.println("Ten: " + ten);
        }
    }

    // ---------- Bai tap trang 31 ----------

    /** Nhap n phan tu, tinh va in tong. */
    static void bai8TongMang(Scanner sc) {
        System.out.println("\n--- Bai 8: Tong mang ---");
        int[] a = nhapMang(sc);
        System.out.println("Tong: " + tinhTong(a));
    }

    /**
     * Ham nhan 2 mang, in mang tong.
     * So phan tu = mang dai hon; thieu thi coi nhu 0.
     */
    static void bai9MangTong(Scanner sc) {
        System.out.println("\n--- Bai 9: Mang tong cua 2 mang ---");
        System.out.println("Nhap mang 1:");
        int[] a = nhapMang(sc);
        System.out.println("Nhap mang 2:");
        int[] b = nhapMang(sc);
        inMangTong(a, b);
    }

    static void inMangTong(int[] a, int[] b) {
        int n = Math.max(a.length, b.length);
        int[] tong = new int[n];
        for (int i = 0; i < n; i++) {
            int x = i < a.length ? a[i] : 0;
            int y = i < b.length ? b[i] : 0;
            tong[i] = x + y;
        }
        System.out.print("Mang tong: ");
        inMang(tong);
    }

    /**
     * Nhap mang bang 1 chuoi, cac phan tu cach nhau boi dau cach.
     * Vi du: "3 4 5 33 554 67 23 6 8 5" -> So phan tu: 10; Tong: 708
     */
    static void bai10ChuoiMang(Scanner sc) {
        System.out.println("\n--- Bai 10: Nhap mang bang 1 chuoi ---");
        System.out.print("Nhap day so (cach nhau boi dau cach): ");
        String line = sc.nextLine().trim();
        while (line.contains("  ")) {
            line = line.replace("  ", " ");
        }

        if (line.isEmpty()) {
            System.out.println("So phan tu: 0; Tong: 0");
            return;
        }

        String[] parts = line.split(" ");
        int tong = 0;
        for (String p : parts) {
            tong += Integer.parseInt(p);
        }
        System.out.println("So phan tu: " + parts.length + "; Tong: " + tong);
    }

    // ---------- Tien ich ----------

    static int[] nhapMang(Scanner sc) {
        System.out.print("Nhap so phan tu n: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("a[" + i + "] = ");
            a[i] = sc.nextInt();
        }
        return a;
    }

    static void inMang(int[] a) {
        System.out.print("Mang: ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if (i < a.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int tinhTong(int[] a) {
        int s = 0;
        for (int x : a) {
            s += x;
        }
        return s;
    }

    /** Demo khong can nhap tay (dung du lieu mau). */
    static void chayDemoTatCa() {
        System.out.println("\n===== DEMO TAT CA BAI TAP B2 =====");

        System.out.println("\n--- Bai 1 (mau: nhap 150 roi 42) ---");
        int n = 150;
        while (n < 0 || n > 100) {
            System.out.println("Nhap: " + n + " -> ngoai khoang, nhap lai");
            n = 42;
        }
        System.out.println("So da nhap: " + n);

        System.out.println("\n--- Bai 2 (mau: nam sinh 2000) ---");
        int namSinh = 2000;
        Calendar c = Calendar.getInstance();
        int namHienTai = c.get(Calendar.YEAR);
        System.out.println("Nam sinh: " + namSinh);
        System.out.println("Nam hien tai: " + namHienTai);
        System.out.println("Tuoi: " + (namHienTai - namSinh));

        int[] mang = {3, 4, 5, 33, 554, 67, 23, 6, 8, 5};
        System.out.println("\n--- Bai 3 ---");
        inMang(mang);
        System.out.println("Tong: " + tinhTong(mang));

        System.out.println("\n--- Bai 4 (tim 33) ---");
        int x = 33;
        int index = -1;
        for (int i = 0; i < mang.length; i++) {
            if (mang[i] == x) {
                index = i;
                break;
            }
        }
        System.out.println("Chi so cua " + x + " la: " + index);

        System.out.println("\n--- Bai 5 ---");
        int max = mang[0];
        for (int i = 1; i < mang.length; i++) {
            max = Math.max(max, mang[i]);
        }
        System.out.println("Phan tu lon nhat: " + max);

        System.out.println("\n--- Bai 6 (mau: 10 15 20 10) ---");
        int[] day = {10, 15, 20, 10};
        int tong = 0;
        for (int v : day) {
            tong += v;
            System.out.println("Nhap: " + v + " -> Tong hien tai: " + tong);
            if (tong > 50) {
                break;
            }
        }
        System.out.println("Tong cac so da nhap: " + tong);

        System.out.println("\n--- Bai 7: Noi chuoi – Tach ho va ten ---");
        String[] mau = {"Nguyen    Van   An  ", "Bui Trung"};
        for (String hoTen : mau) {
            String s = hoTen.trim();
            while (s.contains("  ")) {
                s = s.replace("  ", " ");
            }
            int pos = s.lastIndexOf(' ');
            System.out.println("Input: \"" + hoTen + "\"");
            System.out.println("  Ho: \"" + s.substring(0, pos).trim() + "\"");
            System.out.println("  Ten: \"" + s.substring(pos + 1).trim() + "\"");
        }

        System.out.println("\n--- Bai 8 ---");
        System.out.println("Tong: " + tinhTong(mang));

        System.out.println("\n--- Bai 9 ---");
        int[] a = {1, 2, 3};
        int[] b = {10, 20, 30, 40};
        System.out.print("Mang 1: ");
        inMang(a);
        System.out.print("Mang 2: ");
        inMang(b);
        inMangTong(a, b);

        System.out.println("\n--- Bai 10 ---");
        String line = "3 4 5 33 554 67 23 6 8 5";
        String[] parts = line.split(" ");
        int sum = 0;
        for (String p : parts) {
            sum += Integer.parseInt(p);
        }
        System.out.println("Input: \"" + line + "\"");
        System.out.println("So phan tu: " + parts.length + "; Tong: " + sum);
    }
}
