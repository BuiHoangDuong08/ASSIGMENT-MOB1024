/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigmentgd2.Model;

/**
 *
 * @author duong
 */
public class Grade {

    private int Id;
    private float TiengAnh;
    private float Tinhoc;
    private float GDTC;
    private Students students;

    public Grade() {
    }

    public Grade(int Id, float TiengAnh, float Tinhoc, float GDTC, Students students) {
        this.Id = Id;
        this.TiengAnh = TiengAnh;
        this.Tinhoc = Tinhoc;
        this.GDTC = GDTC;
        this.students = students;
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

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

}
