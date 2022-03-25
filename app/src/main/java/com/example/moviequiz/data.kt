package com.example.moviequiz

import com.example.moviequiz.models.QuizModel

object data {
    val list = arrayListOf<QuizModel>(
        QuizModel(
            "รูปนี้มาจากหนังเรื่องอะไร?",
            R.drawable.poster_the_batman,
            arrayListOf<String>(
                "a. Avengers: End Game",
                "b. Spiderman: No Way home",
                "c. Captain America: Winters Solider",
                "d. The Batman"
            ),
            3
        ),
        QuizModel(
            "ฮีโร่คนไหนตายในเรื่อง?",
            R.drawable.poster_endgame,
            arrayListOf<String>(
                "a. กัปตันอเมริกา",
                "b. แบทแมน",
                "c. ไอออนแมน",
                "d. สไปเดอร์แมน"
            ),
            2
        ),
        QuizModel(
            "รูปนี้มาจากหนังเรื่องอะไร?",
            R.drawable.poster_no_way_home,
            arrayListOf<String>(
                "a. กัปตันอเมริกา",
                "b. แบทแมน",
                "c. ไอออนแมน",
                "d. สไปเดอร์แมน"
            ),
            3
        ),
        QuizModel(
            "ตัวละครตัวนี้เป็นใคร?",
            R.drawable.poster_notmovie,
            arrayListOf<String>(
                "a. ตัวประกอบ",
                "b. จักรกล",
                "c. ไม่ได้มาจากหนัง",
                "d. แบทแมน"
            ),
            2
        ),
        QuizModel(
            "จากรูปเบื้องหลังหนังเรื่องนี้ ใครเป็นนักแสดงนำ?",
            R.drawable.poster_will_smith,
            arrayListOf<String>(
                "a. ลีโอนาร์โด ดิแคพรีโอ",
                "b. วิลล์ สมิธ",
                "c. เจค จิลเลนฮาล",
                "d. ซาโต้ ทาเครุ"
            ),
            1
        ),
        QuizModel(
            "จากรูปเบื้องหลังหนังเรื่องนี้ ใครเป็นนักแสดงนำ?",
            R.drawable.poster_the_wolf,
            arrayListOf<String>(
                "a. ลีโอนาร์โด ดิแคพรีโอ",
                "b. วิลล์ สมิธ",
                "c. เจค จิลเลนฮาล",
                "d. ซาโต้ ทาเครุ"
            ),
            0
        ),
    )
}