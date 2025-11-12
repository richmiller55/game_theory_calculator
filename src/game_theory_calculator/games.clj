(ns game-theory-calculator.games)

;; Example: Representing a simple 2-player normal form game (e.g., Prisoner's Dilemma)
(def prisoner-dilemma
  {:players [:p1 :p2]
   :strategies {:p1 [:cooperate :defect]
                :p2 [:cooperate :defect]}
   :payoffs
   ;; Map of strategies to payoffs [P1 payoff, P2 payoff]
   {[:cooperate :cooperate] [3 3]
    [:cooperate :defect]    [0 4]
    [:defect :cooperate]    [4 0]
    [:defect :defect]       [1 1]}})

(def stag-hunt
  {:players [:p1 :p2]
   :strategies {:p1 [:stag :hare]
                :p2 [:stag :hare]}
   :payoffs {[:stag :stag] [5 5]
             [:stag :hare] [0 3]
             [:hare :stag] [3 0]
             [:hare :hare] [3 3]}})

(def battle-of-the-sexes
  {:players [:p1 :p2]
   :strategies {:p1 [:game :ballet]
                :p2 [:game :ballet]}
   :payoffs {[:game :game]     [3 1]
             [:game :ballet]   [0 0]
             [:ballet :game]   [0 0]
             [:ballet :ballet] [1 3]}})

;; Function to find pure Nash equilibria
(defn find-pure-nash [game]
  (let [players (:players game)
        strategies (:strategies game)
        payoffs (:payoffs game)
        strategy-profiles (keys payoffs)]
    (filter
     (fn [profile]
       (let [current-payoffs (get payoffs profile)]
         ;; Check if any player has an incentive to deviate
         (not-any?
          (fn [player-index]
            (let [player (nth players player-index)
                  player-strategy (nth profile player-index)
                  player-payoff (nth current-payoffs player-index)]
              ;; Find best alternative payoff for this player
              (let [other-strategies (remove #(= % player-strategy) (get strategies player))]
                (some
                 (fn [alt-strategy]
                   (let [alt-profile (assoc (vec profile) player-index alt-strategy)
                         alt-payoff (nth (get payoffs alt-profile) player-index)]
                     (> alt-payoff player-payoff)))
                 other-strategies))))
          (range (count players)))))
     strategy-profiles)))