package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
public class CCDTyE {
private String CCDTyENombre;
private int Ubicacion;
private LocalDate FechaApertura;
private LocalDate FechaCierre;
private String fuerzaN;

public CCDTyE(String nombre, int ubicacion, LocalDate apertura, LocalDate cierre) {
this.CCDTyENombre = nombre;
this.FechaApertura = apertura;
this.FechaCierre = cierre;
this.Ubicacion = ubicacion;
}
public CCDTyE(String nombre, int ubicacion, LocalDate apertura, LocalDate cierre,String FN) {
this.CCDTyENombre = nombre;
this.FechaApertura = apertura;
this.FechaCierre = cierre;
this.Ubicacion = ubicacion;
this.fuerzaN=FN;
}
public CCDTyE() {
}

ArrayList <Fuerza> fuerzas = new ArrayList<>();

public String getCCDTyENombre() {
return CCDTyENombre;
}
public void setCCDTyENombre(String cCDTyENombre) {
CCDTyENombre = cCDTyENombre;
}
public int getUbicacion() {
return Ubicacion;
}
public void setUbicacion(int ubicacion) {
Ubicacion = ubicacion;
}
public LocalDate getFechaApertura() {
return FechaApertura;
}
public void setFechaApertura(LocalDate fechaApertura) {
FechaApertura = fechaApertura;
}
public LocalDate getFechaCierre() {
return FechaCierre;
}
public void setFechaCierre(LocalDate fechaCierre) {
FechaCierre = fechaCierre;
}
public String getFuerzaN() {
	return fuerzaN;
}
public void setFuerzaN(String fuerzaN) {
	this.fuerzaN = fuerzaN;
}
}

