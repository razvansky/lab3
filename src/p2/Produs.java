package p2;

import java.time.LocalDate;
import java.util.Date;

public class Produs {
    private String _nume;
    private float _pret;
    private int _cantitate;
    private String _dataexp;
    private static float incasari=0;
    public Produs(String nume, float pret, int cantitate, String dataexp) {
        this._nume = nume;
        this._pret = pret;
        this._cantitate = cantitate;
        this._dataexp = dataexp;
    }

    public String getNume() {
        return _nume;
    }
    public void setNume(String nume) {
        this._nume = nume;
    }

    public float getPret() {
        return _pret;
    }

    public void setPret(float pret) {
        this._pret = pret;
    }

    public int getCantitate() {
        return _cantitate;
    }
    public void setCantitate(int cantitate) {
        this._cantitate = cantitate;
    }

    public String getDataexp() {
        return _dataexp;
    }
    public void setDataexp(String dataexp) {
        this._dataexp = dataexp;
    }

    public static float getIncasari() {
        return incasari;
    }
    public static void setIncasari(float incasare) {
        incasari = incasari+incasare;
    }
    @Override
    public String toString() {
        return _nume + " " + _pret + " " + _cantitate + " " + _dataexp;
    }

    public boolean verifExp(){
        String[] split = _dataexp.split("/");
        int an = LocalDate.now().getYear();
        int luna = LocalDate.now().getMonthValue();
        int zi = LocalDate.now().getDayOfMonth();
        if(Integer.valueOf(split[2])<an)
        {

            return true;
        }
        else {
            if (Integer.valueOf(split[0]) < luna) {

                return true;
            } else if (Integer.valueOf(split[0]) == zi)
            {
                if (Integer.valueOf(split[1]) < zi) {

                    return true;
                }
            }
        }
        return false;
    }
}
