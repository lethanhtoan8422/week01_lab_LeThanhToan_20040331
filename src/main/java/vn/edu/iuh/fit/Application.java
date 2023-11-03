package vn.edu.iuh.fit;

import vn.edu.iuh.fit.db.ConnectionDB;

public class Application {
    public static void main(String[] args) {
        ConnectionDB.getInstance();
    }
}
