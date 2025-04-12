/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigment1;

/**
 *
 * @author duong
 */
public class Diem extends SinhVien{
    private int Id;
    private float TiengAnh;
    private float Tinhoc;
    private float GDTC;
    
    public double getDiemTB(){
        return (this.TiengAnh + this.Tinhoc + this.GDTC) / 3;
    }

    public Diem() {
    }
    
    public Diem(int Id, float Tinhoc, float GDTC, String MASV, String Hoten, String Email, String SoDT, boolean GioiTinh, String DiaChi, String Hinh) {
        super(MASV, Hoten, Email, SoDT, GioiTinh, DiaChi, Hinh);
        this.Id = Id;
        this.Tinhoc = Tinhoc;
        this.GDTC = GDTC;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public float getTiengAnh() {
        return TiengAnh;
    }

    public void setTiengAnh(float TiengAnh) {
        this.TiengAnh = TiengAnh;
    }

    public float getTinhoc() {
        return Tinhoc;
    }

    public void setTinhoc(float Tinhoc) {
        this.Tinhoc = Tinhoc;
    }

    public float getGDTC() {
        return GDTC;
    }

    public void setGDTC(float GDTC) {
        this.GDTC = GDTC;
    }
    
}
