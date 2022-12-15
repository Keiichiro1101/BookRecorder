package hu.ait.bookrecorder3.googlebookapi

data class Item(
    val accessInfo: AccessInfoX,
    val etag: String,
    val id: String,
    val kind: String,
    val saleInfo: SaleInfoX,
    val searchInfo: SearchInfo,
    val selfLink: String,
    val volumeInfo: VolumeInfoX
)