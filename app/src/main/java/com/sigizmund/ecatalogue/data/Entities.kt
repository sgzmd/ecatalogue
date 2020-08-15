package com.sigizmund.ecatalogue.data

import android.content.Context
import androidx.room.*


@Entity(tableName = "book")
data class Book(
    @PrimaryKey @ColumnInfo(name = "book_id") val id: Int,
    @ColumnInfo(name = "book_title") var title: String
)

@Entity(tableName = "author")
data class Author(
    @PrimaryKey @ColumnInfo(name = "author_id") val id: Int,
    @ColumnInfo(name = "author_name") var name: String
)

@Entity(
    tableName = "book_author_join",
    primaryKeys = ["book_id", "author_id"],
    foreignKeys = [
        ForeignKey(entity = Book::class, parentColumns = ["book_id"], childColumns = ["book_id"]),
        ForeignKey(
            entity = Author::class,
            parentColumns = ["author_id"],
            childColumns = ["author_id"]
        )
    ]
)
data class BookAuthorJoin(
    @ColumnInfo(name = "book_id") val bookId: Int,
    @ColumnInfo(name = "author_id") val authorId: Int
)

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun loadAllBooks(): Array<Book>

    @Query("SELECT * FROM book WHERE book_id IN (SELECT book_id FROM book_author_join WHERE author_id = :authorId)")
    fun loadAllBooksForAuthor(authorId: Int): Array<Book>


    @Insert
    fun insertBook(vararg books: Book): Array<Long>

    @Insert
    fun insertAuthor(vararg authors: Author)

    @Insert
    fun insertBookAuthor(bookAuthor: BookAuthorJoin)

    @Query("SELECT * from book where book_id = :bookId")
    fun loadBookById(bookId: Long): Book
}

@Database(
    entities = [Book::class, Author::class, BookAuthorJoin::class],
    version = 1,
    exportSchema = true
)
abstract class BookDatabase : RoomDatabase() {
    // Singleton prevents multiple instances of database opening at the
    // same time.

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

