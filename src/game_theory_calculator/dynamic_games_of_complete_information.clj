(ns game-theory-calculator.dynamic-games-of-complete-information)

;; Data structure for the Sequential Bargaining game in extensive form
(def sequential-bargaining-game
  {:player :p1
   :actions {:L {:player :p2
                 :actions {:E {:payoffs [1 1]}
                           :O {:payoffs [3 0]}}}
             :S {:player :p2
                 :actions {:E {:payoffs [0 2]}
                           :O {:payoffs [2 0]}}}}})

(defn find-spne
  "Finds the Subgame Perfect Nash Equilibrium (SPNE) for a game in extensive form
   using backward induction.
   The game-tree is expected to be a map where:
   - Decision nodes have :player (keyword) and :actions (map of action-keyword to next-node).
   - Terminal nodes have :payoffs (vector of numbers, e.g., [p1-payoff p2-payoff])."
  [game-tree]
  (defn- backward-induct [node]
    (if (:payoffs node)
      ;; Terminal node: return payoffs and no further strategy
      {:payoffs (:payoffs node) :strategy {}}
      ;; Decision node
      (let [current-player (:player node)
            actions (:actions node)
            ;; Determine player index for payoff comparison (assuming :p1 is 0, :p2 is 1)
            player-index (case current-player
                           :p1 0
                           :p2 1
                           ;; Add more players if needed, or handle dynamically
                           (throw (ex-info "Unknown player" {:player current-player})))
            ;; Recursively solve for each action
            action-results (into {} (for [[action next-node] actions]
                                      [action (backward-induct next-node)]))
            ;; Find the best action for the current player based on their payoff
            best-action-entry (apply max-key #(get-in (second %) [:payoffs player-index]) action-results)
            chosen-action (first best-action-entry)
            chosen-result (second best-action-entry)]
        ;; Combine strategies and return chosen payoffs
        {:payoffs (:payoffs chosen-result)
         :strategy (assoc (:strategy chosen-result) current-player chosen-action)})))
  (backward-induct game-tree))
