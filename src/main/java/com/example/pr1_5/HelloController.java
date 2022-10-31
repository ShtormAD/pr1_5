package com.example.pr1_5;

import javafx.fxml.FXML;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Spinner<Integer> SPNR_X;
    @FXML
    private Spinner<Integer> SPNR_Y;
    @FXML
    private TableView TV;
    @FXML
    private TextField TF_p;
    @FXML
    private TextField TF_q;
    @FXML
    private Label LBL_x;
    @FXML
    private Label LBL_y;
    int x = 0, y = 0;

    @FXML
    public void initialize(){
        SpinnerValueFactory<Integer> valueFactoryX = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);
        SpinnerValueFactory<Integer> valueFactoryY = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10, 2);
        SPNR_X.setValueFactory(valueFactoryX);
        SPNR_Y.setValueFactory(valueFactoryY);
    }
    @FXML
    protected void onHelloButtonClick() {
        int p, q;
        String ws;
        StringBuilder xs = new StringBuilder("Массив Х: ");
        StringBuilder ys = new StringBuilder("Массив Y: ");

        x = SPNR_X.getValue();
        y = SPNR_Y.getValue();
        int[] mas_x = new int[x];
        int[] mas_y = new int[y];
        try{
            p = Integer.parseInt(TF_p.getText()) - 1;
            q = Integer.parseInt(TF_q.getText()) - 1;
            for(int i = 0; i <= x-1; i++){
                ws = TV.queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN,p, i).toString();
                mas_x[i] = Integer.parseInt(ws.substring(ws.indexOf('\'')+1, ws.lastIndexOf('\'')));
            }
            for(int i = 0; i <= y-1; i++){
                ws = TV.queryAccessibleAttribute(AccessibleAttribute.CELL_AT_ROW_COLUMN,i, q).toString();
                mas_y[i] = Integer.parseInt(ws.substring(ws.indexOf('\'')+1, ws.lastIndexOf('\'')));
            }

            for (int i:mas_x) {
                xs.append(i).append(", ");
            }
            for (int i:mas_y) {
                ys.append(i).append(", ");
            }
            LBL_x.setText(xs.toString());
            LBL_y.setText(ys.toString());
        } catch (Exception e){
            ALARM("Проверьте корректность заполнения полей!");
        }
    }
    @FXML
    protected void recreateTable(){
        TV.getColumns().clear();
        TV.getItems().clear();
        x = SPNR_X.getValue();
        y = SPNR_Y.getValue();
        for(int i = 0; i < x; i++){
            TableColumn<List<String>, String> col = new TableColumn<>(String.valueOf(i+1));
            col.setMaxWidth(29);
            col.setEditable(true);
            col.setCellFactory(TextFieldTableCell.<List<String>>forTableColumn());
            TV.getColumns().add(col);
        }
        for(int i = 0; i < y; i++){
            TV.getItems().add(i);
        }
    }
    private void ALARM(String s){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Внимание!");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
}