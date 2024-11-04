module com.example.ru_pizzaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ru_pizzaria to javafx.fxml;
    exports com.example.ru_pizzaria;
}