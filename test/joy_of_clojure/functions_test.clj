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


(deftest count-down-test
  (testing "from 0"
    (is (= [] (count-down 0))))
  (testing "from 1"
    (is (= [1] (count-down 1))))
  (testing "from 5"
    (is (= [5 4 3 2 1] (count-down 5))))
  (testing "first of 1000000"
    (is (= 1000000 (first (count-down 1000000))))))


(deftest strict-map-tests
  (testing "empty collection"
    (is (= [] (strict-map - []))))
  (testing "collection with one element"
    (is (= [-1] (strict-map - [1]))))
  (testing "collections with two elements"
    (is (= [-1 -2] (strict-map - [1 2]))))
  (testing "collection with many elements"
    (is (= [0 -1 -2 -3 -4 -5 -6 -7 -8] (strict-map - (range 9))))))