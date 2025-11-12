(ns game-theory-calculator.core-test
  (:require [clojure.test :refer :all]
            [game-theory-calculator.games :as games]))

(deftest find-pure-nash-test
  (testing "Prisoner's Dilemma"
    (is (= [[:defect :defect]] (games/find-pure-nash games/prisoner-dilemma))))
  (testing "Stag Hunt"
    (is (= (set [[:stag :stag] [:hare :hare]]) (set (games/find-pure-nash games/stag-hunt)))))
  (testing "Battle of the Sexes"
    (is (= (set [[:game :game] [:ballet :ballet]]) (set (games/find-pure-nash games/battle-of-the-sexes))))))
