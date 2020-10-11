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
            "Informatika", "180709770@students.uajy.ac.id", "https://vignette.wikia.nocookie.net/spongebob/images/a/ac/Spongebobwithglasses.jpeg/revision/latest?cb=20121014113150");

    public static final Abouts YOSIA = new Abouts("Yosia Galih Yudhistira", "180709971", "Fakultas Teknologi Industri",
            "Informatika", "180709971@students.uajy.ac.id", "https://static.miraheze.org/loathsomecharacterswiki/7/7e/Patrick_Star.png");

    public static final Abouts KEVIN = new Abouts("Kevin Varlet", "170709400", "Fakultas Teknologi Industri",
            "Informatika", "170709400@students.uajy.ac.id", "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/405610ed-0ed8-404b-b733-b0bc5ad9d340/ddhxhn0-0e568ebc-4af0-4493-a894-5f59375fb9db.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvNDA1NjEwZWQtMGVkOC00MDRiLWI3MzMtYjBiYzVhZDlkMzQwXC9kZGh4aG4wLTBlNTY4ZWJjLTRhZjAtNDQ5My1hODk0LTVmNTkzNzVmYjlkYi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.Q_jGpc9SQ4U8bOf_S2HRpJGxOlBwocDWenAeeOLuz8I");

}
