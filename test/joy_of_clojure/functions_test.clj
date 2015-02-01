(ns joy-of-clojure.functions-test
  (:require [clojure.test :refer :all]
            [joy_of_clojure.functions :refer :all]))

(deftest reverse-empty-list
  (testing "Reversing an empty seq with give an empty seq"
    (is (empty? (my-reverse [])))))

