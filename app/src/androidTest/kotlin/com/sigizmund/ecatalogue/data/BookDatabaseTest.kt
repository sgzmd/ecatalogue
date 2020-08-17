package com.sigizmund.ecatalogue.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookDatabaseTest {

    private lateinit var bookDao: BookDao
    private lateinit var db: BookDatabase

    private var bookId: Long = 0

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BookDatabase::class.java).build()
        bookDao = db.bookDao()

        bookId = bookDao.insertBook(Book(0, "Test Book"))[0]
    }

    @Test
    fun smokeTest() {
        assertThat(bookDao, not(nullValue()))
    }

    @Test
    fun getBooks() {
        val book = bookDao.loadBookById(bookId)
        assertThat(book.title, equalTo("Test Book"))
    }
}