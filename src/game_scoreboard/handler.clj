(ns game-scoreboard.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as schema]))

;; Schema types

;; s/Any, s/Bool, s/Num, s/Keyword, s/Symbol, s/Int, and s/Str are cross-platform schemas.


   (schema/optional-key

(def app
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

