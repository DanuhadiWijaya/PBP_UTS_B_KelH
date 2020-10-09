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
            "1-2", "http://www.travellermade.com/wp-content/uploads/2012/07/Fairmont-Beijing_1.png");

    public static final Rooms PREMIUM_ROOMS = new Rooms("PREMIUM ROOMS", 5000000,
            "1-2", "https://lh3.googleusercontent.com/proxy/5LwPTzbjk0CJcta2hlE5wY-Y7Jim-F6pNQ1Ux-yB5o2aoeDlhVCB6wuf2iNFjfP3zk94jW_ZlH6HaJGOAkzWnC_A7vdW9sDjkHodqHzocdza7MYU3-K8YX9b9mb3xn4m-iB46uo");

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
