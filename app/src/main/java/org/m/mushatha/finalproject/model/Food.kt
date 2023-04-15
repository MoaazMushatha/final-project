package org.m.mushatha.finalproject.model


class Food(var id: Int, var name: String, var image: ByteArray, var price: Double, var categoty: String, var isFavorite: Int, var rate: Int) {

    companion object {
        const val TABLE_NAME = "food_table"
        const val TABLE_NAME_CATEGORY = "category_table"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_IMAGE = "image"
        const val COL_PRICE = "price"
        const val COL_CATEGORY = "category"
        const val COL_IS_FAVORITE = "isFavorite"
        const val COL_RATE = "rate"

        const val TABLE_CREATE =
            "CREATE TABLE $TABLE_NAME(" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "$COL_NAME TEXT NOT NULL ," +
                    "$COL_IMAGE BLOB NOT NULL  ," +
                    "$COL_PRICE DOUBLE NOT NULL ,"+
                    "$COL_CATEGORY TEXT NOT NULL , " +
                    "$COL_IS_FAVORITE INTEGER DEFAULT -1 ," +
                    "$COL_RATE INTEGER NOT NULL" +
                    ")"


    const val TABLE_CREATE_CATEGORY =
        "create table $TABLE_NAME_CATEGORY(" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$COL_NAME TEXT NOT NULL ," +
                "$COL_IMAGE BLOB NOT NULL " +
                ")"
}
}
