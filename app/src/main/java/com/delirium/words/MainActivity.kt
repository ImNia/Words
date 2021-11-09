package com.delirium.words

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.delirium.words.list.WordViewActivity
import com.delirium.words.study.StudyWord
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbUsers : SQLiteDatabase = baseContext.openOrCreateDatabase("usersword.db", MODE_PRIVATE, null)
/*        dbUsers.execSQL("CREATE TABLE IF NOT EXISTS word " +
                "(id TEXT NOT NULL, origin TEXT NOT NULL, translate TEXT NOT NULL, progress REAL NOT NULL, PRIMARY KEY(id))")*/
        /*dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('1971a84d-4dac-44c2-bf94-dbeb270c94bb', 'layout', 'макет', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('54b07d56-4e05-4e9a-bbf2-de20ab134fd2', 'ensure', 'гарантировать', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('0db9312b-0dbb-490b-99da-51f58184cad8', 'essential', 'основной', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('57d688f3-4851-4efa-9b4b-4adb11a949a5', 'appear', 'появляться', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('5b7c3a2d-642c-46f3-81f6-99a4d07d033f', 'occur', 'происходить', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('67495fdb-1093-483d-8971-8bc4e2c49822', 'assume', 'предполагать', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('66b65d7f-4659-4471-ab7f-390e04d07167', 'extend', 'продлевать', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('dcaf5067-9a46-4053-bc4b-9953d1284d33', 'constraint', 'ограничения', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('b1f3bf2e-c73b-47b5-a013-4f2faeb5531a', 'corner', 'угол', 0.0);")
        dbUsers.execSQL("INSERT OR IGNORE INTO word VALUES ('7e3fdff7-a9ba-446c-80af-9498c2c7fe5f', 'expand', 'расширять', 0.0);")*/

//        dbUsers.execSQL("DELETE FROM word WHERE id = '53396555-5df8-496e-b96c-0f184c4ff22e'")
        val query: Cursor = dbUsers.rawQuery("SELECT * FROM word;", null)
//        Log.i("DATABASE_CREATE", "From table:  ${query.getString(0)}")
    }

    fun startStudy(view: View) {
        Log.i("TEST", "Press button Study")
        val studyPage = Intent(this, StudyWord::class.java)
        startActivity(studyPage)
    }

    fun viewWords(view: View) {
        Log.i("TEST", "Press button Words")
        val viewWordsPage = Intent(this, WordViewActivity::class.java)
        startActivity(viewWordsPage)
    }

    fun newWord(view: View) {
        Log.i("TEST", "Press button New  Word")
    }
}