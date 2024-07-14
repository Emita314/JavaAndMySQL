package modelo;

public class Testigo {
private String Tes_Nombre;
private String Tes_Apellido;
private int DNItestigo;
private String Testimonio;

public Testigo(int dNI, String nombre, String apellido, String testimonio2) {
	// TODO Auto-generated constructor stub}
	this.DNItestigo = dNI;
	this.Tes_Nombre = nombre;
	this.Tes_Apellido = apellido;
	this.Testimonio = testimonio2;
}
public Testigo() {
}

public String getTes_Nombre() {
return Tes_Nombre;
}
public void setTes_Nombre(String tes_Nombre) {
Tes_Nombre = tes_Nombre;
}
public String getTes_Apellido() {
return Tes_Apellido;
}
public void setTes_Apellido(String tes_Apellido) {
Tes_Apellido = tes_Apellido;
}
public int getDNItestigo() {
return DNItestigo;
}
public void setDNItestigo(int dNItestigo) {
DNItestigo = dNItestigo;
}
public String getTestimonio() {
return Testimonio;
}
public void setTestimonio(String testimonio) {
Testimonio = testimonio;
}
}

