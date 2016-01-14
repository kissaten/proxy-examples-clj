(defproject hello-world-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [environ "1.0.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [org.clojure/data.codec "0.1.0"]
                 [clj-http "2.0.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :uberjar-name "hello-world.jar"
  :ring {:handler hello-world-clj.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
