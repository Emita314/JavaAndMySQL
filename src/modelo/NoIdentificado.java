package modelo;


public class NoIdentificado extends Detenido {
private String Apodo;
private String Descripcion;
private int dniTes;
private String NombreCentro;

public NoIdentificado(String apodo,String desc,int dniTestigo) {
	this.Apodo = apodo;
	this.Descripcion = desc;
	this.dniTes=dniTestigo;
}
public NoIdentificado(String apod,String desc,int dniTestigo,String centros) {
	this.Apodo = apod;
	this.Descripcion = desc;
	this.dniTes=dniTestigo;
	this.NombreCentro=centros;
}
public NoIdentificado(String apo,String centro) {
	this.Apodo = apo;
	this.NombreCentro=centro;
}



public int TiempoDeCautiverio() {
	
	//CCDTyE ccdtye;
	//int dias = (int) ((ccdtye.getFechaCierre().getTime() - getUltFechaVisto().getTime()));
			
	//return dias;
	return 2;
			}
public boolean Sobrevivio () {
	return false;
}
public String getApodo() {
return Apodo;
}
public void setApodo(String apodo) {
Apodo = apodo;
}
public String getDescripcion() {
return Descripcion;
}
public void setDescripcion(String descripcion) {
Descripcion = descripcion;
}


public int getDniTes() {
	return dniTes;
}


public void setDniTes(int dniTes) {
	this.dniTes = dniTes;
}


public String getNombreCentro() {
	return NombreCentro;
}


public void setNombreCentro(String nombreCentro) {
	NombreCentro = nombreCentro;
}
}