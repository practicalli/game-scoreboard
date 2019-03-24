(ns game-scoreboard.core-test
  (:require [cheshire.core :as cheshire]
            [clojure.test :refer :all]
            [game-scoreboard.handler :refer :all]
            [ring.mock.request :as mock]))

;; Helper functions
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))


;; API focused tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


#_(deftest a-test

    (testing "Test GET request to /hello?name={a-name} returns expected response"

      (let [response (app (-> (mock/request :get  "/game/plus?x=1&y=2")))
            body     (parse-body (:body response))]

        (is (= (:status response) 200))
        (is (= (:result body) 3)))))

      (is (= (:status response) 200))
      (is (= (:result body) 3)))))


;; REPL experiments
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; We can see how mocking in a test works
;; by debugging the following code
(let [response  (app (-> (mock/request :get "/game/random-score")))
      body      (parse-body (:body response))
      {:keys
       [player-id
        score]} (first body)]



  ;; assertions
  (is (= (:status response) 200))
  #_(is (uuid? player-id))
  (is (int? score))
  )
;; => true


;; `uuid?` function does not return true if the #uuid tag literal is missing


