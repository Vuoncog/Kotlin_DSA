package search

object BinarySearch {

    fun <T> binarySearch(array: Array<T>, bigger: (T) -> Boolean, data: T): Int {
        var left = 0
        var right = array.size - 1
        while (left <= right) {
            val mid = (left + right + 1) / 2
             if (array[mid]?.equals(data) == true) {
                 return mid
             }

            if (bigger(array[mid])) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return -1
    }
}