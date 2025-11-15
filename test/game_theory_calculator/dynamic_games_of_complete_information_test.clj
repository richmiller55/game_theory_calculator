(ns game-theory-calculator.dynamic-games-of-complete-information-test
  (:require [clojure.test :refer :all]
            [game-theory-calculator.dynamic-games-of-complete-information :as dynamic-games]))

(deftest find-spne-test
  (testing "Sequential Bargaining Game"
    (let [result (dynamic-games/find-spne dynamic-games/sequential-bargaining-game)]
      (is (= [1 1] (:payoffs result)))
      (is (= {:p1 :L, :p2 :E} (:strategy result))))))
