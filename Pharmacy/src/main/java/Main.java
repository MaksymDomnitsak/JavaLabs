import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;

import dao.*;
import model.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //new MedicineDAO().createMedicinesTable();
        new SellingDAO().createSellingTable();
        //new MedicineDAO().loadtoMedicines(new Medicine("Стрептоцид",new Manufacturer(2,"","","",""),new Destination(1,"Антибактеріальні"),true,25.50));
        new SellingDAO().loadtoSelling(new Selling(new Medicine(2,"",new Manufacturer("","","",""),new Destination(),false,0.0),
                2,LocalDateTime.parse("2021-12-11T12:00:54", DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
        //List<Medicine> list = new MedicineDAO().all();
        List<Selling> listsell = new SellingDAO().readAll();
        //new ManufacturerDAO().deleteManufacturer(1);
        listsell.get(0);
        System.out.println("Complete!");
    }
}
