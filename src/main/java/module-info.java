module com.example.detyra3_siguria_e_te_dhenave {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.detyra3_siguria_e_te_dhenave to javafx.fxml;
    exports com.example.detyra3_siguria_e_te_dhenave;
    exports rsa;
    opens rsa to javafx.fxml;
}