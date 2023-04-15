package org.m.mushatha.finalproject.model

data class Category(var id: Int, var name: String, var image: Int) {

    companion object {
        const val TABLE_NAME = "CATEGORY"
        const val COL_ID="id"
        const val COL_NAME="name"
        const val COL_IMAGE="image"

        const val CREATE_TABLE=
            "Create Table $TABLE_NAME(" +
                    "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "$COL_NAME TEXT NOT NULL ," +
                    "$COL_IMAGE BLOB NOT NULL " +
                    ") "
    }
}

