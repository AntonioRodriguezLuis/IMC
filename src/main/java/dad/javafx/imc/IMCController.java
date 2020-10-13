package dad.javafx.imc;

import java.text.NumberFormat;
import java.text.ParseException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMCController extends Application {

	private TextField pesoText;
	private TextField alturaText;
	private Label imcLabel;
	private Label clasificacionLabel;

	private static final Double PESO_BAJO = 18.5;
	private static final Double PESO_NORMAL = 25.0;
	private static final Double OBESO = 30.0;

	private IMCModel model = new IMCModel();

	@Override
	public void start(Stage primaryStage) throws Exception {

		// TEXT FIELD
		pesoText = new TextField();
		pesoText.setPrefColumnCount(4);

		alturaText = new TextField();
		alturaText.setPrefColumnCount(4);

		// Label
		imcLabel = new Label("");

		clasificacionLabel = new Label();

		// Contenedores
		HBox pesoHBox = new HBox();
		pesoHBox.setSpacing(5);
		pesoHBox.setAlignment(Pos.CENTER);
		pesoHBox.getChildren().addAll(new Label("Peso: "), pesoText, new Label("kg"));

		HBox alturaHBox = new HBox();
		alturaHBox.setSpacing(5);
		alturaHBox.setAlignment(Pos.CENTER);
		alturaHBox.getChildren().addAll(new Label("Altura:"), alturaText, new Label("cm"));

		HBox imcHBox = new HBox();
		imcHBox.setSpacing(5);
		imcHBox.setAlignment(Pos.CENTER);
		imcHBox.getChildren().addAll(new Label("IMC:"), imcLabel);

		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(pesoHBox, alturaHBox, imcHBox, clasificacionLabel);

		pesoText.textProperty().bindBidirectional(model.peso, new NumberStringConverter());
		alturaText.textProperty().bindBidirectional(model.altura, new NumberStringConverter());
		model.clasificacion.bindBidirectional(clasificacionLabel.textProperty());

		imcLabel.textProperty().bind(
				model.peso.divide(((model.altura.divide(100)).multiply((model.altura.divide(100))))).asString("%.2f"));

		imcLabel.textProperty().addListener((o, ov, nv) -> {
			calificacion();
		});

		Scene escena = new Scene(root, 320, 200);

		primaryStage.setScene(escena);
		primaryStage.setTitle("IMC");
		primaryStage.setResizable(true);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	private void calificacion() {
		try {
			if (model.getPeso() != 0 & model.getAltura() != 0) {
				double resultado = NumberFormat.getInstance().parse(imcLabel.getText()).doubleValue();

				// System.out.println(resultado);
				if (resultado < PESO_BAJO) {
					model.setClasificacion("Bajo peso");
				} else if (resultado >= PESO_BAJO & resultado < PESO_NORMAL) {
					clasificacionLabel.setText("Normal");
				} else if (resultado >= PESO_NORMAL & resultado < OBESO) {
					clasificacionLabel.setText("Sobrepeso");
				} else if (resultado >= OBESO) {
					clasificacionLabel.setText("Obeso");
				}
			} else {
				clasificacionLabel.setText("");
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}
	}
}
