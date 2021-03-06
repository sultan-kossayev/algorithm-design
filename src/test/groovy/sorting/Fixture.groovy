package sorting

class Fixture {

    def static UNSORTED_DATA = [
            [],
            [1],
            [1, 3, 4, 5, 16, 16, 17, 27, 28, 36, 39, 42, 43, 46],
            [28, 41, 40, 23, 13, 35, 9, 23, 20, 10, 45, 2, 35, 20, 20, 2, 16, 19],
            [5, 1, 3, 5, 3, 10, 2],
            [2, 2, 2, 2, 2, 2],
            [4, -1, 3, -5, 10],
            [4, -1, 3, -5],
            [4, -1, 3, -5, 10, 6, 7],
            [25, 43, 45, 1, 39, 49, 29, 28, 8, 34, 4, 21, 33, 1],
            [9, 2, 36, 29, 28, 30, 39, 15, 5, 4, 39, 9, 16, 5, 46, 44, 44, 4, 50]
    ]


    def static SORTED_DATA = [
            [],
            [1],
            [1, 3, 4, 5, 16, 16, 17, 27, 28, 36, 39, 42, 43, 46],
            [2, 2, 9, 10, 13, 16, 19, 20, 20, 20, 23, 23, 28, 35, 35, 40, 41, 45],
            [1, 2, 3, 3, 5, 5, 10],
            [2, 2, 2, 2, 2, 2],
            [-5, -1, 3, 4, 10],
            [-5, -1, 3, 4],
            [-5, -1, 3, 4, 6, 7, 10],
            [1, 1, 4, 8, 21, 25, 28, 29, 33, 34, 39, 43, 45, 49],
            [2, 4, 4, 5, 5, 9, 9, 15, 16, 28, 29, 30, 36, 39, 39, 44, 44, 46, 50]
    ]
}
