module com.lovenndme.petstore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lovenndme.petstore to javafx.fxml;
    exports com.lovenndme.petstore;
    exports com.lovenndme.petstore.animal;
    opens com.lovenndme.petstore.animal to javafx.fxml;
}