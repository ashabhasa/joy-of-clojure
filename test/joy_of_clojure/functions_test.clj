(ns joy-of-clojure.functions-test
  (:require [clojure.test :refer :all]
            [joy-of-clojure.functions :refer :all]))

(deftest reverse-empty-list
  (testing "Reversing an empty seq with give an empty seq"
    (is (empty? (my-reverse []))))
  (testing "Reversing a one element list should give a list with the same element"
    (is (= [1] (my-reverse [1]))))
  (testing "Reverse a list"
    (is (= [9 8 7 6 5 4 3 2 1 0] (my-reverse (range 10)))))
  (testing "Nested lists"
    (is (= [[4 5] 3 [1 2]] (my-reverse [[1 2] 3 [4 5]])))))
