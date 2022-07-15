package tech.ericwathome.currencybudy.model

data class RateDetails(
    val success: String,
    val query: Query,
    val info: Info,
    val date: String,
    val result: Float
)

data class Query(
    val from: String,
    val to: String,
    val amount: Int
)

data class Info(
    val timeStamp: Long,
    val rate: Float
)