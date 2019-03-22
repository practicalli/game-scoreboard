(defproject game-scoreboard "0.1.0-SNAPSHOT"
  :description "An Game Scoreboard API"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [metosin/compojure-api "1.1.11"]]
  :ring {:handler game-scoreboard.handler/app}
  :min-lein-version "2.9.1"
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [cheshire "5.5.0"]
                                  [ring/ring-mock "0.3.0"]]
                   :plugins      [[lein-ring "0.12.0"]]}})
