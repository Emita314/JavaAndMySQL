package modelo;

public abstract class Detenido {
boolean testigo;
	
public boolean isTestigo() {
	return testigo;
}

public void setTestigo(boolean testigo) {
	this.testigo = testigo;
}

public Detenido() {
}

public abstract int TiempoDeCautiverio();
public abstract boolean Sobrevivio();

}

