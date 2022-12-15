package hu.ait.bookrecorder3.googlebookapi

data class BookResult(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)