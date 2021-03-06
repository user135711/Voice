package de.ph1b.audiobook.data.repo.internals.migrations

import android.content.ContentValues
import androidx.room.OnConflictStrategy
import androidx.sqlite.db.SupportSQLiteDatabase
import de.ph1b.audiobook.data.repo.internals.transaction

class Migration37to38 : IncrementalMigration(37) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.transaction {
      // add new chapter mark table
      db.execSQL("ALTER TABLE tableChapters ADD marks TEXT")

      // invalidate modification time stamps so the chapters will be re-scanned
      val cv = ContentValues().apply {
        put("lastModified", 0)
      }
      db.update("tableChapters", OnConflictStrategy.FAIL, cv, null, null)
    }
  }
}
