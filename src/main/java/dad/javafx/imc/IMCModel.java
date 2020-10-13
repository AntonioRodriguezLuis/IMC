package dad.javafx.imc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class IMCModel {

	DoubleProperty peso = new SimpleDoubleProperty();
	DoubleProperty altura = new SimpleDoubleProperty();
	StringProperty clasificacion = new SimpleStringProperty();

	public final DoubleProperty pesoProperty() {
		return this.peso;
	}

	public final double getPeso() {
		return this.pesoProperty().get();
	}

	public final void setPeso(final double peso) {
		this.pesoProperty().set(peso);
	}

	public final DoubleProperty alturaProperty() {
		return this.altura;
	}

	public final double getAltura() {
		return this.alturaProperty().get();
	}

	public final void setAltura(final double altura) {
		this.alturaProperty().set(altura);
	}

	public final StringProperty clasificacionProperty() {
		return this.clasificacion;
	}
	

	public final String getClasificacion() {
		return this.clasificacionProperty().get();
	}
	

	public final void setClasificacion(final String clasificacion) {
		this.clasificacionProperty().set(clasificacion);
	}
	

}
