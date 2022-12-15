package hu.ait.bookrecorder3.googlebookapi

data class AccessInfoX(
    val accessViewStatus: String,
    val country: String,
    val embeddable: Boolean,
    val epub: EpubX,
    val pdf: PdfX,
    val publicDomain: Boolean,
    val quoteSharingAllowed: Boolean,
    val textToSpeechPermission: String,
    val viewability: String,
    val webReaderLink: String
)