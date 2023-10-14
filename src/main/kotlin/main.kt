    val prevTransition = 0.0
    val currentTransition = 10_000.0
    val cardType = "Vk_Pay" 

fun main() {
    val rezultCommision = countCommision(cardType, prevTransition, currentTransition)
    if (rezultCommision == -1.0) {
        println("Превышен лимит перевода по карте $cardType")
    } else
        println("Комиссия с суммы $currentTransition по карте $cardType $rezultCommision руб.")
}

fun countCommision(cardType: String, prevTransition: Double, currentTransition: Double): Double {
    val prevTransSum = prevTransition + currentTransition
    val limitMessage = -1.0
    val minCoast = 35.0
    val oneLimit = 150_000.0
    val oneLimitVk = 15_000.0
    val monthlyLimit = 600_000.0
    val monthLyLimitVk = 40_000.0
    var result = when (cardType) {
        "MasterCard", "Maestro" -> if (currentTransition < 75000.0) 0.0 else (currentTransition / 100 * 0.6) + 20.0
        "Visa", "Мир" -> if ((currentTransition / 100 * 0.75) < minCoast) minCoast else (currentTransition / 100 * 0.75)
        else -> if (currentTransition > oneLimitVk || prevTransSum > monthLyLimitVk) limitMessage else 0.0

    }
    if (currentTransition > oneLimit || prevTransSum > monthlyLimit) result = limitMessage

    return result
}

