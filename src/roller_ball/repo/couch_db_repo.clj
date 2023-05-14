(ns roller-ball.repo.couch-db-repo
  (:require [roller-ball.repo.couch-db-consts :refer [db-url]])
  (:require [clojure.data.json :as json])
  (:require [com.ashafa.clutch :refer [with-db put-document get-document]])
  (:require [com.ashafa.clutch.http-client :refer [couchdb-request]])
  (:require [com.ashafa.clutch.utils :refer [url]]))

(defn new-doc [doc]
  (try
    (with-db db-url (put-document doc))
    (catch Exception e
      (println (.getMessage e))
      )
    )
  )

(defn get-doc [doc-id]
  (with-db db-url (get-document doc-id)))

(defn update-doc [doc-id text]
  (with-db db-url
           (let [doc (get-document doc-id)]
             (put-document (assoc doc :text text))))
  (println get-document doc-id)
  )

(defn find-by-tag [tag]
  (try
    (def query {:selector {:tags { :$elemMatch { :$eq tag}}}})
    (def stringified (json/write-str query))
    (couchdb-request :post (url db-url "_find") :data stringified :headers {"Content-Type" "application/json"})
    (catch Exception e (println (.getMessage e)))
  )
)

(defn find-by-level [level]
  (try
    (def query {:selector {:parent { :$eq level}}})
    (def stringified (json/write-str query))
    (couchdb-request :post (url db-url "_find") :data stringified :headers {"Content-Type" "application/json"})
    (catch Exception e (println (.getMessage e)))
    )
  )
