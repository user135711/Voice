package de.ph1b.audiobook.features.bookOverview.list

import de.ph1b.audiobook.data.Book
import de.ph1b.audiobook.features.bookOverview.list.header.BookOverviewHeaderComponent
import de.ph1b.audiobook.misc.recyclerComponent.CompositeListAdapter
import java.util.UUID

typealias BookClickListener = (Book, BookOverviewClick) -> Unit


class BookOverviewAdapter(
  bookClickListener: BookClickListener
) : CompositeListAdapter<Any>(BookOverviewDiff()) {

  init {
    addComponents(BookOverviewComponent(bookClickListener), BookOverviewHeaderComponent())
  }

  fun reloadBookCover(bookId: UUID) {
    for (i in 0 until itemCount) {
      val item = getItem(i)
      if (item is Book && item.id == bookId) {
        notifyItemChanged(i)
        break
      }
    }
  }
}
