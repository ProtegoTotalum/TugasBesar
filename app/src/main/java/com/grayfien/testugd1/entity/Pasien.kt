package com.grayfien.testugd1.entity

import java.time.LocalDate

class Pasien(
    var name: String,
    var username: String,
    var password: String,
    var email: String,
    var tglLahir: LocalDate,
    var noTelp: Long,
) {

    companion object{
        @JvmField
        var listOfPasien = arrayOf(
            Pasien(
                "Sukmadi",
                "sukmadi_ganteng",
                "ganteng",
                "sukmadi_keren@gmail.com",
                LocalDate.parse("1986-12-25"),
                6281265586542
            ),
            Pasien(
                "Tukiyemc",
                "tuki88",
                "tercantikdidunia",
                "tukiyembeauty@gmail.com",
                LocalDate.parse("1975-06-25"),
                6286654453212
            ),
            Pasien(
                "Tresno",
                "tresno_cool",
                "sopokoe",
                "tresnoiu@hotmail.com",
                LocalDate.parse("1992-09-13"),
                62652331255642

            ),
            Pasien(
                "Puput",
                "putput84",
                "hehehe",
                "putput99@ymail.com",
                LocalDate.parse("1995-03-14"),
                6285541411293

            ),
            Pasien(
                "Boboho",
                "bobohondut",
                "makanmakan",
                "boboho88@gmail.com",
                LocalDate.parse("1998-08-30"),
                62889465212352

            ),
            Pasien(
                "Lala",
                "tralala",
                "lalalili22",
                "tralali@yahoo.com",
                LocalDate.parse("1987-02-29"),
                628599451223

            ),
            Pasien(
                "Bambang",
                "blabla123",
                "bobobo",
                "blabla33@gmail.com",
                LocalDate.parse("1965-09-28"),
                689956422312

            ),
            Pasien(
                "Prius",
                "prius_895",
                "toyota_prius",
                "prius_mobil3@ymail.com",
                LocalDate.parse("1974-11-12"),
                6289556423345

            ),
            Pasien(
                "Inul",
                "inul_daratista@gmail.com",
                "dangdut",
                "inul_d45@gmail.com",
                LocalDate.parse("1985-08-14"),
                628556455123
            ),
            Pasien(
                "Wiro Sableng",
                "wiro_sableng",
                "pendekar99",
                "pendekar@gmail.com",
                LocalDate.parse("1600-12-12"),
                6289556455231
            )
        )
    }

}