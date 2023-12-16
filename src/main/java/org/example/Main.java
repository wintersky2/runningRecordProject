package org.example;

import org.example.db.DBConnection;

public class Main {
    public static void main(String[] args) {
        Global.initScanner();

        new App().run();

        Global.closeScanner();
    }
}