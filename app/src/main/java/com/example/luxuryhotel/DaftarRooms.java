package com.example.luxuryhotel;

import java.util.ArrayList;

public class DaftarRooms {
    public ArrayList<Rooms> ROOMS;

    public DaftarRooms(){
        ROOMS = new ArrayList();
        ROOMS.add(KINGS_ROOMS);
        ROOMS.add(DIAMONDS_ROOMS);
        ROOMS.add(PREMIUM_ROOMS);
        ROOMS.add(GOLD_ROOMS);
        ROOMS.add(SILVER_ROOMS);
        ROOMS.add(BRONZE_ROOMS);
        ROOMS.add(DOUBLE_ROOMS);
        ROOMS.add(MEETING_ROOMS);
    }

    public static final Rooms KINGS_ROOMS = new Rooms("KINGS ROOMS", 10000000,
            "1-2", "https://miro.medium.com/max/700/1*zh95I9h9V7f-nbIq8m4RRw.png");

    public static final Rooms DIAMONDS_ROOMS = new Rooms("DIAMONDS ROOMS", 7000000,
            "1-2", "https://cdn-brilio-net.akamaized.net/community/2018/07/23/12315/15-desain-kamar-tidur-bak-hotel-bintang-5-ini-bisa-jadi-inspirasimu.jpg");

    public static final Rooms PREMIUM_ROOMS = new Rooms("PREMIUM ROOMS", 5000000,
            "1-2", "https://i2.wp.com/f1-styx.imgix.net/article/2019/10/15154154/cnn.jpg?fit=2226%2C1449&ssl=1");
    public static final Rooms GOLD_ROOMS = new Rooms("GOLD ROOMS", 3500000,
            "1-2", "https://i.pinimg.com/originals/33/18/14/331814ff790334684abca7a5601fb1ae.png");

    public static final Rooms SILVER_ROOMS = new Rooms("SILVER ROOMS", 2500000,
            "1-2", "https://www.expedia.ca/travelblog/wp-content/uploads/2015/04/shangrilaparis.png");

    public static final Rooms BRONZE_ROOMS = new Rooms("BRONZE ROOMS", 1500000,
            "1-2", "https://cf.bstatic.com/images/hotel/max1024x768/939/93978521.jpg");

    public static final Rooms DOUBLE_ROOMS = new Rooms("DOUBLE BED ROOMS", 1000000,
            "1-2", "https://i.pinimg.com/originals/f9/c6/cb/f9c6cb43754ca21148a08f2e41098e6b.png");

    public static final Rooms MEETING_ROOMS = new Rooms("MEETINGS ROOMS", 7500000,
            "15-20", "https://i.pinimg.com/originals/a1/e1/85/a1e18554bcc713465791b6cdf4d549e2.png");
}
