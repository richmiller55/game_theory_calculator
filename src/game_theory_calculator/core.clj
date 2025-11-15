(ns game-theory-calculator.core
  (:require [game-theory-calculator.static-games-of-complete-information :as games])
  (:gen-class))

(defn -main
  "Finds and prints the pure-strategy Nash equilibria for several games."
  [& args]
  (println "Finding Nash equilibria for Prisoner's Dilemma:")
  (let [equilibria (games/find-pure-nash games/prisoner-dilemma)]
    (println equilibria))
  (println "\nFinding Nash equilibria for Stag Hunt:")
  (let [equilibria (games/find-pure-nash games/stag-hunt)]
    (println equilibria))
  (println "\nFinding Nash equilibria for Battle of the Sexes:")
  (let [equilibria (games/find-pure-nash games/battle-of-the-sexes)]
    (println equilibria)))
