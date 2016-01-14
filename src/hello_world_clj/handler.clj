(ns hello-world-clj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [clojure.string :as str]
            [ring.adapter.jetty :as jetty]
            [compojure.handler :refer [site]]
            [clojure.data.codec.base64 :as base64]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn get-quota-guard []
  (let [proxy-uri (java.net.URI. (env :quotaguardstatic-url))]
    (client/get "http://httpbin.org/ip"
      {:proxy-host (.getHost proxy-uri)
       :proxy-port (.getPort proxy-uri)
       :headers {
         "Proxy-Authorization" (str "Basic " (base64/encode (.getUserInfo proxy-uri)))}})))

(defroutes app-routes
  (GET "/quota-guard" [] (get-quota-guard))
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
