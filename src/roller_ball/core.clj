(ns roller-ball.core
  (:gen-class)
  (:require [roller-ball.repo.couch-db-repo :refer [get-doc find-by-tag]])
  (:require [roller-ball.api.router :refer [api]])
  (:require [ring.adapter.jetty :as jetty]))

(defn get-docs [res]
  (get res :docs))

(defn get-tags [data]
  (map #(get % :tags) data))

(defn -main [& args]
  (try
    ;(def all ( ->
    ;           (find-by-tag "c")
    ;           (get-docs)
    ;           (get-tags)
    ;           ))
    ;(println all)
    (jetty/run-jetty api {:port 3001})
    (catch Exception e (println (.getMessage e))))
)




