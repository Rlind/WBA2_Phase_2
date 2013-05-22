package generated;
public class demo {

private String eigenschaft;

public String getEigenschaft() {
return eigenschaft;
}

public void setEigenschaft( String neuerWert ) {
this.eigenschaft= neuerWert;
}

}
public List<Veraenderungtype> getVeraenderung() {
    if (veraenderung == null) {
        veraenderung = new ArrayList<Veraenderungtype>();
    }
    return this.veraenderung;
}