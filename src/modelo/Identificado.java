package modelo;
import java.time.LocalDate;

public class Identificado extends Detenido {
	private String Det_Nombre;
	private String Det_Apellido;
	private int DNIdetenido;
	private int LugarSecuestro;
	private LocalDate UltFechaVisto;
	private String Biografia;
	private String MaterialAudivisual;
	private int DniTes;

public Identificado (String nombre, String apellido, int dni, int lugarsecuestro, LocalDate ultfechavisto, String biografia, String audiovisual,int testidni) {
	this.Det_Nombre = nombre;
	this.Det_Apellido = apellido;
	this.DNIdetenido = dni;
	this.LugarSecuestro = lugarsecuestro;
	this.UltFechaVisto = ultfechavisto;
	this.Biografia = biografia;
	this.MaterialAudivisual = audiovisual;
	this.DniTes=testidni;
}



public int TiempoDeCautiverio() {
	return 0;
}
public boolean Sobrevivio () {
	if(isTestigo() == false) {
		return true;
	}
	return false;
}
public String getBiografia() {
return Biografia;
}
public void setBiografia(String biografia) {
Biografia = biografia;
}
public String getMaterialAudivisual() {
return MaterialAudivisual;
}
public void setMaterialAudivisual(String materialAudivisual) {
MaterialAudivisual = materialAudivisual;
}
public String getDet_Nombre() {
return Det_Nombre;
}
public void setDet_Nombre(String det_Nombre) {
Det_Nombre = det_Nombre;
}
public String getDet_Apellido() {
return Det_Apellido;
}
public void setDet_Apellido(String det_Apellido) {
Det_Apellido = det_Apellido;
}
public int getDNIdetenido() {
return DNIdetenido;
}
public void setDNIdetenido(int dNIdetenido) {
DNIdetenido = dNIdetenido;
}
public int getLugarSecuestro() {
return LugarSecuestro;
}
public void setLugarSecuestro(int lugarSecuestro) {
LugarSecuestro = lugarSecuestro;
}
public LocalDate getUltFechaVisto() {
return UltFechaVisto;
}
public void setUltFechaVisto(LocalDate ultFechaVisto) {
UltFechaVisto = ultFechaVisto;
}
public int getDniTes() {
return DniTes;
}
public void setDniTes(int dNItes) {
	DniTes = dNItes;
}
}
