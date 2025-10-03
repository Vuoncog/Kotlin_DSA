package sorting

object Sorting {
    fun swap(array: IntArray, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    /**Merge Sort**/
    private fun merge(array: IntArray, left: Int, mid: Int, right: Int) {
        val leftSize = mid - left + 1
        val rightSize = right - mid

        val leftArray = Array(leftSize) { 0 }
        val rightArray = Array(rightSize) { 0 }

        //Copy
        for (i in 0 until leftSize) {
            leftArray[i] = array[left + i]
        }

        for (i in 0 until rightSize) {
            rightArray[i] = array[mid + i + 1]
        }

        var i = 0
        var j = 0
        var index = left
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] < rightArray[j]) {
                array[index] = leftArray[i]
                i++
            } else {
                array[index] = rightArray[j]
                j++
            }
            index++
        }

        while (i < leftSize) {
            array[index] = leftArray[i]
            i++
            index++
        }

        while (j < rightSize) {
            array[index] = rightArray[j]
            j++
            index++
        }
    }

    fun mergeSort(array: IntArray, left: Int, right: Int) {
        if (left >= right) return
        val mid = left + (right - left) / 2
        mergeSort(array, left, mid)
        mergeSort(array, mid + 1, right)

        merge(array, left, mid, right)
    }

    /** Quick Sort **/
    private fun partition(array: IntArray, left: Int, right: Int): Int {
        val pivot = array[right]
        var i = left - 1
        for (j in left until right) {
            if (array[j] <= pivot) {
                i++
                swap(array, i, j)
            }
        }
        swap(array, i + 1, right)
        return i + 1
    }

    fun quickSort(array: IntArray, left: Int, right: Int) {
        if (left >= right) {
            return
        }
        val partition = partition(array, left, right)
        quickSort(array, left, partition - 1)
        quickSort(array, partition + 1, right)
    }

    /** Heap sort **/
    private fun heap(array: IntArray, size: Int, nodeIndex: Int) {
        // n -> 2n + 1/ 2n + 2
        var largest = nodeIndex
        val left = 2 * nodeIndex + 1
        val right = 2 * nodeIndex + 2
        if (left < size && array[largest] < array[left]) {
            largest = left
        }
        if (right < size && array[largest] < array[right]) {
            largest = right
        }

        if (largest != nodeIndex) {
            swap(array, largest, nodeIndex)
            heap(array, size, largest)
        }
    }

    fun heapSort(array: IntArray) {
        for (i in (array.size / 2) - 1 downTo 0) {
            heap(array, array.size, i)
        }
        for (i in array.size - 1 downTo 1) {
            swap(array, i, 0)
            heap(array, i, 0)
        }
    }
}