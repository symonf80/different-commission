fun main() {

    val prevTransition = 500_000.0
    val currentTransition = 80_000.0
    val cardType = "Maestro"

    val rezultCommision = countCommision(cardType, prevTransition, currentTransition)
    if (rezultCommision == -1.0) {
        println("Превышен лимит перевода по карте $cardType")
    } else
        println("Комиссия с суммы $currentTransition по карте $cardType $rezultCommision руб.")
}

fun countCommision(cardType: String, prevTransition: Double, currentTransition: Double): Double {

    val limitMessage = -1.0
    val minCoast = 35.0
    val oneLimit = 150_000.0
    val oneLimitVk = 15_000.0
    val monthlyLimit = 600_000.0
    val monthLyLimitVk = 40_000.0
    var result = when (cardType) {
        "MasterCard", "Maestro" -> if (currentTransition < 75000.0) 0.0 else (currentTransition / 100 * 0.6) + 20.0
        "Visa", "Мир" -> if ((currentTransition / 100 * 0.75) < minCoast) minCoast else (currentTransition / 100 * 0.75)
        else -> if (currentTransition > oneLimitVk || prevTransition > monthLyLimitVk) limitMessage else 0.0

    }
    if (currentTransition > oneLimit || prevTransition > monthlyLimit) result = limitMessage

    return result
}

