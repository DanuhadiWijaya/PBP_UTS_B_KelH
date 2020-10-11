package com.example.luxuryhotel;

import java.util.ArrayList;

public class DaftarAbout {
    public ArrayList<Abouts> ABOUTS;

    public DaftarAbout(){
        ABOUTS = new ArrayList();
        ABOUTS.add(YOSIA);
        ABOUTS.add(DANU);
        ABOUTS.add(KEVIN);
    }

    public static final Abouts DANU = new Abouts("Putu Danuhadi Wijaya", "180709770", "Fakultas Teknologi Industri",
            "Informatika", "082240441272", "https://vignette.wikia.nocookie.net/upinipin/images/4/4a/1_2_ipin.png/revision/latest?cb=20161210135103");

    public static final Abouts YOSIA = new Abouts("Yosia Galih Yudhistira", "180709971", "Fakultas Teknologi Industri",
            "Informatika", "082231241184", "https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");

    public static final Abouts KEVIN = new Abouts("Kevin Varlet", "170709400", "Fakultas Teknologi Industri", "Informatika", "", "https://www.concrete.com.eg/uploads/1b/eb/summer-2020-men-casual.jpg");

}
