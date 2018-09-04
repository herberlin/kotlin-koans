package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return this.getCheck() - other.getCheck()
    }

    fun getCheck(): Int {
        return this.year * 10000 + month * 100 + dayOfMonth;
    }
}

//operator fun MyDate.compareTo(other: MyDate): Int {
//    return this.getCheck()-  other.getCheck()
//}


operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
