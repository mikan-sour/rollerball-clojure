(ns roller-ball.api.GET-objects-at-level
  (:require [clojure.string :as str])
  (:require [ring.util.response :refer [response]])
  (:require [roller-ball.repo.couch-db-repo :refer [find-by-level]]))

(defn get-parent "gets `parent` from :query-string"
  [req]
  (->
    (get req :query-string)
    (str/split #"=")
    (second)
  )
)

(defn GET-objects-at-level [req]
  (try
    (def result (
      -> req
       (get-parent)
       (find-by-level)))
    (response {:result result })
    (catch Exception e
      (println (.getMessage e))
      )
  )
)