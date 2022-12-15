package hu.ait.bookrecorder3.googlebookapi

data class VolumeInfoX(
    val allowAnonLogging: Boolean,
    val authors: List<String>,
    val averageRating: Double,
    val canonicalVolumeLink: String,
    val categories: List<String>,
    val contentVersion: String,
    val description: String,
    val imageLinks: ImageLinksXX,
    val industryIdentifiers: List<IndustryIdentifierX>,
    val infoLink: String,
    val language: String,
    val maturityRating: String,
    val pageCount: Int,
    val panelizationSummary: PanelizationSummaryX,
    val previewLink: String,
    val printType: String,
    val publishedDate: String,
    val publisher: String,
    val ratingsCount: Int,
    val readingModes: ReadingModesX,
    val subtitle: String,
    val title: String
)