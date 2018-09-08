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


operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other);
}

operator fun MyDate.plus(ti: TimeInterval): MyDate = addTimeIntervals(ti, 1)

operator fun MyDate.plus(rti: RepeatedTimeInterval) : MyDate
        = addTimeIntervals(rti.ti, rti.times)


operator fun TimeInterval.times(times : Int) : RepeatedTimeInterval {
    return RepeatedTimeInterval(this,times)
}

class RepeatedTimeInterval(val ti: TimeInterval, val times : Int)


enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class MyDateIterator(startDate: MyDate, endDate: MyDate) : Iterator<MyDate> {

    private var current: MyDate = startDate;
    private var endDate: MyDate = endDate

    override fun next(): MyDate {
        val result = current;
        if (hasNext()) {
            current = current.nextDay();
        }
        return result;
    }

    override fun hasNext(): Boolean {
        return current <= endDate;
    }

}

class DateRange(override val start: MyDate, override val endInclusive: MyDate)
    : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return MyDateIterator(start, endInclusive)
    }
}
