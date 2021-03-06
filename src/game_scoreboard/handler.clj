(ns game-scoreboard.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as schema]
            [ring.middleware.cors :refer [wrap-cors]]))

;; Schema types

;; s/Any, s/Bool, s/Num, s/Keyword, s/Symbol, s/Int, and s/Str are cross-platform schemas.

;; Game scoreboard schemas
;;;;;;;;;;;;;

;; Define a schema for a specific score in a game
(schema/defschema Score
  {:player-id schema/Uuid
   :score     schema/Int})


;; A schema for the result of saving a score to the game scoreboard
(schema/defschema ScoreSaved
  {:status schema/Bool})


;; Design of the Scoreboard
(schema/defschema ScoreBoard
  [{:player-id schema/Uuid
    :score     schema/Int}])

;; Define a schema using a defined schema - to investigate
#_(schema/defschema ScoreBoard
    [Score])


(schema/defschema PlayerAccount
  {:player-id   schema/Uuid
   :player-name schema/Str

   (schema/optional-key
     :player-gravitar) schema/Str})


(schema/defschema AllPlayerAccounts
  [{:player-id   schema/Uuid
    :player-name schema/Str

    (schema/optional-key
      :player-gravitar) schema/Str}])


(schema/defschema NewPlayer
  {:name schema/Str

   (schema/optional-key
     :player-gravitar) schema/Str})

;; Game data
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Example Game data
#_{:player-id #uuid "131e3861-501a-4d19-b740-56c260cacdf8"
   :score     1000001}

(def score-board
  (atom
    []))

(def player-accounts
  (atom
    []))


;; Helper Functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; generate random account names
(defn random-uuid []
  (java.util.UUID/randomUUID))


(defn random-player-id
  []
  (str "player" (rand-int 1000)))


;; generate random score
(defn random-score []
  {:player-id (random-uuid)
   :score     (rand-int 100000000)})


(defn new-player-account
  "Create new player account using a given player name, saving the new account to the player account collection.

  Returns the new player account."
  [name]
  (let [new-account {:player-id   (random-uuid)
                     :player-name name}]
    (swap! player-accounts conj new-account)
    new-account))



;; API app
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def app
  (->
   (api
    {:swagger
     {:ui   "/"
      :spec "/swagger.json"
      :data {:info {:title       "Game-scoreboard"
                    :description "Compojure Api example"}
             :tags [{:name "game", :description "APIs for Game scores and scoreboards"}]}}}

    ;; API Route definitions
    ;; :body is the definition of the response
    (context "/game" []
             :tags ["game"]

             (GET "/scoreboard" []
                  :return ScoreBoard
                  :summary "The scores of the current scoreboard"
                  (ok @score-board))

             (POST "/new-score" []
                   :return ScoreSaved
                   :body [score Score]
                   :summary "Stores a score in the game board"
                   (ok {:status true}))

             (POST "/new-player" []
                   :return PlayerAccount
                   :body [new-player NewPlayer]
                   :summary "Create a new player account"
                   (ok (new-player-account (new-player :name))
                       ))

             (GET "/player-accounts" []
                  :return AllPlayerAccounts
                  :summary "Returns all the current player accounts"
                  (ok @player-accounts))

             (GET "/random-score" []
                  :return ScoreBoard
                  :summary "Generates a random score, adds it to the game board and returns the new game board"
                  (ok (swap! score-board conj (random-score))))
             ))
   (wrap-cors :access-control-allow-origin [#".*"]
              :access-control-allow-methods [:get :post])  ))







;; data model that came with the compojure-api template
;;;;;;;;;;;;;

#_(schema/defschema Pizza
    {:name   schema/Str
     :size   (schema/enum :L :M :S)
     :origin {:country (schema/enum :FI :PO)
              :city    schema/Str}

     (schema/optional-key
       :description) schema/Str
     })


;; api routes that came with the compojure-api template

#_(GET "/plus" []
       :return {:result Long}
       :query-params [x :- Long, y :- Long]
       :summary "adds two numbers together"
       (ok {:result (+ x y)}))

#_(POST "/echo" []
        :return Pizza
        :body [pizza Pizza]
        :summary "echoes a Pizza"
        (ok pizza))

;; REPL design journal
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; What values are in the score board?
@score-board

(deref score-board)


;; Add a specific score to the score-board

#_(swap! score-board conj {:player "REPL" :score 101010101})

#_(swap! score-board conj {:player "Richy" :score 12345})

#_(swap! score-board conj {:player "DNA" :score 42})

#_(swap! score-board conj {:player "RonnyPonny" :score 999999})


(swap! player-accounts conj {:player-id   (random-uuid)
                             :player-name (random-player-id)})


;; Creating a new player-accounts

#_(new-player-account "fred")
