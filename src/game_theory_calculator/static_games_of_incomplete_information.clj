(ns game-theory-calculator.static-games-of-incomplete-information)

;; Bayesian games and Bayesian equilibrium
;;
;; Static Games of Incomplete Information
;; This category is a transitional one, moving from complete to incomplete information.
;; Static games with incomplete information are modeled as Bayesian Games using the
;; Harsanyi transformation. The key concept is that a player's "type" (private information)
;; is unknown to other players.
;;
;; Essential Information/Key Messages
;; - Private Information: At least one player's payoff function is not common knowledge.
;; - Beliefs: Players form probabilistic beliefs (a common prior) about other players' "types."
;; - Modeling Tool: The game is transformed into a Bayesian Game where Nature moves first to assign types.
;;
;;
;; Bayesian Games
;; These are games where players have private information (types) and form beliefs about the
;; types of others. They encompass both static and dynamic scenarios.
;;
;; Essential Information/Key Messages
;; - Type Space (T_i): The set of possible types for each player.
;; - Beliefs (p(t_j | t_i)): The probability player i assigns to player j having type t_j, given i's own type t_i.
;; - Core Solution Concept: Bayesian Nash Equilibrium (BNE).
;;
;; Calculation Focus
;; - Expected Utility Maximization: Each player chooses a strategy (an action contingent on their type)
;;   that maximizes their expected payoff given their beliefs and the strategies of the other players.
;; - Bayesian Nash Equilibrium (BNE): A profile of strategies where each player's strategy is a
;;   best response to the other players' strategies for every possible type that player could be.

;; --- Example: Cournot Duopoly with Uncertain Costs ---

;; Scenario: Two firms (1 and 2) simultaneously choose quantities (q1, q2).
;; Firm 1's cost (c1) is known. Firm 2's cost (c2) can be either c_H (High) or c_L (Low),
;; with probabilities π and 1-π, respectively. Firm 1 knows these probabilities,
;; but not Firm 2's actual cost.
;;
;; Payoff (Profit) Functions:
;; Π_i(q1, q2, c_i) = P(Q)q_i - c_i * q_i, where Q = q1 + q2 and P(Q) is the market inverse demand.

(def cournot-duopoly-bayesian-game
  "Represents the conceptual structure of the Cournot game with incomplete information.
   Strategies are continuous, and payoffs are symbolic functions."
  {:players [:firm1 :firm2]
   :types {:firm2 {:high-cost {:prob 'pi}
                   :low-cost  {:prob '(- 1 pi)}}}
   ;; Strategies are continuous quantities q >= 0.
   :strategies {:firm1 :continuous
                :firm2 :continuous}
   ;; Payoff functions are represented symbolically.
   ;; We assume a linear inverse demand P(Q) = A - Q for this example.
   :payoff-functions
   {:firm1 '(fn [q1 q2 c1 A]
              (- (* (- A (+ q1 q2)) q1) (* c1 q1)))
    :firm2 '(fn [q1 q2 c2 A]
              (- (* (- A (+ q1 q2)) q2) (* c2 q2)))}})

;; --- Why Symbolic Math is Required for Continuous Bayesian Games ---
;;
;; Finding the Bayesian Nash Equilibrium (BNE) for a game with continuous strategies
;; (like quantity in the Cournot example) and symbolic payoffs requires methods from
;; calculus and algebra, not just numerical computation. A simple numerical calculator
;; cannot solve these problems directly because it works with numbers, not abstract functions.
;;
;; The process involves these steps:
;;
;; 1. Define Expected Payoff Functions:
;;    A player who is uncertain about another's type must maximize their *expected* payoff.
;;    For example, Firm 1 doesn't know if Firm 2 is high-cost (HC) or low-cost (LC).
;;    Its expected profit E[Π1] is a weighted average based on its beliefs (probabilities):
;;    E[Π1] = π * Π1(q1, q2_HC, c1) + (1-π) * Π1(q1, q2_LC, c1)
;;    This is a symbolic function of the quantities q1, q2_HC, and q2_LC.
;;
;; 2. Use Calculus to Find Best-Response Functions:
;;    To find the profit-maximizing quantity, each player type takes the partial derivative
;;    of its (expected) profit function with respect to its own quantity and sets it to zero.
;;    - Firm 1 solves: ∂E[Π1]/∂q1 = 0  =>  This yields a best-response function: q1 = f(q2_HC, q2_LC)
;;    - Firm 2 (High Cost) solves: ∂Π2/∂q2_HC = 0 => This yields: q2_HC = g(q1)
;;    - Firm 2 (Low Cost) solves: ∂Π2/∂q2_LC = 0  => This yields: q2_LC = h(q1)
;;
;; 3. Solve a System of Simultaneous Equations:
;;    The BNE is the solution to the system of these best-response functions. We have three
;;    equations and three unknowns (q1, q2_HC, q2_LC). Solving this system requires
;;    symbolic algebraic manipulation (e.g., substitution).
;;
;; This entire process—differentiation and solving equations with variables—is the domain of
;; a Computer Algebra System (CAS). A numerical approach would require discretizing the
;; infinite strategy space into a finite grid, which is only an approximation and
;; computationally intensive.
;;
;; A numerical solver for *discrete* Bayesian games, however, is feasible. It would calculate
;; the expected utility for each discrete action/type pair by summing over the opponent's
;; possible types and actions, weighted by the probabilities.
