package modelo;

public class Fuerza {
private String Fuerza_Npmbre;
private String Ncentro;
public Fuerza(String Fnombre) {
	this.Fuerza_Npmbre=Fnombre;
}
public Fuerza(String Fnombre,String Cnombre) {
	this.Fuerza_Npmbre=Fnombre;
	this.Ncentro=Cnombre;
}
public String getFuerza_Npmbre() {
return Fuerza_Npmbre;
}
public void setFuerza_Npmbre(String fuerza_Npmbre) {
Fuerza_Npmbre = fuerza_Npmbre;
}
public String getNcentro() {
	return Ncentro;
}
public void setNcentro(String ncentro) {
	Ncentro = ncentro;
}
}
