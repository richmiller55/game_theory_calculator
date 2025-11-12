(ns game-theory-calculator.core
  (:require [game-theory-calculator.games :as games])
  (:gen-class))

(defn -main
  "Finds and prints the pure-strategy Nash equilibria for the Prisoner's Dilemma."
  [& args]
  (println "Finding Nash equilibria for Prisoner's Dilemma:")
  (let [equilibria (games/find-pure-nash games/prisoner-dilemma)]
    (println equilibria)))
